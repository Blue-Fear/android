package com.example.eatstime;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapt extends ArrayAdapter<Food> {
    Context context;
    int layoutResourceId;
    ArrayList<Food> data = null;

    public FoodAdapt(Context context, int layoutResourceId, ArrayList<Food> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FoodHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new FoodHolder();
            holder.imgPic = (ImageView)row.findViewById(R.id.imgPic);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.txtDetail = (TextView) row.findViewById(R.id.txtDetail);
            row.setTag(holder);
        } else {
            holder = (FoodHolder) row.getTag();
        }

        Food item = data.get(position);
        holder.imgPic.setImageResource(item.image);
        holder.txtTitle.setText(item.title);
        holder.txtDetail.setText("Giá: "+item.detail+"đ");
        return row;
    }

    static class FoodHolder {
        ImageView imgPic;
        TextView txtTitle;
        TextView txtDetail;
    }

}
