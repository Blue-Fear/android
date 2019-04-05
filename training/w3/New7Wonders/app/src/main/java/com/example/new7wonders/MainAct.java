package com.example.new7wonders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainAct extends AppCompatActivity {
    private ListView lv;
    ArrayList<Wonder> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setControl();
        setEvent();
    }
    public void setControl(){
        lv = findViewById(R.id.lv_wonders);
    }
    public void setEvent(){
        khoiTao();
        Adapter adapter = new Adapter(this,R.layout.list_item_layout,data);
        lv.setAdapter(adapter);
    }

    void khoiTao(){
        data.add(new Wonder(R.drawable.colos,"Đấu Trường La Mã","Năm 80 sau Công Nguyên",R.mipmap.italy));
        data.add(new Wonder(R.drawable.vltt,"Vạn Lý Trường Thành","Thế kỷ thứ 7 trước Công Nguyên",R.mipmap.cina));
        data.add(new Wonder(R.drawable.pyra,"Lăng Mộ Giza","2560 Trước Công Nguyên",R.mipmap.egypt));

    }


}
