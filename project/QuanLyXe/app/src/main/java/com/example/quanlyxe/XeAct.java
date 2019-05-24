package com.example.quanlyxe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class XeAct extends AppCompatActivity {
    Database database;
    ListView lvXe;
    ArrayList<Xe> arrayXe;
    XeAdapter adapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xe);
        lvXe = (ListView) findViewById(R.id.listveiwXe);
        arrayXe = new ArrayList<>();
        adapter = new XeAdapter(XeAct.this, R.layout.listview_row_xe,arrayXe);
        lvXe.setAdapter(adapter);
        //tao database
        database = new Database(this,"quanlyxe.sqlite", null,1);
        //tao bang
        database.QueryData("CREATE TABLE IF NOT EXISTS KHACHHANG(MAKH INTEGER PRIMARY KEY AUTOINCREMENT, TENKH VARCHAR(100), DIACHI VARCHAR(200))");
        database.QueryData("CREATE TABLE IF NOT EXISTS HOPDONG(SOHD INTEGER PRIMARY KEY AUTOINCREMENT, NGAYHD DATE, MAKH INTERGER)");
        database.QueryData("CREATE TABLE IF NOT EXISTS CHITIETHOPDONG(SOHD INTEGER PRIMARY KEY AUTOINCREMENT, MAXE INTERGER, SONGAYTHUE INTERGER, GIATHUE REAL)");
        database.QueryData("CREATE TABLE IF NOT EXISTS XE(MAXE INTEGER PRIMARY KEY AUTOINCREMENT, TENXE VARCHAR(100), XUATXU VARCHAR(200))");
        //them data
        //database.QueryData("INSERT INTO XE VALUES(null, 'Vesion', 'VN')");
        GetDataXe();
    }
    public void DialogXoaXe(final String tenxe, final int maxe){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa xe "+tenxe+" không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM XE WHERE MAXE = '"+maxe+"'");
                Toast.makeText(XeAct.this, "Đã xóa " + tenxe, Toast.LENGTH_SHORT).show();
                GetDataXe();

            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
    public void DialogSuaXe(String TenXe, String XuatXu, final int MaXe){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_xe);
        ImageView imgSxeAvartar = (ImageView) dialog.findViewById(R.id.imgSxeAvartar);
        Button btnSxeChupHinh = (Button) dialog.findViewById(R.id.btnSxeChupHinh);
        Button btnSxeChonHinh = (Button) dialog.findViewById(R.id.btnSxeChonHinh);
        Button btnxeXacNhan = (Button) dialog.findViewById(R.id.btnxeXacNhan);
        Button btnSxeHuy = (Button) dialog.findViewById(R.id.btnSxeHuy);
        final EditText edtSTenXe = (EditText) dialog.findViewById(R.id.edtSTenXe);
        final EditText edtSXuatXu = (EditText) dialog.findViewById(R.id.edtSXuatXu);
        //
        edtSTenXe.setText(TenXe);
        edtSXuatXu.setText(XuatXu);

        btnxeXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = edtSTenXe.getText().toString().trim();
                String xuatxuMoi = edtSXuatXu.getText().toString().trim();
                database.QueryData("UPDATE XE SET TENXE = '"+tenMoi+"', XUATXU ='"+xuatxuMoi+"' WHERE MAXE ='"+MaXe+"'");
                Toast.makeText(XeAct.this, "Đã cập nhật!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataXe();
            }
        });

        btnSxeHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void GetDataXe(){
        Cursor dataXe = database.GetData("SELECT * FROM XE");
        arrayXe.clear();
        while (dataXe.moveToNext()){
            String TENXE = dataXe.getString(1);
            int MAXE = dataXe.getInt(0);
            String XUATXU = dataXe.getString(2);
            arrayXe.add(new Xe(MAXE,TENXE,XUATXU));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_xe,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuxeAdd){
            DialogThemXe();
        }
        return super.onOptionsItemSelected(item);
    }
    private void DialogThemXe(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_xe);
        ImageView imgxeAvartar = (ImageView) dialog.findViewById(R.id.imgxeAvartar);
        Button btnxeChupHinh = (Button) dialog.findViewById(R.id.btnxeChupHinh);
        Button btnxeChonHinh = (Button) dialog.findViewById(R.id.btnxeChonHinh);
        Button btnxeThem = (Button) dialog.findViewById(R.id.btnxeThem);
        Button btnxeHuy = (Button) dialog.findViewById(R.id.btnxeHuy);
        final EditText edtTenXe = (EditText) dialog.findViewById(R.id.edtTenXe);
        final EditText edtXuatXu = (EditText) dialog.findViewById(R.id.edtXuatXu);
        //
        btnxeThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenXe = edtTenXe.getText().toString();
                String XuatXu = edtXuatXu.getText().toString();
                if(TenXe.equals("") || XuatXu.equals("")){
                    Toast.makeText(XeAct.this, "Vui lòng không để trống Tên Xe hoặc Xuất xứ", Toast.LENGTH_SHORT).show();
                }else {
                    database.QueryData("INSERT INTO XE VALUES(null, '"+TenXe+"', '"+XuatXu+"')");
                    Toast.makeText(XeAct.this, "Đã Thêm!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataXe();
                }
            }
        });
        btnxeHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });


        dialog.show();
    }
}
