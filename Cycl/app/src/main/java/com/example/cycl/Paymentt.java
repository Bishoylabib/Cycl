package com.example.cycl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Paymentt extends AppCompatActivity {
    Button button;
    TextView paymentinfo;
    TextView payy;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentt);
        paymentinfo = findViewById(R.id.paymentinfo);
        payy= findViewById(R.id.pay);
        Intent prev = getIntent();
        button =findViewById(R.id.button9);
        ratingBar =findViewById(R.id.ratingBar2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = ratingBar.getRating();
                if (x>3f && x<=5f)
                {
                    Toast.makeText(Paymentt.this, "Thanks for the rating :)", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(Paymentt.this,Homepage.class));
                finish();
            }
        });
        double fare;
        double min;
        double base;
        double per;
        int x = 0;
        int seconds =prev.getIntExtra("time",x);
        int minutes = (seconds % 3600) / 60;

        if (prev.getStringExtra("key").equals("0")){
            paymentinfo.setText("Base fare           \t                   EGP 3.00 \nMinimum Fare \t                   EGP 5.00\nper minute                           EGP 0.48");
            base = 3.00;
            min = 5.00;
            per= 0.48;
            fare = base+(minutes*per);
            if (fare<=min){
                payy.setText("Please Pay           \t               EGP"+String.valueOf(min)+"0");
            }
            else if(fare>min){
                payy.setText("Please Pay           \t               EGP"+String.valueOf(fare));
            }


        }
        else if (prev.getStringExtra("key").equals("1")){
            paymentinfo.setText("Base fare           \t                   EGP 5.00 \nMinimum Fare \t                   " +
                     "EGP 7.00\nper minute \t                         EGP 0.48");
            base = 5.00;
            min = 7.00;
            per= 0.48;
            fare = base+(minutes*per);
            if (fare<=min){
                payy.setText("Please Pay           \t               EGP"+String.valueOf(min)+"0");
            }
            else if(fare>min){
                payy.setText("Please Pay           \t               EGP"+String.valueOf(fare));
            }

        }

    }
}