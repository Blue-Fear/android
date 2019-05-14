package com.example.eatstime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeAct extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setCT();

    }
    public void setCT(){
        Button btn1 = findViewById(R.id.btnFood);
        Button btn2 = findViewById(R.id.btnDrink);
        Button btn3 = findViewById(R.id.btnDesert);
        Button btn4 = findViewById(R.id.btnReser);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFood:
                Toast.makeText(this, "Food clicked",Toast.LENGTH_SHORT).show();
                Intent FoodIntent = new Intent(this, FoodAct.class);
                startActivity(FoodIntent);
                break;
            case R.id.btnDrink:
                Toast.makeText(this, "Drink clicked",Toast.LENGTH_SHORT).show();
                Intent DrinkIntent = new Intent(this, DrinkAct.class);
                startActivity(DrinkIntent);
                break;
            case R.id.btnDesert:
                Toast.makeText(this, "Desert clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnReser:
                Toast.makeText(this, "Reservation clicked",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
