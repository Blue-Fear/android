package com.example.thongtincanhan;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class TTinActi extends AppCompatActivity {
    private EditText edtTen;
    private EditText edtCmnd;
    private EditText edtTtbs;
    private RadioButton rbtDh;
    private RadioButton rbtCd;
    private RadioButton rbtTc;
    private CheckBox cbNhac;
    private CheckBox cbSach;
    private CheckBox cbGame;
    private Button btnGui;
    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttin_acti);
        setControl();
        setEvent();
    }

    public void setControl() {
        edtTen = findViewById(R.id.edt_ten);
//        do{
//            edtTen= findViewById(R.id.edt_ten);
//                   Toast.makeText(TTinActi.this, "Nhap lai >3", Toast.LENGTH_LONG).show();
//            }while (String.valueOf(edtTen.getText()).length()<3);
        edtCmnd = findViewById(R.id.edt_cmnd);
        edtTtbs = findViewById(R.id.edt_ttbs);
        rbtDh = findViewById(R.id.rbt_dh);
        rbtCd = findViewById(R.id.rbt_cd);
        rbtTc = findViewById(R.id.rbt_tc);
        cbNhac = findViewById(R.id.cb_nhac);
        cbSach = findViewById(R.id.cb_sach);
        cbGame = findViewById(R.id.cb_game);
        btnGui = findViewById(R.id.btn_gui);
    }

    public void setEvent() {
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo();




            }
        });


    }
    public void showInfo(){
        String ten = String.valueOf(edtTen.getText());
        ten.trim();
        if (ten.length()<3){
            edtTen.requestFocus();
            edtTen.selectAll();
            Toast.makeText(this,"Ten phai tu 3 ki tu",Toast.LENGTH_LONG).show();
            return;
        }
        String cm = String.valueOf(edtCmnd.getText());
        cm.trim();
        if (cm.length() != 9){
            edtTen.requestFocus();
            edtTen.selectAll();
            Toast.makeText(this,"Phai Nhap du 9 so",Toast.LENGTH_LONG).show();
            return;
        }
        msg = String.valueOf(edtTen.getText()) + "\n";
        msg += String.valueOf(edtCmnd.getText()) + "\n";
        if (rbtDh.isChecked()) {
            msg += rbtDh.getText();
        } else if (rbtCd.isChecked()) {
            msg += rbtCd.getText();
        } else {
            msg += rbtCd.getText();
        }
        msg += "\n-------------\n";
        if (cbNhac.isChecked()) {
            msg += cbNhac.getText() + "\n";
        }
        if (cbSach.isChecked()) {
            msg += cbSach.getText() + "\n";
        }
        if (cbGame.isChecked()) {
            msg += cbGame.getText() + "\n";
        }
        msg += "Thong Tin Bo Sung:\n";
        msg += edtTtbs.getText();
        //Toast.makeText(TTinActi.this, msg, Toast.LENGTH_LONG).show();
        showAlert();
    }
    public void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Tin Cá Nhân");
        builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.show();

    }


}
