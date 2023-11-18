package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.csis3175_grp6_proj.R;

public class SportsBookingActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_booking);

        Bundle extras = getIntent().getExtras();
        String sportName = extras.getString("sport");

        Log.d("icyfung", sportName);

        // title
        TextView textViewTitle = findViewById(R.id.txtViewTitleBooking);
        textViewTitle.setText(sportName + " Booking");

        /*
        1. pull venue and sessions data from database
        2. add the data to the two spinners
        3. add calendar
        4. Configure click listener of proceed button
            4.1 Input validation
            4.2 bundle data and start new activity

         */

        // back button
        Button backBtn = findViewById(R.id.btnBackSportBooking);
        backBtn.setOnClickListener((View view) -> {
            // back to home activity
            finish();
        });


        Button proceedBtn = findViewById(R.id.btnProceedBooking);
        proceedBtn.setOnClickListener((View view) -> {

        });

    }
}