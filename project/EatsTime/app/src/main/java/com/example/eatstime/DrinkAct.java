package com.example.eatstime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DrinkAct extends AppCompatActivity {
    private ListView lv_drink;
    ArrayList<Food> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_food);
        setCtDrink();
        setEvDrink();
    }
    public void setCtDrink(){
        lv_drink = findViewById(R.id.lv_food);
    }
    public void setEvDrink(){
        khoiTao();
        FoodAdapt adapter = new FoodAdapt(this,R.layout.list_item_layout,data);
        lv_drink.setAdapter(adapter);
    }
    void khoiTao(){
        data.add(new Food(R.drawable.kiwi,"Nước Ép Kiwi ","25000"));
        data.add(new Food(R.drawable.dao,"Nước Ép Đào","25000"));
        data.add(new Food(R.drawable.lemon,"Nước Lemon","15000"));
        data.add(new Food(R.drawable.dau,"Nước Ép Dâu","20000"));
    }
}
