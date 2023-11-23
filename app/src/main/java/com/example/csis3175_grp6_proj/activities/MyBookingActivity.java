package com.example.csis3175_grp6_proj.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.csis3175_grp6_proj.adapters.MyBookingViewPagerAdapter;
import com.example.csis3175_grp6_proj.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MyBookingActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {


    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyBookingViewPagerAdapter myBookingViewPagerAdapter;
    BottomNavigationView bottomNavigationView;

    FrameLayout frameLayout;

    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 =findViewById(R.id.view_pager);
        myBookingViewPagerAdapter = new MyBookingViewPagerAdapter(this);
        viewPager2.setAdapter(myBookingViewPagerAdapter);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.mybooking);
        bottomNavigationView.setOnItemSelectedListener(this);
        frameLayout = findViewById(R.id.frameLayoutMyBooking);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("USER_ID", null);
        Log.d("upcomingbooking", userId + "");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        frameLayout.setVisibility(View.VISIBLE);
        viewPager2.setVisibility(View.GONE);
        Intent myIntent;
        int itemId = item.getItemId();
        if (itemId == R.id.profile) {
            myIntent = new Intent(this, MyAccountActivity.class);
            startActivity(myIntent);
            overridePendingTransition(0, 0);

            return true;
        }
        else if (itemId == R.id.home) {
            myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            overridePendingTransition(0, 0);

            return true;

        } else if (itemId == R.id.mybooking) {
            return true;
        }
        return false;
    }
}