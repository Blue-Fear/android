package com.example.quanlyxe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Database database;
    ListView lvKhachHang;
    ArrayList<KhachHang> arrayKhachHang;
    KhachHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tao database
        database = new Database(this,"quanlyxe.sqlite", null,1);
        //tao bang
        database.QueryData("CREATE TABLE IF NOT EXISTS KHACHHANG(MAKH INTEGER PRIMARY KEY AUTOINCREMENT, TENKH VARCHAR(100), DIACHI VARCHAR(200))");
        database.QueryData("CREATE TABLE IF NOT EXISTS HOPDONG(SOHD INTEGER PRIMARY KEY AUTOINCREMENT, NGAYHD DATE, MAKH INTERGER)");
        database.QueryData("CREATE TABLE IF NOT EXISTS CHITIETHOPDONG(SOHD INTEGER PRIMARY KEY AUTOINCREMENT, MAXE INTERGER, SONGAYTHUE INTERGER, GIATHUE REAL)");
        database.QueryData("CREATE TABLE IF NOT EXISTS XE(MAXE INTEGER PRIMARY KEY AUTOINCREMENT, TENXE VARCHAR(100), XUATXU VARCHAR(200))");
        //them data
       //database.QueryData("INSERT INTO KHACHHANG VALUES(null, 'Phat', 'Quan 5')");
        setCT();
    }
    public void setCT(){
        Button btnKH = findViewById(R.id.btnKhachHang);
        Button btnXe = findViewById(R.id.btnXe);
        //
        btnKH.setOnClickListener(this);
        btnXe.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnKhachHang:
                Toast.makeText(this, "Khách hàng", Toast.LENGTH_SHORT).show();
                Intent KHIntent = new Intent(this,KhachHangAct.class);
                startActivity(KHIntent);
                break;
            case R.id.btnXe:
                Toast.makeText(this, "Xe", Toast.LENGTH_SHORT).show();
                Intent XeIntent = new Intent(this,XeAct.class);
                startActivity(XeIntent);
                break;
        }
    }

}
