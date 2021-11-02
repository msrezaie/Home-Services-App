package com.example.finalbuild_dec20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OrderForm extends AppCompatActivity {

    TextView Cfname, Cphnum, Chomea, Ccate, Cserve;
    EditText ReqDetail;

    Button submit;

    databaseController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        db = new databaseController(this);

        Cfname = findViewById(R.id.cliFuName);
        Cphnum = findViewById(R.id.cliPNum);
        Chomea = findViewById(R.id.cliHAdd);
        Ccate = findViewById(R.id.cliCat);
        Cserve = findViewById(R.id.clientExtra);

        ReqDetail = findViewById(R.id.userReq);

        submit = findViewById(R.id.submitReq);

        String fname = getIntent().getStringExtra("clientname");

        Cursor res = db.getClientInfo(fname);
        while (res.moveToNext()) {
            Cfname.setText(res.getString(3));
            Cphnum.setText(res.getString(4));
            Chomea.setText(res.getString(5));
            Cserve.setText(res.getString(6));
            Ccate.setText(res.getString(7));
        }

        final String CliFname = Cfname.getText().toString();
        final String CliPhone = Cphnum.getText().toString();
        final String CliHome = Chomea.getText().toString();
        final String CliCate = Ccate.getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String udetail = ReqDetail.getText().toString();

                String userName = getIntent().getStringExtra("name");

                Cursor res1 = db.getUserData(userName);

                String usrFname = "";
                String usrPhone = "";
                String usrHome = "";

                while (res1.moveToNext()) {
                    usrFname = res1.getString(3);
                    usrPhone = res1.getString(4);
                    usrHome = res1.getString(5);
                }

                String userType = getIntent().getStringExtra("userT");

                if (udetail.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please state your order detail first!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = db.insertRequestForm(CliFname, CliPhone, CliHome, CliCate, usrFname, usrPhone, usrHome, udetail);
                    if (check) {
                        Toast.makeText(getApplicationContext(), "Received, Please Wait While We Process Your Order, Thank You!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), OrderStatus.class);
                        intent.putExtra("type", userType);
                        intent.putExtra("clintN", CliFname);
                        intent.putExtra("clintP", CliPhone);
                        intent.putExtra("clintH", CliHome);
                        intent.putExtra("clintC", CliCate);
                        intent.putExtra("userN", usrFname);
                        intent.putExtra("userP", usrPhone);
                        intent.putExtra("userH", usrHome);
                        intent.putExtra("userD", udetail);
                        intent.putExtra("visible", true);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "no good!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}