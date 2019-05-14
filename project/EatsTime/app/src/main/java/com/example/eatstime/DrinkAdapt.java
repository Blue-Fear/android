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

public class DrinkAdapt extends ArrayAdapter<Drink> {
    Context context;
    int layoutResourceId;
    ArrayList<Drink> data = null;

    public DrinkAdapt(Context context, int layoutResourceId, ArrayList<Drink> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    static class DrinkHolder{
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtDetail;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DrinkAdapt.DrinkHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new DrinkAdapt.DrinkHolder();
            holder.imgIcon= (ImageView)row.findViewById(R.id.imgPic);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.txtDetail = (TextView) row.findViewById(R.id.txtDetail);
            row.setTag(holder);
        } else {
            holder = (DrinkAdapt.DrinkHolder) row.getTag();
        }

        Drink item = data.get(position);
        holder.imgIcon.setImageResource(item.icon);
        holder.txtTitle.setText(item.title);
        holder.txtDetail.setText("Giá: "+item.detail+"đ");
        return row;
    }
}
