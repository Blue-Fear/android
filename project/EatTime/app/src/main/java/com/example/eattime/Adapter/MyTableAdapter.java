package com.example.eattime.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eattime.Model.Table;
import com.example.eattime.R;

import java.util.List;

public class MyTableAdapter extends RecyclerView.Adapter<MyTableAdapter.MyViewHolder> {

    Context context;
    List<Table> tableList;

    public MyTableAdapter(Context context, List<Table> tableList) {
        this.context = context;
        this.tableList = tableList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_table, viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.txt_table_name.setText(tableList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_table_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_table_name = (TextView)itemView.findViewById(R.id.txt_table_name);
        }
    }
}
