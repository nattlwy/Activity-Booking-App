package com.example.csis3175_grp6_proj.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.Booking;
import com.example.csis3175_grp6_proj.models.SportsIcon;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookingAdapter extends BaseAdapter {

    private LeisureLinkDatabase lldb;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    List<Booking> adapterBookings;

    public BookingAdapter(List<Booking> adapterBookings) {
        this.adapterBookings = adapterBookings;
    }

    @Override
    public int getCount() {
        return adapterBookings.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterBookings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_booking,viewGroup,false);
        }

        //populate
        TextView txtViewBookingName = view.findViewById(R.id.txtViewBookingName);
        TextView txtViewBookingVenueName = view.findViewById(R.id.txtViewBookingVenue);
        TextView txtViewBookingDateAndTime = view.findViewById(R.id.txtViewBookingDateAndTime);
        ImageView imgViewBookingLogo = view.findViewById(R.id.imgViewBookingLogo);

        int bookingId = adapterBookings.get(i).getBoookingId();
        String venueId = adapterBookings.get(i).getVenueId();
        Log.d("bookingadpater", Integer.toString(bookingId));

        lldb = Room.databaseBuilder(viewGroup.getContext(), LeisureLinkDatabase.class,"leisurelink.db").build();

        View finalView = view;
        executorService.execute(new Runnable() {
                @Override
            public void run() {
                String sportName = lldb.bookingDao().GetSportName(bookingId);
                String sportImageName = sportName.toLowerCase().replace(" ", "_");
                Log.d("bookingadpater", sportName);
                String facilityName = lldb.bookingDao().GetFacility(bookingId);
                int dayOfWeek = lldb.bookingDao().GetDayOfWeek(bookingId);
                int hour = lldb.bookingDao().GetHour(bookingId);
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

                finalView.post(new Runnable() {
                    @Override
                    public void run() {
                        txtViewBookingName.setText(sportName);
                        txtViewBookingVenueName.setText(venueId + "  " + facilityName);
                        txtViewBookingDateAndTime.setText(adapterBookings.get(i).getActivityDate() + "    " + engDayOfWeek + "    " + duration);
                        imgViewBookingLogo.setImageResource(viewGroup.getContext().getResources().getIdentifier(sportImageName, "drawable", viewGroup.getContext().getPackageName()));
                    }
                });
            }
    });


        return view;
    }
}
