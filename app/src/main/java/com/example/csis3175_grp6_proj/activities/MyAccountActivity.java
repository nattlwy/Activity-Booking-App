package com.example.csis3175_grp6_proj.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyAccountActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    TextView txtViewFullName;

    TextView txtViewEmailInfo;

    TextView txtViewBeActivePassHolder;

    TextView txtViewPassNumInfo;

    Button btnLogOut;

    LeisureLinkDatabase appdb;
    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences;

    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("USER_ID", null);

        Log.d("myaccount", userId + "");

        txtViewBeActivePassHolder = findViewById(R.id.txtViewBeActivePassYesOrNo);
        txtViewEmailInfo = findViewById(R.id.txtViewEmailInfo);
        txtViewFullName = findViewById(R.id.txtViewUsername);
        txtViewPassNumInfo = findViewById(R.id.txtViewBeActivePassNumber);

        appdb = Room.databaseBuilder(getApplicationContext(),
                LeisureLinkDatabase.class,"leisurelink.db").build();

        ExecutorService executorService
                = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<User> UsersFromDB = appdb.userDao().GetAllUsers();
                Log.d("myaccount", UsersFromDB.size() + " users in db");
            }
        });


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(this);

        btnLogOut = findViewById(R.id.btnLogOut);

        btnLogOut.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // destroy shared preference data
                sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("USER_ID").apply();
                startActivity(new Intent(MyAccountActivity.this, LogIn.class));

            }
        }));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent myIntent;
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            overridePendingTransition(0, 0);

            return true;
        }
        else if (itemId == R.id.mybooking) {
            myIntent = new Intent(this, MyBookingActivity.class);
            startActivity(myIntent);
            overridePendingTransition(0, 0);

            return true;
        } else if (itemId == R.id.profile) {
            return true;
        }
        return false;
    }
}