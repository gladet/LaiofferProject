package com.laioffer.laiofferproject;


import android.graphics.Color;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantGridFragment extends Fragment {

    private GridView mGridView;

    public RestaurantGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_restaurant_grid, container, false);
        View view = inflater.inflate(R.layout.fragment_restaurant_grid, container, false);
        mGridView  = (GridView) view.findViewById(R.id.restaurant_grid_view);
        mGridView.setAdapter(new RestaurantAdapter(getActivity()));
        return view;
    }

    // Change background color if the item is selected
    public void onItemSelected(int position){
        for (int i = 0; i < mGridView.getChildCount(); i++){
            if (position == i) {
                mGridView.getChildAt(i).setBackgroundColor(Color.BLUE);
            } else {
                mGridView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }


}
