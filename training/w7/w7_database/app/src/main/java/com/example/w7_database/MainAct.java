package com.example.w7_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainAct extends AppCompatActivity implements View.OnClickListener{
    ListView lv_mh;
    ArrayList<Monhoc> data = new ArrayList<>();
    MonhocAdapt adapt = null;
    Button btnInsert, btnLoad;
    private EditText edtTen, edtMa, edtTiet;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setCt();
        setEv();
        btnInsert.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
    }

    public void setCt(){
        lv_mh = findViewById(R.id.lview);
        btnInsert = findViewById(R.id.btnInsert);
        btnLoad = findViewById(R.id.btnLoad);
        edtTen = findViewById(R.id.edTen);
        edtMa = findViewById(R.id.edMa);
        edtTiet = findViewById(R.id.edTiet);
    }

    public void setEv(){
        adapt = new MonhocAdapt(this, R.layout.item_layout, data);
        lv_mh.setAdapter(adapt);
    }

//    public void InsertMH(){
//        saveDB();
//        Toast.makeText(this,"Da Them Mon Hoc",Toast.LENGTH_SHORT).show();
 //   }
    public void LoadMH(){
        loadDB();
        Toast.makeText(this,"Load Mon Hoc",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsert:
                MyDatabase db = new MyDatabase(this);
                Monhoc mh = insertMH();
                if(mh!=null){
                    db.insertMonhocs(mh);
                }

                break;
            case R.id.btnLoad:
                LoadMH();
                break;
        }
    }

//    public void saveDB(){
//        MyDatabase db = new MyDatabase(this);
//        Monhoc monhoc = new Monhoc();
//        monhoc.setmTen(edtTen.getText().toString());
//        monhoc.setmMa(edtMa.getText().toString());
//        monhoc.setmTiet(edtTiet.getText().toString());
//        db.insertMonhocs(monhoc);
//    }
    private Monhoc insertMH(){
        String name = edtTen.getText().toString();
        String ma = edtMa.getText().toString();
        String tiet = edtTiet.getText().toString();
        //da co nhung thuoc tinh cua mh > khoi tao doi tuong mh
        Monhoc mh = new Monhoc(name,ma,tiet);
        return mh;
    }

    public void loadDB(){
        MyDatabase db = new MyDatabase(this);
        data.clear();
        db.getMonHocS(data);
        adapt.notifyDataSetChanged();

    }
}
