package com.example.csis3175_grp6_proj.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.models.Booking;

import java.util.List;

public class BookingAdapter extends BaseAdapter {

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

//        txtViewBookingName.setText(adapterBookings.get(i).getBookingName());
//        txtViewBookingVenueName.setText(adapterBookings.get(i).getBookingFacility());
//        txtViewBookingDateAndTime.setText(adapterBookings.get(i).getBookingDate() + " " + adapterBookings.get(i).getBookingTime());
//
//        imgViewBookingLogo.setImageResource(adapterBookings.get(i).getBookingActivityPic());

        return view;
    }
}
