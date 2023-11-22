package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Sport;
import com.example.csis3175_grp6_proj.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity  {
    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences;
    String userId;
    LeisureLinkDatabase lldb;
    List<Sport> Sports = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("USER_ID", null);
        Log.d("icyfung", userId + "");

       Sports = ReadSportsCSV();
        lldb = Room.databaseBuilder(getApplicationContext(), LeisureLinkDatabase.class,"leisurelink.db").build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                lldb.sportDao().insertSportsFromList(Sports);
            }
        });

//      uncomment the line below can bypass the login for debug
//      userId = "abc";
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
    private List<Sport> ReadSportsCSV() {
        List<Sport> Sports = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.sports);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String sportLine;

        try {
            if ((sportLine = reader.readLine()) != null) {  }
            while ((sportLine = reader.readLine()) != null) {
                String[] eachSportLine = sportLine.split(",");
                int sportCapacity = Integer.parseInt(eachSportLine[3]);
                Sport eachSport = new Sport(eachSportLine[0], eachSportLine[1], eachSportLine[2], sportCapacity);
                Sports.add(eachSport);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Sports;
    }





}