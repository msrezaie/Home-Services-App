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
import android.widget.Toast;

import java.util.ArrayList;

public class ClientServiceListPage extends AppCompatActivity {

    databaseController db;

    ListView cList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_service_list_page);

        cList = findViewById(R.id.clientList);

        db = new databaseController(this);

        String categoryV = getIntent().getStringExtra("clientname");

        ArrayList<String> clientsList = new ArrayList<>();

        Cursor list_I = db.getClientNameData(categoryV);

        if (list_I.getCount() == 0) {
            ;
        } else {
            while (list_I.moveToNext()) {
                clientsList.add(list_I.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientsList);
                cList.setAdapter(listAdapter);

            }
        }

        cList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String userType = getIntent().getStringExtra("usrtype");
                String userName = getIntent().getStringExtra("usrname");
                String user = cList.getItemAtPosition(i).toString();
                Intent intent = new Intent(getApplicationContext(), OrderForm.class);
                intent.putExtra("clientname", user);
                intent.putExtra("name", userName);
                intent.putExtra("userT", userType);
                startActivity(intent);
            }
        });
    }
}