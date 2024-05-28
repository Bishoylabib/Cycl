package com.example.cycl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Park extends AppCompatActivity {
    View view;
    TextView data,textView;
    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);
        Intent prev = getIntent();
        int x = 0;
        int seconds =prev.getIntExtra("time",x);
        textView = findViewById(R.id.textView5);
        if(prev.getStringExtra("key").equals("0")){
            textView.setText("Please park the bike in the station.");
        }
        else if(prev.getStringExtra("key").equals("1")){
            textView.setText("Please park the scooter in the station.");
        }
            button = findViewById(R.id.Payment);
            data = findViewById(R.id.data);
            view=findViewById(R.id.view);
            progressBar = findViewById(R.id.progressBar1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (prev.getStringExtra("key").equals("0")){
                        startActivity(new Intent(Park.this,Paymentt.class).putExtra("key","0")
                                .putExtra("time",seconds));
                        finish();

                    }
                    else if (prev.getStringExtra("key").equals("1")){
                        startActivity(new Intent(Park.this,Paymentt.class).putExtra("key","1")
                                .putExtra("time",seconds));
                        finish();

                    }
                }
            });
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE));
            }

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("distance");


            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Integer value = dataSnapshot.getValue(Integer.class);
                    data.setText(value.toString()+"cm");
                    if (value>120){
                        progressBar.setIndeterminateTintMode(PorterDuff.Mode.DST_ATOP);

                    }
                    else if(value<=120 && value>60){
                        progressBar.setIndeterminateTintList(ColorStateList.valueOf(Color.GREEN));
                        progressBar.setIndeterminateTintMode(PorterDuff.Mode.MULTIPLY);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE));
                            data.setText(value.toString()+"cm\nGo Back");
                        }

                    }
                    else if(value<=60 && value>30){
                        progressBar.setIndeterminateTintList(ColorStateList.valueOf(Color.YELLOW));
                        progressBar.setIndeterminateTintMode(PorterDuff.Mode.MULTIPLY);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            data.setText(value.toString()+"cm\nSlowly");
                        }

                    }
                    else if(value<=30){
                        progressBar.setIndeterminateTintList(ColorStateList.valueOf(Color.RED));
                        progressBar.setIndeterminateTintMode(PorterDuff.Mode.MULTIPLY);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                            data.setText(value.toString()+"cm\nSTOP");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    data.setText("Error");

                }
            });


    }
}