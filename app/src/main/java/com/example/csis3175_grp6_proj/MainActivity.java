package com.example.csis3175_grp6_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    public static final String SHARED_PREFS = "shared_prefs";

    SharedPreferences sharedPreferences;
    String userId, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("USER_ID", null);
        // uncomment the line below can bypass the login for debug
//        userId = "abc";
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent;
        if (userId != null ) {
            intent = new Intent(MainActivity.this, HomeActivity.class);
        }
        else {
            intent = new Intent(MainActivity.this, LogIn.class);
        }
        startActivity(intent);
    }





}