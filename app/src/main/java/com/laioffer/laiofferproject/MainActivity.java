package com.laioffer.laiofferproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  implements RestaurantListFragment
        .OnItemSelectListener, RestaurantGridFragment.OnItemSelectListener {

    private RestaurantListFragment mListFragment;
    private RestaurantGridFragment mGridFragment;
    private BackendListFragment mBackendFragment;

    @Override
    public void onListItemSelected(int position) {
        mListFragment.onItemSelected(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show different fragments based on screen size.
        /*if (findViewById(R.id.fragment_container) != null) {
            Fragment fragment = isTablet() ? new RestaurantGridFragment()
                    : new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    fragment).commit();
        }*/

        //add list view
        //mListFragment = new RestaurantListFragment();
        mBackendFragment = new BackendListFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.restaurant_list_container,
                mBackendFragment).commit();

        /*new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                YelpApi yelp = new YelpApi();
                yelp.searchForBusinessesByLocation("dinner", "San Francisco, CA", 20);
                return null;
            }
        }.execute();*/



        //add Gridview
        /*if (isTablet()) {
            mGridFragment = new RestaurantGridFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.restaurant_grid_container,
                    mGridFragment).commit();
        }*/

        /*// Get ListView object from xml.
        ListView eventListView = (ListView) findViewById(R.id.event_list);

        // Initialize an adapter.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.restaurant_item,
                R.id.event_name,
                getRestaurantsName());

        // Assign adapter to ListView.
        RestaurantAdapter adapter = new RestaurantAdapter(this);

        // Assign adapter to ListView.
        eventListView.setAdapter(adapter);

        Log.e("Life cycle test", "We are at onCreate()");*/
    }

    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     *   Get resturants names
     */
    /*private String[] getRestaurantsName() {
        String[] names = {
                "Restaurant1", "Restaurant2", "Restaurant3",
                "Restaurant4", "Restaurant5", "Restaurant6",
                "Restaurant7", "Restaurant8", "Restaurant9",
                "Restaurant10", "Restaurant11", "Restaurant12"};
        return names;

    }*/

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life cycle test", "We are at onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life cycle test", "We are at onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life cycle test", "We are at onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life cycle test", "We are at onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life cycle test", "We are at onDestroy()");
    }

    // Add this method to main activity
    @Override
    public void onItemSelected(int position){
        //mGridFragment.onItemSelected(position);
        if (!isTablet()) {
            Intent intent = new Intent(this, RestaurantGridActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        } else {
            mGridFragment.onItemSelected(position);
        }

    }
}
