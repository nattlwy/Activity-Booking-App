package com.example.csis3175_grp6_proj.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Booking;
import com.example.csis3175_grp6_proj.models.TimeSlot;
import com.example.csis3175_grp6_proj.models.Venue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SportsBookingActivity2 extends AppCompatActivity {
    LeisureLinkDatabase lldb;
    List<Venue> venueList;
    List<TimeSlot> availableTimeslots;
    Booking CurrBooking = new Booking();
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

        // Init db
        lldb = Room.databaseBuilder(getApplicationContext(), LeisureLinkDatabase.class,"leisurelink.db").build();

        // Venue
        LoadVenueFromDBToVenueSpinner();
        Spinner venueSpinner = findViewById(R.id.spinnerVenueBooking);
        venueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SportBooking", venueList.get(position).getVenueName());
                CurrBooking.setVenueId(
                        venueList.get(position).getVenueId()
                );
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*
        1. pull venue and sessions data from database
        2. add the data to the two spinners
        3. add calendar
        4. Configure click listener of proceed button
            4.1 Input validation
            4.2 bundle data and start new activity

         */

        // calendar
        CalendarView calendar = findViewById(R.id.calendar);
        calendar.setMinDate(System.currentTimeMillis());
        calendar.setMaxDate(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String dateStr = dayOfMonth + "/" + (month+1) + "/" + year;
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK) - 1;
                if (dayOfWeek == 0)
                    dayOfWeek = 7;
                Log.d("SportBooking", dateStr + " " + dayOfWeek);
//                availableTimeslots = lldb.timeSlotDao().GetAvailableTimeSlotOfTheDay(dateStr, dayOfWeek);
//                List<String> lst = new ArrayList<>();
//                for (TimeSlot ts : availableTimeslots){
//                    String timeslotStr = ts.getHour() + ":00 - " + (ts.getHour() + 2) + ":00" ;
//                    Log.d("SportBooking", timeslotStr);
//                    lst.add(timeslotStr);
//                }
//                Spinner spinnerTimeSlotBooking = findViewById(R.id.spinnerTimeSlotBooking);
//                ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(SportsBookingActivity2.this, android.R.layout.simple_spinner_item, lst);
//                spinnerTimeSlotBooking.setAdapter(arrAdapter);
            }
        });

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

    public void LoadVenueFromDBToVenueSpinner() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                venueList = lldb.venueDao().GetAllVenues();
                List<String> venueNameList = new ArrayList<>();
                for (Venue ven: venueList) {
                    venueNameList.add(ven.getVenueName());
                }
                Spinner venueSpinner = findViewById(R.id.spinnerVenueBooking);
                // add venue to spinner
                ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(SportsBookingActivity2.this, android.R.layout.simple_spinner_item, venueNameList);
                venueSpinner.setAdapter(arrAdapter);
            }
        });
    }

    public void LoadAvailableTimeSlotsFromDB() {
    }
}