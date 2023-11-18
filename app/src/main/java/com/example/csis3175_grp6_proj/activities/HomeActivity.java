package com.example.csis3175_grp6_proj.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.models.Sport;
import com.example.csis3175_grp6_proj.models.SportsIcon;
import com.example.csis3175_grp6_proj.adapters.SportsIconRecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements SportsIconRecyclerViewAdapter.OnItemClickListener, NavigationBarView.OnItemSelectedListener {
    List<SportsIcon> SportsIconList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Populate Sports Grid Layout
        AddData();
        RecyclerView recyclerViewIcons = findViewById(R.id.recyclerViewSportsImgHome);
        SportsIconRecyclerViewAdapter adapter =  new SportsIconRecyclerViewAdapter(
                SportsIconList,
                HomeActivity.this
        );

        GridLayoutManager gm = new GridLayoutManager(HomeActivity.this, 3);
        recyclerViewIcons.setAdapter(adapter);
        recyclerViewIcons.setLayoutManager(gm);

        // Populate Bottom Nav Bar
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(HomeActivity.this);

    }

    public void AddData() {
        SportsIconList.add(
                new SportsIcon(101, "Basketball", R.drawable.basketball)
        );
        SportsIconList.add(
                new SportsIcon(102, "Badminton", R.drawable.badminton)
        );
        SportsIconList.add(
                new SportsIcon(103, "Table Tennis", R.drawable.table_tennis)
        );
    }

    @Override
    public void onItemClick(int i) {
        String sportName = SportsIconList.get(i).getImgName();

        Bundle bundle = new Bundle();
        bundle.putString("sport", sportName);
        Intent intent = new Intent(HomeActivity.this, SportsBookingActivity2.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent myIntent;
        int itemId = item.getItemId();
        if (itemId == R.id.profile) {
            myIntent = new Intent(this, MyAccountActivity.class);
            startActivity(myIntent);
            overridePendingTransition(0, 0);

            return true;
        }
        else if (itemId == R.id.mybooking) {
            myIntent = new Intent(this, MyBookingActivity.class);
            startActivity(myIntent);
            overridePendingTransition(0, 0);

            return true;
        } else if (itemId == R.id.home) {
            return true;
        }
        return false;
    }
}