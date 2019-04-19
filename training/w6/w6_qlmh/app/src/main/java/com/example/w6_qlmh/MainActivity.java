package com.example.w6_qlmh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv_mh;
    ArrayList<MonHoc> data = new ArrayList<>();
    MonHocAdapt adapt = null;
    Button btnInsert, btnDelete, btnFind, btnUpdate;
    EditText tenMh, maMh, soTiet;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setCtMH();
        setEvMH();
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        lv_mh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonHoc mh = data.get(position);
                index = position;
                maMh.setText(mh.getMa());
                tenMh.setText(mh.getTen());
                soTiet.setText(mh.getSotiet());
            }
        });

    }

    public void setCtMH() {
        lv_mh = findViewById(R.id.lview);
        tenMh = findViewById(R.id.edTen);
        maMh = findViewById(R.id.edMa);
        soTiet = findViewById(R.id.edTiet);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnFind = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    public void setEvMH() {
        adapt = new MonHocAdapt(this, R.layout.list_item_layout, data);
        lv_mh.setAdapter(adapt);
    }

    private MonHoc getMonHoc() {
        MonHoc monHoc = new MonHoc();
        monHoc.setTen(tenMh.getText().toString());
        monHoc.setMa(maMh.getText().toString());
        monHoc.setSotiet(soTiet.getText().toString());

        return monHoc;
    }

    // Them mon hoc vao listview
    public void InsertMH() {
        MonHoc monHoc = getMonHoc();
        data.add(monHoc);
        Toast.makeText(this, "Đã Thêm Môn Học", Toast.LENGTH_SHORT).show();
        adapt.notifyDataSetChanged();
    }

    // Xoa mon hoc
    public void DeleteMH() {
        if (index >= 0) {
            data.remove(index);
            adapt.notifyDataSetChanged();
            Toast.makeText(this, "Đã Xóa Môn Học", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
    }
    // update mon hoc

    public void UpdateMH() {
        if (index >=0) {
            String ma = maMh.getText().toString();
            String ten = tenMh.getText().toString();
            String st = soTiet.getText().toString();
            data.get(index).setMa(ma);
            data.get(index).setTen(ten);
            data.get(index).setSotiet(st);
            Toast.makeText(this, "Update thanh cong", Toast.LENGTH_SHORT).show();
            adapt.notifyDataSetChanged();
        } else
            Toast.makeText(this, "Update khong thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsert:
                InsertMH();
                break;
            case R.id.btnDelete:
                DeleteMH();

                break;
            case R.id.btnUpdate:
                UpdateMH();
                break;
            case R.id.btnSearch:
                Toast.makeText(this, "Đã Cập Nhật Môn Học", Toast.LENGTH_SHORT).show();
                break;

        }
    }

}
