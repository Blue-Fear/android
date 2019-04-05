package com.example.country;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    ArrayList<Country> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();

    }
    public void setControl(){
        lv = findViewById(R.id.lv_country);
    }
    public void setEvent(){
        khoiTao();
        Adapter adapter = new Adapter(this,R.layout.list_item_layout,data);
        lv.setAdapter(adapter);
    }

    void khoiTao(){
        data.add(new Country(R.drawable.vn,"Việt Nam","VND"));
        data.add(new Country(R.drawable.halan,"Hà Lan","EURO"));
        data.add(new Country(R.drawable.japan,"Nhật Bản","JPY"));
        data.add(new Country(R.drawable.germany,"Germany","EURO"));
    }


}
