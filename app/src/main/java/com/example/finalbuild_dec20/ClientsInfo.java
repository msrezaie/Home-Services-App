package com.example.finalbuild_dec20;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ClientsInfo extends AppCompatActivity {

    TextView cName, cPhone, cHome, cServeC, cExtra, uReviewRate, cNofReq;

    databaseController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_info);

        cName = findViewById(R.id.cfname);
        cPhone = findViewById(R.id.cphone);
        cHome = findViewById(R.id.chomadd);
        cServeC = findViewById(R.id.cscate);
        cExtra = findViewById(R.id.clientExtra);
        cNofReq = findViewById(R.id.numofRate);

        db = new databaseController(this);

        String name = getIntent().getStringExtra("client");

        Cursor res = db.getClientInfo(name);

        while (res.moveToNext()) {
            cName.setText(res.getString(3));
            cPhone.setText(res.getString(4));
            cHome.setText(res.getString(5));
            cServeC.setText(res.getString(7));
            cExtra.setText(res.getString(6));
        }

        Cursor res1 = db.getClientOrders(name);

        ArrayList<String> total = new ArrayList<>();

        while (res1.moveToNext()) {
            total.add(res1.getString(1));
        }
        Integer totalV = total.size();

        cNofReq.setText(String.valueOf(totalV));
    }
}