package com.laioffer.laiofferproject;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends Fragment {

    OnItemSelectListener mCallback;

    ListView mListView;

    // Container Activity must implement this interface
    public interface OnItemSelectListener {
        public void onItemSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnItemSelectListener) context;
        } catch (ClassCastException e) {
            //do something
        }
    }


    public RestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        mListView = (ListView) view.findViewById(R.id.restuarant_list);
        mListView.setAdapter(new RestaurantAdapter(getActivity()));
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getRestaurantsName());*/

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Restaurant r = (Restaurant)mListView.getItemAtPosition(position);
                //Prepare all the data we need to start map activity.
                Bundle bundle = new Bundle();
                bundle.putParcelable(RestaurantMapActivity.EXTRA_LATLNG,
                        new LatLng(r.getLat(), r.getLng()));

                //Create explicit intent to start map activity class
                Intent intent = new Intent(view.getContext(), RestaurantMapActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                //mCallback.onItemSelected(i);
            }
        });


        // Assign adapter to ListView.
        //listView.setAdapter(adapter);
        return view;

    }

    /**
     *   Get resturants names
     */
    private String[] getRestaurantsName() {
        String[] names = {
                "Restaurant1", "Restaurant2", "Restaurant3",
                "Restaurant4", "Restaurant5", "Restaurant6",
                "Restaurant7", "Restaurant8", "Restaurant9",
                "Restaurant10", "Restaurant11", "Restaurant12"};
        return names;
    }

    public void onItemSelected(int position){
        for (int i = 0; i < mListView.getChildCount(); i++){
            if (position == i) {
                mListView.getChildAt(i).setBackgroundColor(Color.BLUE);
            } else {
                mListView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }

}
