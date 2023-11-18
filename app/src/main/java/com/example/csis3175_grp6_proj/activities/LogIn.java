package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogIn extends AppCompatActivity {
    List<User> UserList = new ArrayList<>();
    LeisureLinkDatabase lldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText editTxtViewEmail = findViewById(R.id.txtUsername);
        EditText editTxtViewPassword = findViewById(R.id.txtPassword);
        TextView txtViewClickToRegister = findViewById(R.id.txtViewClickToRegister);
        Button btnLogIn = findViewById(R.id.btnLogIn);


        //Load file in
        UserList = ReadUserCSV();
        Log.d("LogIn",UserList.size()+ " Users in file");

        lldb = Room.databaseBuilder(getApplicationContext(), LeisureLinkDatabase.class,"leisurelink.db").build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                lldb.userDao().insertUsersFromList(UserList);
                List<User> UsersFromDB = lldb.userDao().GetAllUsers();
            }
        });



        btnLogIn.setOnClickListener((View view) -> {
            // Empty field
            if (editTxtViewEmail.getText().toString().isEmpty() || editTxtViewPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Email and Password cannot be blank", Toast.LENGTH_SHORT).show();
            }
            else {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        User user = lldb.userDao().getUserByEmailAndPassword(editTxtViewEmail.getText().toString(), editTxtViewPassword.getText().toString());

                        runOnUiThread(() -> {
                            if (user == null) {
                                Toast.makeText(LogIn.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(LogIn.this, MainActivity.class));
                            }
                        });
                    }
                });
            }

        });

        // Button - register activity if no account
        txtViewClickToRegister.setOnClickListener((View view) -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });

    }

    // Read File Method
    private List<User> ReadUserCSV() {
        List<User> Users = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.users);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String userLine;

        try {
            if ((userLine = reader.readLine()) != null) {

            }
            while ((userLine = reader.readLine()) != null) {
                String[] eachUserFields = userLine.split(",");
                int userId = Integer.parseInt(eachUserFields[0]);
                boolean beActivePassYesorNo = false;
                if (eachUserFields[6].equals("true")) {
                    beActivePassYesorNo = true;
                }
                Double credit = Double.parseDouble(eachUserFields[8]);
                User eachUser = new User(userId,eachUserFields[1], eachUserFields[2], eachUserFields[3],eachUserFields[4], eachUserFields[5], beActivePassYesorNo, eachUserFields[7], credit);
                Users.add(eachUser);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Users;
    }
}