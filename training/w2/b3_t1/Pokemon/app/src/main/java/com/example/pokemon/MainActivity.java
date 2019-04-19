package com.example.pokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    /*private RadioButton rbPika;
    private RadioButton rbEch;
    private RadioButton rbVit;
    private RadioButton rbCoco;*/
    private ImageView imView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        //setEvent(View view);
    }
    public void setControl(){
        imView = findViewById(R.id.img_view);
        /*rbPika = findViewById(R.id.rb_pika);
        rbEch = findViewById(R.id.rb_ech);
        rbVit = findViewById(R.id.rb_vit);
        rbCoco = findViewById(R.id.rb_coco);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int id = radioGroup.getCheckedRadioButtonId();
        if (id == -1){

        }*/

    }
    public void setEvent(View view){
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id){
            case R.id.rb_ech:
                show1();
                break;
            case R.id.rb_pika:
                this.imView.setImageResource(R.drawable.pika);
                break;
            case R.id.rb_vit:
                this.imView.setImageResource(R.drawable.vit);
                break;
            case R.id.rb_coco:
                this.imView.setImageResource(R.drawable.sau);
        }
    }
    public void show1(){
        this.imView.setImageResource(R.drawable.ech);
    }
}
