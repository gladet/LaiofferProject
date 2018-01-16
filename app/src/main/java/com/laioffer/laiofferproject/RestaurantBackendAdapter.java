package com.laioffer.laiofferproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by music on 11/29/2017.
 */

public class RestaurantBackendAdapter extends BaseAdapter {
    private Context context;
    private List<Restaurant> restaurantData;
    private DataService dataService;

    public RestaurantBackendAdapter(Context context, List<Restaurant> restaurantData) {
        this.context = context;
        this.restaurantData = restaurantData;
        dataService = new DataService();
    }

    @Override
    public int getCount() {
        return restaurantData.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

}
