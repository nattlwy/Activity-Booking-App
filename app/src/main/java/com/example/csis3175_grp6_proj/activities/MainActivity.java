package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Sport;
import com.example.csis3175_grp6_proj.models.TimeSlot;
import com.example.csis3175_grp6_proj.models.User;
import com.example.csis3175_grp6_proj.models.Venue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
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
    List<Venue> Venues = new ArrayList<>();
    List<TimeSlot> TimeSlots = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("USER_ID", null);
        Log.d("icyfung", userId + "");

        Sports = ReadSportsCSV();
        Venues = ReadVenuesCSV();
        TimeSlots = ReadTimeSlotsCSV();
        lldb = Room.databaseBuilder(getApplicationContext(), LeisureLinkDatabase.class,"leisurelink.db").build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                lldb.sportDao().insertSportsFromList(Sports);
                lldb.venueDao().insertVenuesFromList(Venues);
                lldb.timeSlotDao().insertTimeSlotsFromList(TimeSlots);
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

    private List<Venue> ReadVenuesCSV() {
        List<Venue> Venues = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.venues);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String venueLine;
        try {
            if ((venueLine = reader.readLine()) != null) {

            }
            while ((venueLine = reader.readLine()) != null) {
                String[] eachVenueLine = venueLine.split(",");
                Venue eachVenue = new Venue(eachVenueLine[0], eachVenueLine[1], eachVenueLine[2], eachVenueLine[3]);
                Venues.add(eachVenue);
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
        return Venues;
    }

    private List<TimeSlot> ReadTimeSlotsCSV() {
        List<TimeSlot> TimeSlots = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.timeslots);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String timeSlotLine;
        try {
            if ((timeSlotLine = reader.readLine()) != null) {

            }
            while ((timeSlotLine = reader.readLine()) != null) {
                String[] eachTimeSlotLine = timeSlotLine.split(",");
                int dayOfWeek = Integer.parseInt(eachTimeSlotLine[1]);
                int hour = Integer.parseInt(eachTimeSlotLine[2]);
                TimeSlot eachTimeSlot = new TimeSlot(eachTimeSlotLine[0], dayOfWeek, hour);
                TimeSlots.add(eachTimeSlot);
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
        return TimeSlots;
    }






}