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


public class UserPage extends AppCompatActivity {

    private String [] mylist = {"House Cleaner","Electrician","Carpenter","Cook","Handyman","House Setter","Car Mechanic"};
    ListView listcat, usrOList;

    databaseController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        listcat = findViewById(R.id.categoryList);
        usrOList = findViewById(R.id.ordersList);

        ArrayAdapter<String> myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        listcat.setAdapter(myadapter);

        db = new databaseController(this);

        final String userName = getIntent().getStringExtra("usersname");

        listcat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    String userType = getIntent().getStringExtra("usertype");
                    String user = listcat.getItemAtPosition(position).toString();
                    Intent intent = new Intent(getApplicationContext(), ClientServiceListPage.class);
                    intent.putExtra("clientname", user);
                    intent.putExtra("usrname", userName);
                    intent.putExtra("usrtype", userType);
                    startActivity(intent);

                } else if(position==1){
                    String userType = getIntent().getStringExtra("usertype");
                    String user = listcat.getItemAtPosition(position).toString();
                    Intent intent = new Intent(getApplicationContext(), ClientServiceListPage.class);
                    intent.putExtra("clientname", user);
                    intent.putExtra("usrname", userName);
                    intent.putExtra("usrtype", userType);

                    startActivity(intent);

                }else if(position==2){
                    String userType = getIntent().getStringExtra("usertype");
                    String user = listcat.getItemAtPosition(position).toString();
                    Intent intent = new Intent(getApplicationContext(), ClientServiceListPage.class);
                    intent.putExtra("clientname", user);
                    intent.putExtra("usrname", userName);
                    intent.putExtra("usrtype", userType);

                    startActivity(intent);

                }else if(position==3){
                    String userType = getIntent().getStringExtra("usertype");
                    String user = listcat.getItemAtPosition(position).toString();
                    Intent intent = new Intent(getApplicationContext(), ClientServiceListPage.class);
                    intent.putExtra("clientname", user);
                    intent.putExtra("usrname", userName);
                    intent.putExtra("usrtype", userType);

                    startActivity(intent);

                }else if(position==4){
                    String userType = getIntent().getStringExtra("usertype");
                    String user = listcat.getItemAtPosition(position).toString();
                    Intent intent = new Intent(getApplicationContext(), ClientServiceListPage.class);
                    intent.putExtra("clientname", user);
                    intent.putExtra("usrname", userName);
                    intent.putExtra("usrtype", userType);
                    startActivity(intent);


                }else if(position==5){
                    String userType = getIntent().getStringExtra("usertype");
                    String user = listcat.getItemAtPosition(position).toString();
                    Intent intent = new Intent(getApplicationContext(), ClientServiceListPage.class);
                    intent.putExtra("clientname", user);
                    intent.putExtra("usrname", userName);
                    intent.putExtra("usrtype", userType);
                    startActivity(intent);

                }else if(position==6){
                    String userType = getIntent().getStringExtra("usertype");
                    String user = listcat.getItemAtPosition(position).toString();
                    Intent intent = new Intent(getApplicationContext(), ClientServiceListPage.class);
                    intent.putExtra("clientname", user);
                    intent.putExtra("usrname", userName);
                    intent.putExtra("usrtype", userType);

                    startActivity(intent);

                }else if(position==7){
                    String userType = getIntent().getStringExtra("usertype");
                    String user = listcat.getItemAtPosition(position).toString();
                    Intent intent = new Intent(getApplicationContext(), ClientServiceListPage.class);
                    intent.putExtra("clientname", user);
                    intent.putExtra("usrname", userName);
                    intent.putExtra("usrtype", userType);

                    startActivity(intent);
                }
            }
        });

        Cursor res = db.getUserData(userName);
        String value = "";
        while (res.moveToNext()) {
            value = res.getString(3);
        }

        ArrayList<String> mylist = new ArrayList<>();
        final ArrayList<Integer> shadow = new ArrayList<>();

        Cursor res1 = db.getOrderFromName(value);

        if (res1.getCount() == 0) {
            ;
        } else {
            while (res1.moveToNext()) {
                shadow.add(res1.getInt(0));
                mylist.add(res1.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
                usrOList.setAdapter(listAdapter);
            }
        }

        usrOList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int value = shadow.get(position);
                Intent intent = new Intent(getApplicationContext(), OrderStatus.class);
                intent.putExtra("orderValue", value);
                intent.putExtra("fromUser", true);
                startActivity(intent);
            }
        });
    }
}