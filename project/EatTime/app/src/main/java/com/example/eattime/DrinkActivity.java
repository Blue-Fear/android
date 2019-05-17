package com.example.eattime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.eattime.Adapter.FoodAdapter;
import com.example.eattime.Model.Food;
import com.example.eattime.R;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {
    private ListView lv_drink;
    ArrayList<Food> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        setCtDrink();
        setEvDrink();
    }
    public void setCtDrink(){
        lv_drink = findViewById(R.id.lv_food);
    }
    public void setEvDrink(){
        khoiTao();
        FoodAdapter adapter = new FoodAdapter(this,R.layout.list_item_layout,data);
        lv_drink.setAdapter(adapter);
    }
    void khoiTao(){
        data.add(new Food(R.drawable.kiwi,"Nước Ép Kiwi ","25000"));
        data.add(new Food(R.drawable.dao,"Nước Ép Đào","25000"));
        data.add(new Food(R.drawable.lemon,"Nước Lemon","15000"));
        data.add(new Food(R.drawable.dau,"Nước Ép Dâu","20000"));
    }
}
