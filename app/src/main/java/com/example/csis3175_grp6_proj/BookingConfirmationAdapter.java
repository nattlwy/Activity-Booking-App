package com.example.csis3175_grp6_proj;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BookingConfirmationAdapter extends BaseAdapter {
    List<BookingConfirmation> adapterBookingConfirmation;

    public BookingConfirmationAdapter(List<BookingConfirmation> adapterBookingConfirmation) {
        this.adapterBookingConfirmation = adapterBookingConfirmation;
    }

    @Override
    public int getCount() {
        return adapterBookingConfirmation.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterBookingConfirmation.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_bookingconfirmation, viewGroup, false);
        }

        //populate
        TextView txtViewTopic = view.findViewById(R.id.txtViewTopic);
        txtViewTopic.setText(adapterBookingConfirmation.get(i).getTopic());

        TextView txtViewInformation = view.findViewById(R.id.txtViewInfo);
        txtViewInformation.setText(adapterBookingConfirmation.get(i).getInformation());

        return view;
    }
}
