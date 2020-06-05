package com.example.adamsrestaurant;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class StartersFragment extends Fragment {

    GridView gridView;
    private String foodCategory = "Starters";
    int foodImages[] = {R.drawable.m01_garlicbread, R.drawable.m02_bruschetta, R.drawable.m03_mushroomsoup,
    R.drawable.m04_cheeseplatter, R.drawable.m05_chickenwings, R.drawable.m06_prawncocktail, R.drawable.m07_springrolls};
    String foodName[];
    String foodPrice[];
    DatabaseHelper myDB;

    public StartersFragment() {
        // Required empty public constructor
    }

    public static StartersFragment newInstance() {
        StartersFragment fragment = new StartersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDB = new DatabaseHelper(this.getContext());



        Cursor resultStarters = myDB.getCategory(foodCategory);


        for(int i = 0; i<resultStarters.getCount(); i++){
            Cursor currentDish = myDB.getRowAt(Integer.toString(i));
            if(currentDish.getString(1).equals(foodCategory)){

                foodName[i] = currentDish.getString(2);
                foodPrice[i] = currentDish.getString(9);
            }

        }

        GridAdapter adapter = new GridAdapter(StartersFragment.this.getContext(), foodImages, foodName, foodPrice);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StartersFragment.this.getContext(), "Selected Item: "+foodName[position], Toast.LENGTH_SHORT).show();
            }
        });



        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_starters, container, false);
        // Inflate the layout for this fragment
        gridView = (GridView) view.findViewById(R.id.gridView);
        return view;
    }
}
