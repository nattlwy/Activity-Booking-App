package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;

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