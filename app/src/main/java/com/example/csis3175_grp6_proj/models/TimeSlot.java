package com.example.csis3175_grp6_proj.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "timeslots")
public class TimeSlot {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="timeslotid")
    private String TimeSlotId;

    @NonNull
    @ColumnInfo(name="dayofweek")
    private int DayOfWeek;

    @NonNull
    @ColumnInfo(name="hour")
    private int Hour;

    @NonNull
    public String getTimeSlotId() {
        return TimeSlotId;
    }

    public void setTimeSlotId(@NonNull String timeSlotId) {
        TimeSlotId = timeSlotId;
    }

    public int getDayOfWeek() {
        return DayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        DayOfWeek = dayOfWeek;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public TimeSlot() {
    }

    public TimeSlot(@NonNull String timeSlotId, int dayOfWeek, int hour) {
        TimeSlotId = timeSlotId;
        DayOfWeek = dayOfWeek;
        Hour = hour;
    }
}
