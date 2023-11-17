package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.csis3175_grp6_proj.R;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView txtViewClickToRegister = findViewById(R.id.txtViewClickToRegister);

        txtViewClickToRegister.setOnClickListener((View view) -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

    }
}