package com.example.csis3175_grp6_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initDatePicker();
        dateButton = findViewById(R.id.btnDOB);
        dateButton.setText(getTodaysDate());
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        String monthEng;
        switch (month) {
            case 1:
                monthEng = "JAN";
                break;
            case 2:
                monthEng = "FEB";
                break;
            case 3:
                monthEng =  "MAR";
                break;
            case 4:
                monthEng = "APR";
                break;
            case 5:
                monthEng = "MAY";
                break;
            case 6:
                monthEng = "JUN";
                break;
            case 7:
                monthEng = "JUL";
                break;
            case 8:
                monthEng = "AUG";
                break;
            case 9:
                monthEng = "SEP";
                break;
            case 10:
                monthEng = "OCT";
                break;
            case 11:
                monthEng = "NOV";
                break;
            case 12:
                monthEng = "DEC";
                break;
            default:
                monthEng = "ERROR";
                break;
        }
        return monthEng;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

}