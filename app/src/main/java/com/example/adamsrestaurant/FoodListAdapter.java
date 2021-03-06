package com.example.adamsrestaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodListAdapter  extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Food> foodList;
    private ImageView imageView;

    public FoodListAdapter(Context context, int layout, ArrayList<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.tv_foodName);
            holder.txtPrice = (TextView) row.findViewById(R.id.tv_foodPrice);
           // holder.imageView (ImageView) row.findViewById(R.id.iv_food);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }
        Food food = foodList.get(position);
        holder.txtName.setText(food.getItemName());
        holder.txtPrice.setText(food.getPrice());

        byte[] foodImage = food.getLink();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
