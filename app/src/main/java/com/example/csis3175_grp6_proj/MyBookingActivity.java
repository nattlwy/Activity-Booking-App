package com.example.csis3175_grp6_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MyBookingActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyBookingViewPagerAdapter myBookingViewPagerAdapter;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 =findViewById(R.id.view_pager);
        myBookingViewPagerAdapter = new MyBookingViewPagerAdapter(this);
        viewPager2.setAdapter(myBookingViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
        Intent myIntent;
        int itemId = item.getItemId();
        if (itemId == R.id.profile) {
            myIntent = new Intent(this, MyAccountActivity.class);
            startActivity(myIntent);

            return true;
        }
        else if (itemId == R.id.home) {
            myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }
        return false;
    }
}