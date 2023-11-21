package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.csis3175_grp6_proj.R;

public class BookingReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_review);

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