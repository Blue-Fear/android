package com.example.eattime.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eattime.Common.Common;
import com.example.eattime.Model.TimeSlot;
import com.example.eattime.R;

import java.util.ArrayList;
import java.util.List;

public class MyTimeSlotAdapter extends RecyclerView.Adapter<MyTimeSlotAdapter.MyViewHolder> {

    Context context;
    List<TimeSlot> timeSlotList;

    public MyTimeSlotAdapter(Context context) {
        this.context = context;
        this.timeSlotList = new ArrayList<>();
    }

    public MyTimeSlotAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_time_slot,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txt_time_slot.setText(new StringBuilder(Common.convertTimeSlotToString(i)).toString());

        if (timeSlotList.size() == 0) // neu tat ca vi tri trong > show list
        {
            myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
            myViewHolder.txt_time_slot_des.setText("Còn Trống");
            myViewHolder.txt_time_slot_des.setTextColor(context.getResources().getColor(R.color.colorButton1));
            myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));

        }
        else // neu co vi tri da book
        {
            for (TimeSlot slotValue:timeSlotList)
            {
                // loop all time slot tu server va set mau khac
                int slot = Integer.parseInt(slotValue.getSlot().toString());
                myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
                myViewHolder.txt_time_slot_des.setText("Còn Trống");
                myViewHolder.txt_time_slot_des.setTextColor(context.getResources().getColor(R.color.colorButton1));
                myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));
                if (slot == i) // neu slot == vi tri
                {
                    myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
                    myViewHolder.txt_time_slot_des.setText("Full");
                    myViewHolder.txt_time_slot_des.setTextColor(context.getResources().getColor(android.R.color.white));
                    myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return Common.TIME_SLOT_TOTAL;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_time_slot, txt_time_slot_des;
        CardView card_time_slot;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_time_slot = (CardView)itemView.findViewById(R.id.card_time_slot);
            txt_time_slot = (TextView)itemView.findViewById(R.id.txt_time_slot);
            txt_time_slot_des = (TextView)itemView.findViewById(R.id.txt_time_des);

        }
    }
}
