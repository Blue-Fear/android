package com.example.quanlyxe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class KhachHangAdapter extends BaseAdapter {
    private KhachHangAct context;
    private int layout;
    private List<KhachHang> KhachHangList;

    public KhachHangAdapter(KhachHangAct context, int layout, List<KhachHang> khachHangList) {
        this.context = context;
        this.layout = layout;
        KhachHangList = khachHangList;
    }

    @Override
    public int getCount() {
        return KhachHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        ImageView imgAvartar;
        TextView txtTenKH, txtDiachi;
        Button btnSua, btnXoa;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.imgAvartar = (ImageView) view.findViewById(R.id.imgAvartar);
            //holder.txtMaKH = (TextView) view.findViewById(R.id.txtMaKH);
            holder.txtTenKH = (TextView) view.findViewById(R.id.txtTenKH);
            holder.txtDiachi = (TextView) view.findViewById(R.id.txtDiachi);
            holder.btnSua = (Button) view.findViewById(R.id.btnSua);
            holder.btnXoa = (Button) view.findViewById(R.id.btnXoa);
            view.setTag(holder);
            }else {
            holder = (ViewHolder) view.getTag();
        }
        final KhachHang khachHang = KhachHangList.get(i);
        holder.txtTenKH.setText(khachHang.getTENKH());
        holder.txtDiachi.setText(khachHang.getDIACHI());
        //bat su kien Sua & Xoa
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSuaKhachHang(khachHang.getTENKH(), khachHang.getDIACHI(),khachHang.getMAKH());

            }
        });
        //Xoa
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaKhachHang(khachHang.getTENKH(),khachHang.getMAKH());

            }
        });
        return view;
    }
}
