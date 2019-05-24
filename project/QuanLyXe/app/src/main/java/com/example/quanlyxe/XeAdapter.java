package com.example.quanlyxe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class XeAdapter extends BaseAdapter {
    private XeAct context;
    private int layout;
    private List<Xe> XeList;
    public XeAdapter(XeAct context, int layout, List<Xe> xeList) {
        this.context = context;
        this.layout = layout;
        XeList = xeList;
    }
    @Override
    public int getCount() {
        return XeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        ImageView imgxeAvartar;
        TextView txtTenXe, txtXuatXu;
        Button btnxeSua, btnxeXoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        XeAdapter.ViewHolder holder;
        if(view == null){
            holder = new XeAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.imgxeAvartar = (ImageView) view.findViewById(R.id.imgxeAvartar);
            //holder.txtMaKH = (TextView) view.findViewById(R.id.txtMaKH);
            holder.txtTenXe = (TextView) view.findViewById(R.id.txtTenXe);
            holder.txtXuatXu = (TextView) view.findViewById(R.id.txtXuatXu);
            holder.btnxeSua = (Button) view.findViewById(R.id.btnxeSua);
            holder.btnxeXoa = (Button) view.findViewById(R.id.btnxeXoa);
            view.setTag(holder);
        }else {
            holder = (XeAdapter.ViewHolder) view.getTag();
        }
        final Xe xe = XeList.get(i);
        holder.txtTenXe.setText(xe.getTENXE());
        holder.txtXuatXu.setText(xe.getXUATXU());
        //bat su kien Sua & Xoa
        holder.btnxeSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSuaXe(xe.getTENXE(), xe.getXUATXU(),xe.getMAXE());

            }
        });
        //Xoa
        holder.btnxeXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaXe(xe.getTENXE(),xe.getMAXE());

            }
        });
        return view;
    }
}
