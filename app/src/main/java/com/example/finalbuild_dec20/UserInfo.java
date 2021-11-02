package com.example.finalbuild_dec20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserInfo extends AppCompatActivity {

    TextView fName, pNum, hAdd;
    ListView orderList;

    databaseController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        db = new databaseController(this);

        fName = findViewById(R.id.uName);
        pNum = findViewById(R.id.uNum);
        hAdd = findViewById(R.id.uAdd);

        orderList = findViewById(R.id.userOrders);

        String name = getIntent().getStringExtra("user");

        Cursor res = db.getUserInfo(name);

        while (res.moveToNext()) {
            fName.setText(res.getString(3));
            pNum.setText(res.getString(4));
            hAdd.setText(res.getString(5));
        }

        ArrayList<String> usrOlist = new ArrayList<>();
        final ArrayList<Integer> shadow = new ArrayList<>();

        Cursor res1 = db.getOrderFromName(name);

        while (res1.moveToNext()) {
            shadow.add(res1.getInt(0));
            usrOlist.add(res1.getString(1));
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usrOlist);
            orderList.setAdapter(listAdapter);
        }

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int value = shadow.get(i);
                Intent intent = new Intent(getApplicationContext(), OrderStatus.class);
                intent.putExtra("orderValue", value);
                intent.putExtra("fromAdmin", true);
                startActivity(intent);
            }
        });
    }
}