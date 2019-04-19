package com.example.new7wonders;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Wonder> {
    Context context;
    int layoutResourceId;
    ArrayList<Wonder> data = null;

    public Adapter(Context context, int layoutResourceId, ArrayList<Wonder> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WonderHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new WonderHolder();
            holder.imgPic = (ImageView)row.findViewById(R.id.imgPic);
            holder.imgFlag = (ImageView) row.findViewById(R.id.imgFlag);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.txtDate = (TextView) row.findViewById(R.id.txtDate);
            row.setTag(holder);
        } else {
            holder = (WonderHolder) row.getTag();
        }

        Wonder item = data.get(position);
        holder.imgPic.setImageResource(item.image);
        holder.txtTitle.setText(item.wonder);
        holder.txtDate.setText(item.date);
        holder.imgFlag.setImageResource(item.flag);


        return row;
    }

    static class WonderHolder {
        ImageView imgPic;
        ImageView imgFlag;
        TextView txtTitle;
        TextView txtDate;
    }

}
