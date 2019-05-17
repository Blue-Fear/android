package com.example.eattime;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.example.eattime.Adapter.MyViewPagerAdapter;
import com.example.eattime.Common.Common;
import com.example.eattime.Common.NonSwipeViewPager;
import com.example.eattime.Model.Table;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.view_pager)
    NonSwipeViewPager viewPager;
    @BindView(R.id.btn_back_step)
    Button btn_back_step;
    @BindView(R.id.btn_next_step)
    Button btn_next_step;

    LocalBroadcastManager localBroadcastManager;

    //Load tables tu firebase
    AlertDialog dialog;
    CollectionReference tableRef;


    // event
    @OnClick(R.id.btn_back_step)
    void backStep()
    {
        if (Common.step == 3 || Common.step > 0)
        {
            Common.step--;
            viewPager.setCurrentItem(Common.step);
        }
    }
    @OnClick(R.id.btn_next_step)
    void nextClick()
    {
        if (Common.step < 3 || Common.step == 0)
        {
            Common.step++;
            if (Common.step == 1) // sau khi chon nha hang
            {
                if (Common.currentRestaurant != null)
                    loadTable(Common.currentRestaurant.getResId());
            }
            viewPager.setCurrentItem(Common.step);
        }
    }

    // Load Ban tu firebase
    private void loadTable(String resId) {
        dialog.show();
        if (!TextUtils.isEmpty(Common.city))
        {
            ///Restaurant/Quan 9/Branch/26ZXrBd9gZdZweTPjr6G/Tables
            tableRef = FirebaseFirestore.getInstance()
                    .collection("Restaurant")
                    .document(Common.city)
                    .collection("Branch")
                    .document(resId)
                    .collection("Tables");

            tableRef.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            // Tao list table o model
                            ArrayList<Table> tables = new ArrayList<>();
                            for (QueryDocumentSnapshot tableSnapShot:task.getResult())
                            {
                                Table table = tableSnapShot.toObject(Table.class);
                                table.setTableId(tableSnapShot.getId());

                                tables.add(table);
                            }

                            // gui broadcast de book2fragment load recycler
                            Intent intent = new Intent(Common.KEY_TABLE_LOAD_DONE);
                            intent.putParcelableArrayListExtra(Common.KEY_TABLE_LOAD_DONE,tables);
                            localBroadcastManager.sendBroadcast(intent);

                            dialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                }
            });
        }


    }

    // broadcast receiver
    private BroadcastReceiver buttonNextReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Common.currentRestaurant = intent.getParcelableExtra(Common.KEY_RESTAURANT_STORE);
            btn_next_step.setEnabled(true);
            setColorButton();
        }
    };

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(buttonNextReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(BookingActivity.this);

        //load tables
        dialog = new SpotsDialog.Builder().setContext(this).build();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(buttonNextReceiver, new IntentFilter(Common.KEY_ENABLE_BUTTON_NEXT));

        setupStepView();
        setColorButton();

        //View
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(4); // Dat gioi han 4 buoc
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                stepView.go(i,true);

                if (i == 0)
                    btn_back_step.setEnabled(false);
                else
                    btn_back_step.setEnabled(true);
                setColorButton();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setColorButton() {
        if (btn_next_step.isEnabled())
        {
            btn_next_step.setBackgroundResource(R.color.colorButton1);
        }
        else
        {
            btn_next_step.setBackgroundResource(android.R.color.darker_gray);
        }

        if (btn_back_step.isEnabled())
        {
            btn_back_step.setBackgroundResource(R.color.colorButton1);
        }
        else
        {
            btn_back_step.setBackgroundResource(android.R.color.darker_gray);
        }
    }

    private void setupStepView() {
        List<String> stepList = new ArrayList<>();
        stepList.add("Nhà Hàng");
        stepList.add("Bàn");
        stepList.add("Thời Gian");
        stepList.add("Xác Nhận");
        stepView.setSteps(stepList);
    }
}
