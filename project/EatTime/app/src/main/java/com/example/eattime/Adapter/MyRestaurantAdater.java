package com.example.eattime.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eattime.Common.Common;
import com.example.eattime.Interface.IRecycleItemSelectedListener;
import com.example.eattime.Model.Restaurant;
import com.example.eattime.R;

import java.util.ArrayList;
import java.util.List;

public class MyRestaurantAdater extends RecyclerView.Adapter<MyRestaurantAdater.MyViewHolder> {

    Context context;
    List<Restaurant> restaurantList;
    List<CardView> cardViewList;
    //Broadcast de cho bookingActitiy biet da chon table
    LocalBroadcastManager localBroadcastManager;

    public MyRestaurantAdater(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
        cardViewList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_restaurant, viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.txt_restaurant_name.setText(restaurantList.get(i).getName());
        myViewHolder.txt_restaurant_address.setText(restaurantList.get(i).getAddress());

        if (!cardViewList.contains(myViewHolder.card_restaurant))
            cardViewList.add(myViewHolder.card_restaurant);

        myViewHolder.setiRecycleItemSelectedListener(new IRecycleItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int pos) {
                // Set mau nen cho tables khong duoc chon
                for (CardView cardView:cardViewList)
                    cardView.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
                // Set mau cho table duoc chon
                myViewHolder.card_restaurant.setCardBackgroundColor(context.getResources().getColor(R.color.colorButton1));

                // broadcast de bookingActivity enable next button
                Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
                //can phai gui restaurant object den intent nen phai implement Parcelable trong restaurant obj
                intent.putExtra(Common.KEY_RESTAURANT_STORE, restaurantList.get(pos));
                intent.putExtra(Common.KEY_STEP,1);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_restaurant_name, txt_restaurant_address;
        CardView card_restaurant;

        IRecycleItemSelectedListener iRecycleItemSelectedListener;

        public void setiRecycleItemSelectedListener(IRecycleItemSelectedListener iRecycleItemSelectedListener) {
            this.iRecycleItemSelectedListener = iRecycleItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_restaurant_address = (TextView)itemView.findViewById(R.id.txt_restaurant_address);
            txt_restaurant_name = (TextView)itemView.findViewById(R.id.txt_restaurant_name);
            card_restaurant = (CardView)itemView.findViewById(R.id.card_restaurant);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecycleItemSelectedListener.onItemSelectedListener(v,getAdapterPosition());
        }
    }
}
