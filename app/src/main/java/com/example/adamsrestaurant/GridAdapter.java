package com.example.adamsrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private int foodImage[];
    private String foodName[];
    private String foodPrice[];
    private Context context;
    private LayoutInflater layoutInflater;

    public GridAdapter(Context context, int foodImage[], String foodName[], String foodPrice[]){
        this.context=context;
        this.foodImage=foodImage;
        this.foodName=foodName;
        this.foodPrice=foodPrice;
    }

    @Override
    public int getCount() {
        return foodName.length;
    }

    @Override
    public Object getItem(int position) {
        return foodName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if(convertView == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = layoutInflater.inflate(R.layout.food_items, null);
        }

        ImageView ifoodImage = (ImageView) gridView.findViewById(R.id.iv_food);
        TextView ifoodName = (TextView) gridView.findViewById(R.id.tv_foodName);
        TextView ifoodPrice = (TextView) gridView.findViewById(R.id.tv_foodPrice);

        ifoodImage.setImageResource(foodImage[position]);
        ifoodName.setText(foodName[position]);
        ifoodPrice.setText(foodPrice[position]);

        return gridView;
    }
}
