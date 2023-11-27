package com.example.csis3175_grp6_proj.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.csis3175_grp6_proj.activities.BookingReviewActivity;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Booking;
import com.example.csis3175_grp6_proj.adapters.BookingAdapter;
import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpcomingBookingFragment extends Fragment {

    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences;
    String userId;

    LeisureLinkDatabase lldb;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    List<Booking> UpcomingBookingList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_upcoming_booking, container, false);

        //View view = inflater.inflate(R.layout.fragment_upcoming_booking, container, false);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("USER_ID", null);
        Log.d("upcoming", userId + "");

        lldb = Room.databaseBuilder(requireContext().getApplicationContext(),
                LeisureLinkDatabase.class,"leisurelink.db").build();

        // Start a background task using ExecutorService
        executorService.execute(() -> {
            UpcomingBookingList = fetchDataFromlldb();
            requireActivity().runOnUiThread(() -> {
                // Initialize adapter
                BookingAdapter myAdapter = new BookingAdapter(UpcomingBookingList);
                ListView lstViewBooking = view.findViewById(R.id.lstViewBooking);
                // Set the adapter to the ListView
                lstViewBooking.setAdapter(myAdapter);

                Log.d("upcoming", "successful run ui");

                lstViewBooking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("upcomingbookingbutton", String.valueOf(i));
                        Booking clickedBooking = (Booking) adapterView.getItemAtPosition(i);
                        int bookingId = clickedBooking.getBookingId();
                        Intent intent = new Intent(getActivity(), BookingReviewActivity.class);
                        intent.putExtra("bookingId", bookingId);
                        Log.d("upcoming", Integer.toString(bookingId));
                        startActivity(intent);
                    }
                });

            });
        });




        // Find the ListView by its ID
        //ListView lstViewBooking = view.findViewById(R.id.lstViewBooking);

        // Create a custom adapter to display your booking data
        //BookingAdapter myAdapter = new BookingAdapter(UpcomingBookingList);
        // Set the adapter for the ListView
        //lstViewBooking.setAdapter(myAdapter);
        // Route to booking review activity on click

//
       return view;
    }



    private List<Booking> fetchDataFromlldb() {
        List<Booking> UpcomingBookingFromDB = lldb.bookingDao().GetUpcomingBookings(userId);
        Log.d("Upcoming", UpcomingBookingFromDB.size() + " upcoming bookings in db");

        return UpcomingBookingFromDB;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        // Shutdown the executor service when the fragment is destroyed
        executorService.shutdown();
    }


}