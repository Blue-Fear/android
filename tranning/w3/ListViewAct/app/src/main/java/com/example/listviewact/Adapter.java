package com.example.listviewact;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<SocialNetwork> {
Context context;
int layoutResourceId;
ArrayList<SocialNetwork> data = null;

    public Adapter(Context context, int layoutResourceId, ArrayList<SocialNetwork> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        SocialNetWorkHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new SocialNetWorkHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            row.setTag(holder);
        }
        else
        {
            holder = (SocialNetWorkHolder)row.getTag();
        }

        SocialNetwork item = data.get(position);
        holder.txtTitle.setText(item.title);
        holder.imgIcon.setImageResource(item.icon);

        return row;
    }
    static  class SocialNetWorkHolder{
        ImageView imgIcon;
        TextView txtTitle;
    }
}
