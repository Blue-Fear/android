package com.example.listviewact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView1;
    ArrayList<SocialNetwork> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setControl();
        setEvent();
    }

    private void setControl() {
        listView1 = findViewById(R.id.listView1);
    }

    private void setEvent() {
        Khoitao();
        Adapter adapter = new Adapter(this, R.layout.listview_item_row, data);
        listView1.setAdapter(adapter);
    }

    void Khoitao() {
        data.add(new SocialNetwork(R.drawable.iconfinder_facebook_4416093, "Facebook"));
        data.add(new SocialNetwork(R.drawable.iconfinder_twitter_4416099, "Twitter"));
        data.add(new SocialNetwork(R.drawable.iconfinder_insta_4416094, "Instagram"));
    }
}
