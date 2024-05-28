package com.example.cycl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Ride extends AppCompatActivity {
    private int seconds = 0;
    Button park;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);
        park=findViewById(R.id.button6);
        runTimer();
        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getStringExtra("key").equals("0")){
                    startActivity(new Intent(
                            Ride.this,Park.class).putExtra("key","0").putExtra("time",seconds
                    ));
                    finish();
                }
                else if (getIntent().getStringExtra("key").equals("1")){
                    startActivity(new Intent(
                            Ride.this,Park.class).putExtra("key","1").putExtra("time",seconds
                    ));
                    finish();
                }
            }
        });


    }
    private void runTimer()
    {
        final TextView timeView = (TextView)findViewById(R.id.time_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String
                        .format(Locale.getDefault(),
                                "%02d:%02d:%02d", hours,
                                minutes, secs);
                timeView.setText(time);
                seconds++;
                handler.postDelayed(this, 1000);
            }
        });
    }
}