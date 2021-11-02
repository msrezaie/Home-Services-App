package com.example.finalbuild_dec20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    databaseController db;

    Button loginBtn, U_SignBtn, C_SignBtn;
    EditText l_Uname, l_Passw;
    RadioGroup accGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        db = new databaseController(this);

        accGroup = findViewById(R.id.accountsG);

        loginBtn = findViewById(R.id.btnLogin);
        U_SignBtn = findViewById(R.id.userSignUp);
        C_SignBtn = findViewById(R.id.clientSignUp);
        l_Uname = findViewById(R.id.userName);
        l_Passw = findViewById(R.id.passWord);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = l_Uname.getText().toString();
                String pass = l_Passw.getText().toString();

                RadioButton checkGroup = findViewById(accGroup.getCheckedRadioButtonId());

                String userType = checkGroup.getText().toString();

                if (user.equals("")||pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill both Username and Password!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check1 = db.loginCheckUsr(user, pass);
                    boolean check2 = db.loginCheckCli(user, pass);
                    if (user.equals("admin") & pass.equals("admin") & userType.equals("Admin")) {
                        Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                        startActivity(intent);
                    } else if (userType.equals("User") & check1 == true) {
                        Intent intent = new Intent(getApplicationContext(), UserPage.class);
                        intent.putExtra("usersname", user);
                        intent.putExtra("usertype", userType);
                        startActivity(intent);
                    } else if (userType.equals("Client") & check2 == true) {
                        Intent intent = new Intent(getApplicationContext(), ClientPage.class);
                        intent.putExtra("clientUn", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Credentials, Please Select the Appropriate User Type", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        U_SignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserSignUp.class);
                startActivity(intent);
            }
        });

        C_SignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClientSignUp.class);
                startActivity(intent);
            }
        });

    }
}