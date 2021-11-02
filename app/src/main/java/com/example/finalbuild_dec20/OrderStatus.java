package com.example.finalbuild_dec20;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class OrderStatus extends AppCompatActivity {

    TextView clFname, clPhone, clHome, clCate, usrfname, usrphone, usrHome, usrDetail, clImess, cliOApp, rPerc, rDesc;
    RatingBar preRate;
    RadioGroup cliChoice;
    float ratevalue;
    Button usrSubmit, cliSubmit;
    RelativeLayout cliSide, usrSide;
    EditText cliET;

    databaseController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        db = new databaseController(this);

        clFname = findViewById(R.id.cliFname);
        clPhone = findViewById(R.id.cliPhone);
        clHome = findViewById(R.id.cliHome);
        clCate = findViewById(R.id.cliService);
        usrfname = findViewById(R.id.usrFname);
        usrphone = findViewById(R.id.usrPhone);
        usrHome = findViewById(R.id.usrHome);
        usrDetail = findViewById(R.id.usrClExtra);
        clImess = findViewById(R.id.clientMessage);
        cliOApp = findViewById(R.id.reqStat);

        rPerc = findViewById(R.id.ratePerc);
        rDesc = findViewById(R.id.rateDesc);

        preRate = findViewById(R.id.userRate);

        usrSide = findViewById(R.id.user_side);
        cliSide = findViewById(R.id.client_side);

        cliChoice = findViewById(R.id.choice_group);

        usrSubmit = findViewById(R.id.usr_submitReq);
        cliSubmit = findViewById(R.id.cli_submitReq);

        cliET = findViewById(R.id.cliEMessage);


        boolean firstSub = getIntent().getBooleanExtra("visible", false);

        if (firstSub) {
            String cfname = getIntent().getStringExtra("clintN");
            String cphon = getIntent().getStringExtra("clintP");
            String chaddr = getIntent().getStringExtra("clintH");
            String ccateg = getIntent().getStringExtra("clintC");
            String usrfn = getIntent().getStringExtra("userN");
            String usrph = getIntent().getStringExtra("userP");
            String usrha = getIntent().getStringExtra("userH");
            String usrdr = getIntent().getStringExtra("userD");

            clFname.setText(cfname);
            clPhone.setText(cphon);
            clHome.setText(chaddr);
            clCate.setText(ccateg);
            usrfname.setText(usrfn);
            usrphone.setText(usrph);
            usrHome.setText(usrha);
            usrDetail.setText(usrdr);

            cliSide.setVisibility(View.GONE);
            usrSide.setVisibility(View.GONE);

        }

        boolean fromUser = getIntent().getBooleanExtra("fromUser", false);

        if (fromUser) {
            final Integer value = getIntent().getIntExtra("orderValue", 0);

            Cursor res = db.getOrder(value);
            while (res.moveToNext()) {
                clFname.setText(res.getString(1));
                clPhone.setText(res.getString(2));
                clHome.setText(res.getString(3));
                clCate.setText(res.getString(4));
                usrfname.setText(res.getString(5));
                usrphone.setText(res.getString(6));
                usrHome.setText(res.getString(7));
                usrDetail.setText(res.getString(8));
                clImess.setText(res.getString(9));
                cliOApp.setText(res.getString(10));
                rDesc.setText(res.getString(11));
                rPerc.setText(res.getString(12));
            }
            cliSide.setVisibility(View.GONE);
            usrSide.setVisibility(View.VISIBLE);

            preRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    ratevalue = preRate.getRating();

                    if (ratevalue <= 2.5) {
                        rDesc.setText("Bad");
                    } else {
                        rDesc.setText("Good");
                    }

                    rPerc.setText(ratevalue + "/5");
                }
            });

            usrSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String rateDesc = rDesc.getText().toString();
                    boolean check = db.updateFromUser(value, rPerc.getText().toString(), rateDesc);
                    if (check) {
                        Toast.makeText(getApplicationContext(), "Your Rating will be Sent to the Client.", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                        cliSide.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        boolean fromClient = getIntent().getBooleanExtra("fromClient", false);

        if (fromClient) {

            final Integer value = getIntent().getIntExtra("orderValue", 0);

            Cursor res = db.getOrder(value);
            while (res.moveToNext()) {
                clFname.setText(res.getString(1));
                clPhone.setText(res.getString(2));
                clHome.setText(res.getString(3));
                clCate.setText(res.getString(4));
                usrfname.setText(res.getString(5));
                usrphone.setText(res.getString(6));
                usrHome.setText(res.getString(7));
                usrDetail.setText(res.getString(8));
                clImess.setText(res.getString(9));
                cliOApp.setText(res.getString(10));
                rDesc.setText(res.getString(11));
                rPerc.setText(res.getString(12));

            }
            cliSide.setVisibility(View.VISIBLE);
            usrSide.setVisibility(View.GONE);

            cliSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RadioButton checkGroup = findViewById(cliChoice.getCheckedRadioButtonId());

                    String decision = checkGroup.getText().toString();
                    String message = cliET.getText().toString();

                    boolean check = db.updateFromClient(value, message, decision);
                    if (check) {
                        Toast.makeText(getApplicationContext(), "Your Message will be Sent to the User. ", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                        cliSide.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        boolean fromAdmin = getIntent().getBooleanExtra("fromAdmin", false);

        if (fromAdmin) {
            Integer value = getIntent().getIntExtra("orderValue", 0);

            Cursor res = db.getOrder(value);
            while (res.moveToNext()) {
                clFname.setText(res.getString(1));
                clPhone.setText(res.getString(2));
                clHome.setText(res.getString(3));
                clCate.setText(res.getString(4));
                usrfname.setText(res.getString(5));
                usrphone.setText(res.getString(6));
                usrHome.setText(res.getString(7));
                usrDetail.setText(res.getString(8));
                clImess.setText(res.getString(9));
                cliOApp.setText(res.getString(10));
                rDesc.setText(res.getString(11));
                rPerc.setText(res.getString(12));
            }
            cliSide.setVisibility(View.GONE);
            usrSide.setVisibility(View.GONE);

        }
    }
}