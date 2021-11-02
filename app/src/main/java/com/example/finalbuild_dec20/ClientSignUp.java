package com.example.finalbuild_dec20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ClientSignUp extends AppCompatActivity {

    EditText userName, passWord, fullName,  phone, homeADD, service;
    Spinner clSpinner;
    Button signup;

    databaseController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);

        db = new databaseController(this);

        userName = findViewById(R.id.cliUname);
        passWord = findViewById(R.id.cliPassw);
        fullName = findViewById(R.id.cliFname);
        phone = findViewById(R.id.cliPhn);
        homeADD = findViewById(R.id.cliAddress);
        service = findViewById(R.id.cliServices);

        clSpinner = findViewById(R.id.cateSpin);
        signup = findViewById(R.id.cliSign);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(ClientSignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Service_Category));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clSpinner.setAdapter(myAdapter);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String spinner_value = clSpinner.getSelectedItem().toString();

                String username = userName.getText().toString();
                String password = passWord.getText().toString();
                String fullname = fullName.getText().toString();
                String phonE = phone.getText().toString();
                String homeAdd = homeADD.getText().toString();
                String servicE = service.getText().toString();

                if (username.equals("")||password.equals("")||fullname.equals("")||phonE.equals("")||homeAdd.equals("")||servicE.equals("")||spinner_value.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill out all of the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insCheck = db.insertClient(username, password, fullname, phonE, homeAdd, servicE, spinner_value);
                    if (insCheck) {
                        Intent bridgeActivity = new Intent(getApplicationContext(), ClientPage.class);
                        startActivity(bridgeActivity);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error: this user already exists!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}