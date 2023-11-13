package com.example.csis3175_grp6_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyAccountActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent myIntent;
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);

            return true;
        }
        else if (itemId == R.id.mybooking) {
            myIntent = new Intent(this, MyBookingActivity.class);
            startActivity(myIntent);
        }
        return false;
    }
}