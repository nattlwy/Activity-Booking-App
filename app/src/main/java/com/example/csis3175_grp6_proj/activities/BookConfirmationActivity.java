package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Booking;
import com.example.csis3175_grp6_proj.models.BookingConfirmation;
import com.example.csis3175_grp6_proj.adapters.BookingConfirmationAdapter;
import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookConfirmationActivity extends AppCompatActivity {
    String NextBookingId;
    LeisureLinkDatabase lldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_confirmation);
        Bundle extras = getIntent().getExtras();
        String sportName = extras.getString("SportName");

        ImageView sportsLogoImgView = findViewById(R.id.imgViewBookingConfirmActivityLogo);
        sportsLogoImgView.setImageResource(
                getResources().getIdentifier(sportName.toLowerCase().replace(" ", "_"), "drawable", getPackageName())
        );

        TextView sportNameTxtView = findViewById(R.id.txtViewConfirmBookingNameInfo);
        sportNameTxtView.setText(sportName);

        TextView dateTxtView = findViewById(R.id.txtViewConfirmBookingDateInfo);
        dateTxtView.setText(extras.getString("ActivityDate"));

        TextView timeslotTxtView = findViewById(R.id.txtViewConfirmBookingTimeInfo);
        timeslotTxtView.setText(extras.getString("TimeSlotString"));

        TextView venueInfoTxtView = findViewById(R.id.txtViewConfirmBookingCenterInfo);
        venueInfoTxtView.setText(extras.getString("VenueName"));

        TextView facilityInfoTxtView = findViewById(R.id.txtViewConfirmBookingFacilityInfo);
        TextView modeOfPaymentTxtView = findViewById(R.id.txtViewModeOfPaymentInfo);
        lldb = Room.databaseBuilder(getApplicationContext(), LeisureLinkDatabase.class,"leisurelink.db").build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String facility = lldb.sportDao().GetSportFacilityById(extras.getString("SportId"));
                facilityInfoTxtView.setText(facility);
                User user = lldb.userDao().getUserByUserId(extras.getInt("UserId"));
                String modeOfPayment;
                if (user.isBeActivePassHolder())
                    modeOfPayment = "Be Active Pass";
                else
                    modeOfPayment = "CAD $3.99 Pay At Venue";
                modeOfPaymentTxtView.setText(modeOfPayment);
            }
        });

        Booking booking = new Booking();
        booking.setActivityDate(extras.getString("ActivityDate"));
        booking.setUserId(extras.getInt("UserId"));
        booking.setSportId(extras.getString("SportId"));
        booking.setVenueId(extras.getString("VenueId"));
        booking.setTimeSlotId(extras.getString("TimeSlotId"));
        booking.setStatus("Upcoming");

        Button backBtn = findViewById(R.id.btnBackMyBooking);
        backBtn.setOnClickListener((View view) -> {
            finish();
        });

        Button confirmBtn = findViewById(R.id.buttonConfirmation);
        confirmBtn.setOnClickListener((View view) -> {

        });
    }
}