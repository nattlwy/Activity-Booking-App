package com.example.csis3175_grp6_proj.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.activities.BookingReviewActivity;
import com.example.csis3175_grp6_proj.adapters.BookingAdapter;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HistoryBookingFragment extends Fragment {

    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences;
    String userId;

    LeisureLinkDatabase lldb;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    List<Booking> HistoryBookingList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_booking, container, false);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("USER_ID", null);
        Log.d("history", userId + "");

        lldb = Room.databaseBuilder(requireContext().getApplicationContext(),
                LeisureLinkDatabase.class,"leisurelink.db").build();

        executorService.execute(() -> {
            HistoryBookingList = fetchDataFromlldb();
            requireActivity().runOnUiThread(() -> {
                // Initialize adapter
                BookingAdapter myAdapter = new BookingAdapter(HistoryBookingList);
                ListView lstViewBooking = view.findViewById(R.id.lstViewHistoryBooking);
                // Set the adapter to the ListView
                lstViewBooking.setAdapter(myAdapter);

                Log.d("history", "successful run ui");


            });
        });

        return view;
    }

    private List<Booking> fetchDataFromlldb() {
        List<Booking> HistoryBookingFromDB = lldb.bookingDao().GetHistoryBookings(userId);
        Log.d("History", HistoryBookingFromDB.size() + " history bookings in db");

        return HistoryBookingFromDB;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        // Shutdown the executor service when the fragment is destroyed
        executorService.shutdown();
    }
}