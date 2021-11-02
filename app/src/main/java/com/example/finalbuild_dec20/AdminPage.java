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

import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {

    databaseController db;

    ListView usersL, clientsL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        final ListView usersL = findViewById(R.id.userList);
        final ListView clientL = findViewById(R.id.clientsList);

        db = new databaseController(this);

        ArrayList<String> listU = new ArrayList<>();
        ArrayList<String> listC = new ArrayList<>();

        Cursor getUList = db.getDataForUList();
        Cursor getCList = db.getDataForCList();

        if (getUList.getCount() == 0) {
            ;
        } else {
            while (getUList.moveToNext()) {
                listU.add(getUList.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listU);
                usersL.setAdapter(listAdapter);

                usersL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String user = usersL.getItemAtPosition(i).toString();
                        Intent bridgeActivity = new Intent(getApplicationContext(), UserInfo.class);
                        startActivity(bridgeActivity);
                    }
                });
            }
        }

        if (getCList.getCount() == 0) {
            ;
        } else {
            while (getCList.moveToNext()) {
                listC.add(getCList.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listC);
                clientL.setAdapter(listAdapter);

                clientL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String user = clientL.getItemAtPosition(i).toString();
                        Intent bridgeActivity = new Intent(getApplicationContext(), ClientsInfo .class);
                        startActivity(bridgeActivity);
                    }
                });
            }
        }

        usersL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clicked = usersL.getItemAtPosition(i).toString();
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                intent.putExtra("user", clicked);
                startActivity(intent);
            }
        });

        clientL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clicked = clientL.getItemAtPosition(i).toString();
                Intent intent = new Intent(getApplicationContext(), ClientsInfo.class);
                intent.putExtra("client", clicked);
                startActivity(intent);
            }
        });
    }
}