package com.example.quanlyxe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
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

public class KhachHangAct extends AppCompatActivity {
    Database database;
    ListView lvKhachHang;
    ArrayList<KhachHang> arrayKhachHang;
    KhachHangAdapter adapter;
    KhachHangAdapter.ViewHolder viewHolder;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khachhang);
        lvKhachHang = (ListView) findViewById(R.id.listveiwKhachHang);
        arrayKhachHang = new ArrayList<>();
        adapter = new KhachHangAdapter(KhachHangAct.this, R.layout.listview_row,arrayKhachHang);
        lvKhachHang.setAdapter(adapter);
        //tao database
        database = new Database(this,"quanlyxe.sqlite", null,1);
        //tao bang
        database.QueryData("CREATE TABLE IF NOT EXISTS KHACHHANG(MAKH INTEGER PRIMARY KEY AUTOINCREMENT, TENKH VARCHAR(100), DIACHI VARCHAR(200))");
        database.QueryData("CREATE TABLE IF NOT EXISTS HOPDONG(SOHD INTEGER PRIMARY KEY AUTOINCREMENT, NGAYHD DATE, MAKH INTERGER)");
        database.QueryData("CREATE TABLE IF NOT EXISTS CHITIETHOPDONG(SOHD INTEGER PRIMARY KEY AUTOINCREMENT, MAXE INTERGER, SONGAYTHUE INTERGER, GIATHUE REAL)");
        database.QueryData("CREATE TABLE IF NOT EXISTS XE(MAXE INTEGER PRIMARY KEY AUTOINCREMENT, TENXE VARCHAR(100), XUATXU VARCHAR(200))");
        //them data
        //database.QueryData("INSERT INTO KHACHHANG VALUES(null, 'Phat', 'Quan 5')");
        GetDataKhachHang();
    }
    public void DialogXoaKhachHang(final String tenkh, final int makh){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa khách hàng "+tenkh+" không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM KHACHHANG WHERE MAKH = '"+makh+"'");
                Toast.makeText(KhachHangAct.this, "Đã xóa " + tenkh, Toast.LENGTH_SHORT).show();
                GetDataKhachHang();

            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
    public void DialogSuaKhachHang(String TenKH, String Diachi, final int MaKH){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua);
        ImageView imgSAvartar = (ImageView) dialog.findViewById(R.id.imgSAvartar);
        Button btnSChupHinh = (Button) dialog.findViewById(R.id.btnSChupHinh);
        Button btnSChonHinh = (Button) dialog.findViewById(R.id.btnSChonHinh);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.btnXacNhan);
        Button btnSHuy = (Button) dialog.findViewById(R.id.btnSHuy);
        final EditText edtSTenKH = (EditText) dialog.findViewById(R.id.edtSTenKH);
        final EditText edtSDiachi = (EditText) dialog.findViewById(R.id.edtSDiachi);
        //
        edtSTenKH.setText(TenKH);
        edtSDiachi.setText(Diachi);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = edtSTenKH.getText().toString().trim();
                String diaChiMoi = edtSDiachi.getText().toString().trim();
                database.QueryData("UPDATE KHACHHANG SET TENKH = '"+tenMoi+"', DIACHI ='"+diaChiMoi+"' WHERE MAKH ='"+MaKH+"'");
                Toast.makeText(KhachHangAct.this, "Đã cập nhật!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataKhachHang();
            }
        });

        btnSHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void GetDataKhachHang(){
        Cursor dataKhachHang = database.GetData("SELECT * FROM KHACHHANG");
        arrayKhachHang.clear();
        while (dataKhachHang.moveToNext()){
            String TENKH = dataKhachHang.getString(1);
            int MAKH = dataKhachHang.getInt(0);
            String DIACHI = dataKhachHang.getString(2);
            arrayKhachHang.add(new KhachHang(MAKH,TENKH,DIACHI));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_khachhang,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuAdd){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }
    private void DialogThem(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_khach_hang);
        ImageView imgAvartar = (ImageView) dialog.findViewById(R.id.imgAvartar);
        Button btnChupHinh = (Button) dialog.findViewById(R.id.btnChupHinh);
        Button btnChonHinh = (Button) dialog.findViewById(R.id.btnChonHinh);
        Button btnThem = (Button) dialog.findViewById(R.id.btnThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);
        final EditText edtTenKH = (EditText) dialog.findViewById(R.id.edtTenKH);
        final EditText edtDiachi = (EditText) dialog.findViewById(R.id.edtDiachi);
        //Hinh anh
        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);

            }
        });
        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });


        //Them
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenKH = edtTenKH.getText().toString();
                String Diachi = edtDiachi.getText().toString();
                if(TenKH.equals("") || Diachi.equals("")){
                    Toast.makeText(KhachHangAct.this, "Vui lòng không để trống Tên Khách Hàng hoặc Địa chỉ", Toast.LENGTH_SHORT).show();
                }else {
                    database.QueryData("INSERT INTO KHACHHANG VALUES(null, '"+TenKH+"', '"+Diachi+"')");
                    Toast.makeText(KhachHangAct.this, "Đã Thêm!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataKhachHang();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

        dialog.show();
    }
    // Hinh anh them

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && requestCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            viewHolder.imgAvartar.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
