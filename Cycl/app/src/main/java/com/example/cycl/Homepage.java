package com.example.cycl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class Homepage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    Button Bike;
    Button Scooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Bike = findViewById(R.id.button);
        Scooter = findViewById(R.id.button5);
        Bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this,MapsActivity.class).putExtra("key","0"));
            }
        });
        Scooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this,MapsActivity.class).putExtra("key","1"));
            }
        });
    }
    public void showPopup (View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.optionmenu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        startActivity(new Intent(Homepage.this,MainActivity.class));
        finish();
        return true;
    }
}