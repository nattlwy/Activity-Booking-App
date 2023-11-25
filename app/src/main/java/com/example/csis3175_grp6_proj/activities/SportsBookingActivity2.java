package com.example.csis3175_grp6_proj.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SportsBookingActivity2 extends AppCompatActivity {
    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences;
    LeisureLinkDatabase lldb;
    List<Venue> venueList;
    List<TimeSlot> availableTimeslots;
    List<String> availableTimeslotStrs = new ArrayList<>();
    Booking CurrBooking = new Booking();
    int CurrBookingDayOfWeek;
    Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_booking);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", null);
        Bundle extras = getIntent().getExtras();
        String sportName = extras.getString("sport");
        String sportId = extras.getString("sportId");
        CurrBooking.setSportId(sportId);
        CurrBooking.setUserId(Integer.parseInt(userId));

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
                bundle.putString("VenueName", venueList.get(position).getVenueName());
                LoadAvailableTimeSlotsFromDB(CurrBooking.getActivityDate(), CurrBookingDayOfWeek, CurrBooking.getVenueId());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // TimeSlots
        Spinner spinnerTimeSlotBooking = findViewById(R.id.spinnerTimeSlotBooking);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar today = Calendar.getInstance();
        String todayStr = sdf.format(today.getTime());
        CurrBookingDayOfWeek = today.get(Calendar.DAY_OF_WEEK) - 1;
        if (CurrBookingDayOfWeek == 0)
            CurrBookingDayOfWeek = 7;
        LoadAvailableTimeSlotsFromDB(todayStr, CurrBookingDayOfWeek, CurrBooking.getVenueId());
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(SportsBookingActivity2.this, android.R.layout.simple_spinner_item, availableTimeslotStrs);
        spinnerTimeSlotBooking.setAdapter(arrAdapter);

        spinnerTimeSlotBooking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CurrBooking.setTimeSlotId(availableTimeslots.get(position).getTimeSlotId());
                bundle.putString("TimeSlotString", availableTimeslotStrs.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                CurrBookingDayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK) - 1;
                if (CurrBookingDayOfWeek == 0)
                    CurrBookingDayOfWeek = 7;
                Log.d("SportBooking", dateStr + " " + CurrBookingDayOfWeek);
                LoadAvailableTimeSlotsFromDB(dateStr, CurrBookingDayOfWeek, CurrBooking.getVenueId());
                ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(SportsBookingActivity2.this, android.R.layout.simple_spinner_item, availableTimeslotStrs);
                spinnerTimeSlotBooking.setAdapter(arrAdapter);
                CurrBooking.setActivityDate(dateStr);
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
            Log.d("SportBooking", String.format("%s %s %s %s", CurrBooking.getUserId(), CurrBooking.getSportId(), CurrBooking.getActivityDate(), CurrBooking.getTimeSlotId()));
            bundle.putString("SportName", sportName);
            bundle.putInt("UserId", CurrBooking.getUserId());
            bundle.putString("SportId", CurrBooking.getSportId());
            bundle.putString("ActivityDate", CurrBooking.getActivityDate());
            bundle.putString("VenueId", CurrBooking.getVenueId());
            bundle.putString("TimeSlotId", CurrBooking.getTimeSlotId());
            bundle.putInt("BookingDayOfWeek", CurrBookingDayOfWeek);

            Intent intent = new Intent(SportsBookingActivity2.this, BookConfirmationActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
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

    public void LoadAvailableTimeSlotsFromDB(String date, int dayOfWeek, String venueId) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                availableTimeslots = lldb.timeSlotDao().GetAvailableTimeSlotOfTheDay(date, dayOfWeek, venueId);
                availableTimeslotStrs.clear();
                for (TimeSlot ts : availableTimeslots){
                    String timeslotStr = ts.getHour() + ":00 - " + (ts.getHour() + 2) + ":00" ;
//                    Log.d("SportBooking", timeslotStr);
                    availableTimeslotStrs.add(timeslotStr);
                }
            }
        });
    }
}