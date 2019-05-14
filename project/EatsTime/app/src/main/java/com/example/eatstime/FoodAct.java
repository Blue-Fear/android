package com.example.eatstime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodAct extends AppCompatActivity {
    private ListView lv_food;
    ArrayList<Food> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_food);
        setCtFood();
        setEvFood();
    }
    public void setCtFood(){
        lv_food = findViewById(R.id.lv_food);
    }
    public void setEvFood(){
        khoiTao();
        FoodAdapt adapter = new FoodAdapt(this,R.layout.list_item_layout,data);
        lv_food.setAdapter(adapter);
    }
    void khoiTao(){
        data.add(new Food(R.drawable.piza,"Pizza","85000"));
        data.add(new Food(R.drawable.hamburg,"Hamburger","35000"));
        data.add(new Food(R.drawable.ga,"Chicken","65000"));
        data.add(new Food(R.drawable.soup,"Soup","25000"));
    }
}
