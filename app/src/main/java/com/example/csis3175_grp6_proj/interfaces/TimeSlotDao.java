package com.example.csis3175_grp6_proj.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.csis3175_grp6_proj.models.Sport;
import com.example.csis3175_grp6_proj.models.TimeSlot;

import java.util.List;

@Dao
public interface TimeSlotDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTimeSlots(TimeSlot... timeSlots);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneTimeSlot(TimeSlot timeSlot);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertTimeSlotsFromList(List<TimeSlot> timeslots);

    @Query("SELECT * FROM timeslots")
    List<TimeSlot> GetAllTimeSlots();

    @Query("SELECT * FROM TIMESLOTS WHERE timeslotid not in (SELECT timeslotid FROM BOOKINGS WHERE acivitydate = :date AND status != 'Cancelled') AND dayofweek = :dayOfWeek")
    List<TimeSlot> GetAvailableTimeSlotOfTheDay(String date, int dayOfWeek);
}
