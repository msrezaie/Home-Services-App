package com.example.finalbuild_dec20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserSignUp extends AppCompatActivity {

    databaseController db;

    EditText FName, UName, Passw, Phone, Addre;
    Button usrSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        db = new databaseController(this);

        usrSub = findViewById(R.id.usrSign);

        UName = findViewById(R.id.usrName);
        Passw = findViewById(R.id.usrPassw);
        FName = findViewById(R.id.usrFname);
        Phone = findViewById(R.id.usrPhn);
        Addre = findViewById(R.id.usrAddr);

        usrSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userN = UName.getText().toString();
                String passW = Passw.getText().toString();
                String fnamE = FName.getText().toString();
                String phonE = Phone.getText().toString();
                String addR = Addre.getText().toString();

                if (userN.equals("")||passW.equals("")||fnamE.equals("")||phonE.equals("")||addR.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill out all of the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insCheck = db.insertUsers(userN, passW, fnamE, phonE, addR);
                    if (insCheck) {
                        Toast.makeText(getApplicationContext(), "Sign-up Successful! Please Login to Proceed", Toast.LENGTH_LONG).show();
                        Intent bridgeActivity = new Intent(getApplicationContext(), Welcome.class);
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