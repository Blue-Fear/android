package com.example.eattime.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eattime.Model.Restaurant;
import com.example.eattime.R;

import java.util.List;

public class MyRestaurantAdater extends RecyclerView.Adapter<MyRestaurantAdater.MyViewHolder> {

    Context context;
    List<Restaurant> restaurantList;

    public MyRestaurantAdater(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_restaurant, viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txt_restaurant_name.setText(restaurantList.get(i).getName());
        myViewHolder.txt_restaurant_address.setText(restaurantList.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_restaurant_name, txt_restaurant_address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_restaurant_address = (TextView)itemView.findViewById(R.id.txt_restaurant_address);
            txt_restaurant_name = (TextView)itemView.findViewById(R.id.txt_restaurant_name);
        }
    }
}
