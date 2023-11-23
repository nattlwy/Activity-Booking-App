package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Booking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookingReviewActivity extends AppCompatActivity {

    private LeisureLinkDatabase lldb;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_review);

        Intent intent = getIntent();
        int bookingId = intent.getIntExtra("bookingId", -1);
        Log.d("reviewactivity", Integer.toString(bookingId));

        TextView txtViewReviewSportName = findViewById(R.id.txtViewReviewBookingNameInfo);
        TextView txtViewReviewDate = findViewById(R.id.txtViewReviewBookingDateInfo);
        TextView txtViewReviewTime = findViewById(R.id.txtViewReviewBookingTimeInfo);
        TextView txtViewReviewVenue = findViewById(R.id.txtViewReviewBookingCenterInfo);
        TextView txtViewReviewFacility = findViewById(R.id.txtViewReviewBookingFacilityInfo);
        TextView txtViewPaymentMode = findViewById(R.id.txtViewModeOfPaymentInfo);

        lldb = Room.databaseBuilder(getApplicationContext(),
                LeisureLinkDatabase.class,"leisurelink.db").build();
        ExecutorService executorService
                = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                String sportName = lldb.bookingDao().GetSportName(bookingId);
                String sportImageName = sportName.toLowerCase().replace(" ", "_");
                Log.d("bookingreview", sportName);
                String facilityName = lldb.bookingDao().GetFacility(bookingId);
                String venueName = lldb.bookingDao().GetVenueName(bookingId);
                int dayOfWeek = lldb.bookingDao().GetDayOfWeek(bookingId);
                int hour = lldb.bookingDao().GetHour(bookingId);
                Booking clickedBooking = lldb.bookingDao().GetOneBookingByBookingId(bookingId);
                String duration = Integer.toString(hour) + ":00 - " + Integer.toString(hour+2) + ":00";
                String engDayOfWeek;
                if (dayOfWeek == 1) {
                    engDayOfWeek = "Mon";
                }
                else if (dayOfWeek == 2) {
                    engDayOfWeek = "Tue";
                }
                else if (dayOfWeek == 3) {
                    engDayOfWeek = "Wed";
                }
                else if (dayOfWeek == 4) {
                    engDayOfWeek = "Thu";
                }
                else if (dayOfWeek == 5) {
                    engDayOfWeek = "Fri";
                }
                else if (dayOfWeek == 6) {
                    engDayOfWeek = "Sat";
                }
                else {
                    engDayOfWeek = "Sun";
                }
                boolean activePassStatus = lldb.bookingDao().GetUserPassStatus(bookingId);
                String paymentMode;
                if (activePassStatus) {
                    paymentMode = "Be Active Pass";
                } else {
                    paymentMode = "CAD $3.99 Pay At Venue";
                }

                Log.d("bookingreview", Boolean.toString(activePassStatus));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtViewReviewSportName.setText(sportName);
                        txtViewReviewFacility.setText(facilityName);
                        txtViewPaymentMode.setText(paymentMode);
                        txtViewReviewVenue.setText(venueName);
                        txtViewReviewDate.setText(engDayOfWeek + " " + clickedBooking.getActivityDate());
                        txtViewReviewTime.setText(duration);

                    }
                });





            }
        });



        // Back button
        Button backBtn = findViewById(R.id.btnBackMyBooking);
        backBtn.setOnClickListener((View view) -> { finish(); });

        // Cancel Booking button
        Button cancelBookingBtn = findViewById(R.id.buttonCancellation);
        cancelBookingBtn.setOnClickListener((View view) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(BookingReviewActivity.this);
            builder.setMessage(R.string.cancelBookingDialogMsg);
            // Yes Button
            builder.setPositiveButton(R.string.cancelBookingDialogYesBtnTxt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("icyfung", "Yes, cancel");
                }
            });
            // No button
            builder.setNegativeButton(R.string.cancelBookingDialogNoBtnTxt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("icyfung", "No, don't cancel");
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }
}