package com.laioffer.laiofferproject;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by music on 11/20/2017.
 */

public class DataService {
    /**
     * Fake all the restaurant data for now. We will refine this and connect
     * to our backend later.
     */
    /*public static List<Restaurant> getRestaurantData() {
        List<Restaurant> eventData = new ArrayList<Restaurant>();
        for (int i = 0; i < 10; ++i) {
            eventData.add(
                    new Restaurant("Restaurant", "1184 W valley Blvd, CA 90101",
                            "Asian", i * 7 + 7,
                            i * 5 - 5
                    ));
        }
        return eventData;
    }*/

    /**
     * Get nearby restaurants through Yelp API.
     */
    public List<Restaurant> getNearbyRestaurants() {
        YelpApi yelp = new YelpApi();
        String jsonResponse = yelp.searchForBusinessesByLocation("dinner", "San Francisco, CA", 20);
        return parseResponse(jsonResponse);
    }

    /**
     * Parse the JSON response returned by Yelp API.
     */
    private List<Restaurant> parseResponse(String jsonResponse)  {
        try {
            JSONObject json = new JSONObject(jsonResponse);
            JSONArray businesses = json.getJSONArray("businesses");
            ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
            for (int i = 0; i < businesses.length(); i++) {
                JSONObject business = businesses.getJSONObject(i);

                //Parse restaurant information
                if (business != null) {
                    String name = business.getString("name");
                    String type = ((JSONArray) business.get("categories")).
                            getJSONArray(0).get(0).toString();

                    JSONObject location = (JSONObject) business.get("location");
                    JSONObject coordinate = (JSONObject) location.get("coordinate");
                    double lat = coordinate.getDouble("latitude");
                    double lng = coordinate.getDouble("longitude");
                    String address =
                            ((JSONArray) location.get("display_address")).get(0).toString();

                    // Download the image.
                    Bitmap thumbnail =  getBitmapFromURL(business.getString("image_url"));
                    Bitmap rating = getBitmapFromURL(business.getString("rating_img_url"));

                    restaurants.add(new Restaurant(name, address, type, lat, lng, thumbnail, rating));
                }
            }
            return restaurants;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Download an Image from the given URL, then decodes and returns a Bitmap object.
     */
    public Bitmap getBitmapFromURL(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage().toString());
        }
        return bitmap;
    }

}
