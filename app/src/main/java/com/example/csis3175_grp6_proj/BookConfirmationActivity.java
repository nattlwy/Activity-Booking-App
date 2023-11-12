package com.example.csis3175_grp6_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookConfirmationActivity extends AppCompatActivity {
    List<String> TopicList = new ArrayList<>(Arrays.asList("Activity: ", "Time: ", "Location: "));
    List<String> InformationList = new ArrayList<>(Arrays.asList("Table Tennis", "Oct 31, 2023 Tue 8:30 AM - 10:30 AM", "Bonsor Recreation Complex (BON)"));

    List<BookingConfirmation> ConfirmationDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_confirmation);
        LoadModelData();

        ListView listViewBookingConfirmation = findViewById(R.id.listViewBookingInfo);

        BookingConfirmationAdapter myAdapter = new BookingConfirmationAdapter(ConfirmationDetails);

        listViewBookingConfirmation.setAdapter(myAdapter);
    }

    private void LoadModelData() {
        for (int i = 0; i < TopicList.size(); i++) {
            BookingConfirmation eachBooking = new BookingConfirmation(TopicList.get(i), InformationList.get(i));
            ConfirmationDetails.add(eachBooking);
        }
    }
}