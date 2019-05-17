package com.example.eattime.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eattime.Adapter.MyTableAdapter;
import com.example.eattime.Common.Common;
import com.example.eattime.Common.SpacesItemDecoration;
import com.example.eattime.Model.Table;
import com.example.eattime.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Booking2Fragment extends Fragment {

    Unbinder unbinder;
    // listen broacast khi da load xong table
    LocalBroadcastManager localBroadcastManager;
    @BindView(R.id.recycler_table)
    RecyclerView recycler_table;

    private BroadcastReceiver tableDoneReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Table> tableArrayList = intent.getParcelableArrayListExtra(Common.KEY_TABLE_LOAD_DONE);
            // Tao adapter sau
            MyTableAdapter adapter = new MyTableAdapter(getContext(),tableArrayList);
            recycler_table.setAdapter(adapter);
        }
    };

    static Booking2Fragment instance;

    public static Booking2Fragment getInstance() {
        if (instance == null)
            instance = new Booking2Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(tableDoneReceiver, new IntentFilter(Common.KEY_TABLE_LOAD_DONE));
        

    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(tableDoneReceiver);
        super.onDestroy();
    }

    private void initView() {
        recycler_table.setHasFixedSize(true);
        recycler_table.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycler_table.addItemDecoration(new SpacesItemDecoration(4));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking2,container,false);
        unbinder = ButterKnife.bind(this, itemView);
        initView();
        return itemView;
    }
}
