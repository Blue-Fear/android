package com.example.format;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edtText;
    private Button btnResult;
    private CheckBox cbBr;
    private CheckBox cbCl;
    private CheckBox cbCent;
    private TextView tvKq;
    String msg = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();

    }

    public void setControl() {
        edtText = findViewById(R.id.edt_text);
        btnResult = findViewById(R.id.btn_result);
        cbBr = findViewById(R.id.cb_br);
        cbCent = findViewById(R.id.cb_cent);
        cbCl = findViewById(R.id.cb_cl);
        tvKq = findViewById(R.id.tv_kq);

    }

    public void setEvent() {
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = String.valueOf(edtText.getText());
                tvKq.setText(msg);
                if (cbBr.isChecked()) {
                    tvKq.setBackgroundColor(-16711936);
                } else {
                    tvKq.setBackgroundColor(16777215);
                }
                if (cbCl.isChecked()) {
                    tvKq.setTextColor(Color.RED);
                } else {
                    tvKq.setTextColor(Color.BLACK);
                }
                if (cbCent.isChecked()) {
                    tvKq.setGravity(Gravity.CENTER);
                } else {
                    tvKq.setGravity(Gravity.LEFT);
                }


            }
        });
    }
/*    public void onChecked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.cb_br:
                if (checked){
                    tvKq.setBackgroundColor(-16711936);
                }else {
                    tvKq.setBackgroundColor(16777215);
                }
                break;
            case R.id.cb_cl:
                if (checked){
                    tvKq.setTextColor(Color.BLUE);
                }else {
                    tvKq.setTextColor(15022416);
                }
            case R.id.cb_cent:
                if (checked){
                    tvKq.setGravity(Gravity.CENTER);
                }
        }
    }*/
}
