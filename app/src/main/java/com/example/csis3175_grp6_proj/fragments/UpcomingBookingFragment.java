package com.example.csis3175_grp6_proj.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.csis3175_grp6_proj.activities.BookingReviewActivity;
import com.example.csis3175_grp6_proj.models.Booking;
import com.example.csis3175_grp6_proj.adapters.BookingAdapter;
import com.example.csis3175_grp6_proj.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpcomingBookingFragment extends Fragment {

    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences;
    String userId;


    List<Booking> UpcomingBookingList = new ArrayList<>();
    List<String> BookingNames = new ArrayList<>
            (Arrays.asList("Table Tennis", "Badminton","Basketball"));

    List<String> BookingFacilities = new ArrayList<>(Arrays.asList("BON Multi-purpose Room 4/5", "BON Gym 2/3", "CAM Sportshall 3/4"));

    List<String> BookingCenters = new ArrayList<>(Arrays.asList("Bonsor Recreation Complex (BON)", "Bonsor Recreation Complex (BON)", "Cameron Recreation Complex (CAM)"));
    List<String> BookingDates = new ArrayList<>(Arrays.asList("Oct 30, 2023", "Oct 30, 2023", "Oct 30, 2023"));

    List<String> BookingTimes = new ArrayList<>(Arrays.asList("Mon 7:45 PM - 9:45 PM", " Mon 7:45 PM - 9:45 PM", " Mon 7:45 PM - 9:45 PM"));

    List<Integer> BookingActivityLogos = new ArrayList<>(Arrays.asList(R.drawable.table_tennis, R.drawable.badminton, R.drawable.basketball));
    List<String> BookingStatuses = new ArrayList<>(Arrays.asList("Upcoming", "Upcoming", "Upcoming"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //LoadMoreData();
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_upcoming_booking, container, false);

        //View view = inflater.inflate(R.layout.fragment_upcoming_booking, container, false);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("USER_ID", null);
        Log.d("mybooking", userId + "");

        // Find the ListView by its ID
        //ListView lstViewBooking = view.findViewById(R.id.lstViewBooking);

        // Create a custom adapter to display your booking data
        //BookingAdapter myAdapter = new BookingAdapter(UpcomingBookingList);
        // Set the adapter for the ListView
        //lstViewBooking.setAdapter(myAdapter);
        // Route to booking review activity on click
//        lstViewBooking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("icyfung", String.valueOf(i));
//                Intent intent = new Intent(getActivity(), BookingReviewActivity.class);
//                startActivity(intent);
//            }
//        });
//
       return view;
    }

//    private void LoadMoreData() {
//
//        for (int i = 0; i < BookingNames.size();i++){
//            Booking eachBooking =
//                    new Booking(BookingNames.get(i),BookingActivityLogos.get(i), BookingCenters.get(i), BookingFacilities.get(i), BookingDates.get(i), BookingTimes.get(i), BookingStatuses.get(i));
//            UpcomingBookingList.add(eachBooking);
//        }
//    }
}