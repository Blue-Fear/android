package com.example.w7_database;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MonhocAdapt extends ArrayAdapter<Monhoc> {
    Context context;
    int layoutResourceId;
    ArrayList<Monhoc> data = null;

    public MonhocAdapt(Context context, int layoutResourceId, ArrayList<Monhoc> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    static class MonHocHolder {
        ImageView img;
        TextView txtTitle, txtMa, txtTiet;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MonHocHolder holder = null;
        if (row != null) {
            holder = (MonHocHolder) row.getTag();
        }
        else{
            holder = new MonHocHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder.img = (ImageView)row.findViewById(R.id.imgPic);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.txtMa = (TextView) row.findViewById(R.id.txtMa);
            holder.txtTiet = (TextView) row.findViewById(R.id.txtTiet);
            row.setTag(holder);
        }

        Monhoc item = data.get(position);
        holder.img.setImageResource(R.mipmap.ic_launcher);
        holder.txtTitle.setText("Ten MH: "+item.getmTen());
        holder.txtMa.setText("Ma MH: "+item.getmMa());
        holder.txtTiet.setText("So Tiet: "+item.getmTiet());
        return row;
    }
}
