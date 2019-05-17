package com.example.eattime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.eattime.Adapter.FoodAdapter;
import com.example.eattime.Model.Food;
import com.example.eattime.R;

import java.util.ArrayList;

public class DessertActivity extends AppCompatActivity {
    private ListView lv_drink;
    ArrayList<Food> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        setCtDessert();
        setEvDessert();
    }
    public void setCtDessert(){
        lv_drink = findViewById(R.id.lv_food);
    }
    public void setEvDessert(){
        khoiTao();
        FoodAdapter adapter = new FoodAdapter(this,R.layout.list_item_layout,data);
        lv_drink.setAdapter(adapter);
    }
    void khoiTao(){
        data.add(new Food(R.drawable.dessser1,"Bánh bông lan socola kem dâu ","29000"));
        data.add(new Food(R.drawable.dessert2,"Donut","10000"));
        data.add(new Food(R.drawable.dessert3,"Bánh kem socola","15000"));
        data.add(new Food(R.drawable.dessert4,"Bánh Nazes","5000"));
        data.add(new Food(R.drawable.dessert5,"Bánh Ốc","10000"));
        data.add(new Food(R.drawable.dessert6,"Bánh trứng cafe","20000"));
    }
}
