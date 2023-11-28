package com.example.csis3175_grp6_proj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.csis3175_grp6_proj.R;
import com.example.csis3175_grp6_proj.databases.LeisureLinkDatabase;
import com.example.csis3175_grp6_proj.databinding.ActivityRegisterBinding;
import com.example.csis3175_grp6_proj.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {

    List<String> BeActivePassNumberList = new ArrayList<>();

    ActivityRegisterBinding binding;

    LeisureLinkDatabase lldb;

    boolean passwordValid = true;
    boolean emailValid = true;

    boolean passNumberValid = false;


    User newUser;

    int lastUserId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BeActivePassNumberList = ReadBeActivePassCSV();

        lldb = Room.databaseBuilder(getApplicationContext(),
                LeisureLinkDatabase.class,"leisurelink.db").build();
        ExecutorService executorService
                = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<User> UsersFromDB = lldb.userDao().GetAllUsers();
                Log.d("Register", UsersFromDB.size() + " users in db");

                lastUserId = lldb.userDao().getLastInsertedUser().getUserId();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        //check if password matching
                        binding.txtPasswordConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {

                                if(!binding.txtPasswordConfirm.getText().toString().equals(binding.txtPasswordInput.getText().toString())) {
                                    Toast.makeText(RegisterActivity.this, "Confirm password doesn't match.", Toast.LENGTH_SHORT).show();
                                    binding.txtPasswordConfirm.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_dark));
                                    passwordValid = false;
                                } else {
                                    binding.txtPasswordConfirm.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
                                    passwordValid = true;
                                }

                            }
                        });

                        //check if email already existed in the database
                        binding.txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {

                                emailValid = true;
                                binding.txtEmail.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.black));

                                for (User user:UsersFromDB) {
                                    if (binding.txtEmail.getText().toString().equals(user.getEmail())) {
                                        Toast.makeText(RegisterActivity.this, "This email is already registered.", Toast.LENGTH_SHORT).show();
                                        emailValid = false;
                                        binding.txtEmail.setTextColor(ContextCompat.getColor(getApplicationContext(),android.R.color.holo_red_dark));
                                    }
                                }

                            }
                        });

                    }
                });

            }
        });

        binding.btnSubmit.setOnClickListener((View view) -> {

            if (binding.txtFirstName.getText().toString().isEmpty()
                    || binding.txtLastName.getText().toString().isEmpty()
                    || binding.txtEmail.getText().toString().isEmpty()
                    || binding.txtDateOfBirth.getText().toString().isEmpty()
                    || binding.txtPasswordInput.getText().toString().isEmpty()
                    || binding.txtPasswordConfirm.getText().toString().isEmpty())
            {
                Toast.makeText(RegisterActivity.this, "First 6 fields must not be empty.", Toast.LENGTH_SHORT).show();
            } else {

                if (emailValid && passwordValid) {

                    try {
                        if (binding.CheckBoxActivePass.isChecked()) {
                            if (binding.txtActivePassNum.getText().toString().isEmpty()) {
                                Toast.makeText(RegisterActivity.this, "Please enter active pass number", Toast.LENGTH_SHORT).show();
                            } else {
                                //verify activepass number
                                for (String passNumber:BeActivePassNumberList) {
                                    if(binding.txtActivePassNum.getText().toString().equals(passNumber)) {
                                        //can create account
                                        newUser = new User(lastUserId+1, binding.txtFirstName.getText().toString(), binding.txtLastName.getText().toString(), binding.txtEmail.getText().toString(), binding.txtPasswordInput.getText().toString(), binding.txtDateOfBirth.getText().toString(),true, binding.txtActivePassNum.getText().toString());
                                        //changing boolean activepassnumvalid
                                        passNumberValid = true;

                                        executorService.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                lldb.userDao().insertOneUser(newUser);

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        Toast.makeText(RegisterActivity.this, "Account successfully created.", Toast.LENGTH_SHORT).show();
                                                        Log.d("Register", newUser.getUserId() + " is added.");
                                                        //redirect to log in page
                                                        startActivity(new Intent(RegisterActivity.this, LogIn.class));

                                                    }
                                                });
                                            }
                                        });

                                    }
                                }

                                if (!passNumberValid) {

                                    Toast.makeText(RegisterActivity.this, "This BeActivePass number is not found.", Toast.LENGTH_SHORT).show();

                                }


                            }
                        } else {
                            //no activepass user adding to database

                            //user id update by 1
                            newUser = new User(lastUserId+1, binding.txtFirstName.getText().toString(), binding.txtLastName.getText().toString(), binding.txtEmail.getText().toString(), binding.txtPasswordInput.getText().toString(), binding.txtDateOfBirth.getText().toString(),false, "na");
//                            new InsertUserTask().execute(newUser);
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {

                                    lldb.userDao().insertOneUser(newUser);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            Log.d("Register", newUser.getUserId() + " is added.");
                                            Toast.makeText(RegisterActivity.this, "Account successfully created.", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this, LogIn.class));

                                        }
                                    });

                                }
                            });


                        }

                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                } else if(!emailValid) {

                    Toast.makeText(RegisterActivity.this, "This email is already registered.", Toast.LENGTH_SHORT).show();

                } else if(!passwordValid) {
                    Toast.makeText(RegisterActivity.this, "Confirm password doesn't match.", Toast.LENGTH_SHORT).show();
                }

            }


        });


    }


    private List<String> ReadBeActivePassCSV() {
        List<String> BeActivePassNumbers = new ArrayList<>();

        InputStream inputStream = getResources()
                .openRawResource(R.raw.beactivepass);
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(inputStream));
        String passNumberLine;

        try {
            if ((passNumberLine = reader.readLine()) != null){
                //process header
            }
            while((passNumberLine = reader.readLine()) != null){
                BeActivePassNumbers.add(passNumberLine);
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

        return BeActivePassNumbers;
    }

}