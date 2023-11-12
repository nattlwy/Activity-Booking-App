package com.example.csis3175_grp6_proj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SportsIconRecyclerViewAdapter.OnItemClickListener {

    List<SportsIcon> SportsIconList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddData();

        RecyclerView recyclerViewIcons = findViewById(R.id.recyclerViewSportsImgMain);
        SportsIconRecyclerViewAdapter adapter =  new SportsIconRecyclerViewAdapter(
                SportsIconList,
                this
        );

        GridLayoutManager gm = new GridLayoutManager(this, 3);
        recyclerViewIcons.setAdapter(adapter);
        recyclerViewIcons.setLayoutManager(gm);



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
    }
}