package com.example.finalbuild_dec20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClientPage extends AppCompatActivity {

    ListView corderL;

    Button cliback;

    databaseController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_page);

        corderL = findViewById(R.id.orderlist);

        db = new databaseController(this);

        final String clun = getIntent().getStringExtra("clientUn");

        Cursor res = db.getClientData(clun);
        String value = "";
        while (res.moveToNext()) {
            value = res.getString(3);
        }

        Cursor res1 = db.getClientOrders(value);

        ArrayList<String> mylist = new ArrayList<>();
        final ArrayList<Integer> shadow = new ArrayList<>();

        if (res1.getCount() == 0) {
            ;
        } else {
            while (res1.moveToNext()) {
                mylist.add(res1.getString(5));
                shadow.add(res1.getInt(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
                corderL.setAdapter(listAdapter);
            }
        }

        corderL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int value = shadow.get(i);
                Intent intent = new Intent(getApplicationContext(), OrderStatus.class);
                intent.putExtra("orderValue", value);
                intent.putExtra("fromClient", true);
                startActivity(intent);



            }
        });
    }
}