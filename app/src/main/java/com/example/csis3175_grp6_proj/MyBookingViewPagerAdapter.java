package com.example.csis3175_grp6_proj;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStateManagerControl;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.csis3175_grp6_proj.fragments.CancelledBookingFragment;
import com.example.csis3175_grp6_proj.fragments.HistoryBookingFragment;
import com.example.csis3175_grp6_proj.fragments.UpcomingBookingFragment;

public class MyBookingViewPagerAdapter extends FragmentStateAdapter {

    public MyBookingViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new UpcomingBookingFragment();
            case 1:
                return new HistoryBookingFragment();
            case 2:
                return new CancelledBookingFragment();
            default:
                return new UpcomingBookingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
