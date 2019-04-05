package com.example.country;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Country> {
    Context context;
    int layoutResourceId;
    ArrayList<Country> data = null;

    public Adapter(Context context, int layoutResourceId, ArrayList<Country> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CountryHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CountryHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.txtCurrency = (TextView) row.findViewById(R.id.txtCurrency);
            row.setTag(holder);
        } else {
            holder = (CountryHolder) row.getTag();
        }

        Country item = data.get(position);
        holder.txtTitle.setText(item.countryName);
        holder.imgIcon.setImageResource(item.flagName);
        holder.txtCurrency.setText("Đơn vị tiền tệ: "+item.currency);

        return row;
    }

    static class CountryHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtCurrency;
    }

}

