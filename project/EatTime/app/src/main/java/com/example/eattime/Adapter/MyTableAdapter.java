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
import com.example.eattime.Model.Table;
import com.example.eattime.R;

import java.util.ArrayList;
import java.util.List;

public class MyTableAdapter extends RecyclerView.Adapter<MyTableAdapter.MyViewHolder> {

    Context context;
    List<Table> tableList;

    // add listener khi chon table
    List<CardView> cardViewList;
    LocalBroadcastManager localBroadcastManager;



    public MyTableAdapter(Context context, List<Table> tableList) {
        this.context = context;
        this.tableList = tableList;
        cardViewList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_table, viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        myViewHolder.txt_table_name.setText(tableList.get(i).getName());

        if (!cardViewList.contains(myViewHolder.card_table));
            cardViewList.add(myViewHolder.card_table);

        myViewHolder.setiRecycleItemSelectedListener(new IRecycleItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int pos) {
                // Set mau nen cho tat ca item khong duoc chon
                for (CardView cardView : cardViewList)
                {
                    cardView.setBackgroundColor(context.getResources()
                            .getColor(android.R.color.white));
                }
                // Set mau nen cho item duoc chon
                myViewHolder.card_table.setBackgroundColor(
                        context.getResources()
                        .getColor(R.color.colorButton1)
                );

                // gui broacast de enable button next
                Intent intent  = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
                intent.putExtra(Common.KEY_TABLE_SELECTED,tableList.get(pos));
                intent.putExtra(Common.KEY_STEP,2);
                localBroadcastManager.sendBroadcast(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_table_name;
        CardView card_table;

        IRecycleItemSelectedListener iRecycleItemSelectedListener;

        public void setiRecycleItemSelectedListener(IRecycleItemSelectedListener iRecycleItemSelectedListener) {
            this.iRecycleItemSelectedListener = iRecycleItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card_table = (CardView)itemView.findViewById(R.id.card_table);
            txt_table_name = (TextView)itemView.findViewById(R.id.txt_table_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecycleItemSelectedListener.onItemSelectedListener(v,getAdapterPosition());
        }
    }
}
