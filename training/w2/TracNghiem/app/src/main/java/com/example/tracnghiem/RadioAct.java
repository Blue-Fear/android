package com.example.tracnghiem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RadioAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
    }
    public void onBack(View view){
        Intent intent1 = new Intent(this, SwitchAct.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent1);
    }
    public void onNext(View view){
        Intent intent2 = new Intent(this, CheckBoxAct.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent2);
    }
}
