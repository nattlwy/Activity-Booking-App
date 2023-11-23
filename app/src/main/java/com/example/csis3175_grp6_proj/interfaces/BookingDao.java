package com.example.csis3175_grp6_proj.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.csis3175_grp6_proj.models.Booking;

import java.util.List;

@Dao
public interface BookingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBookings(Booking... bookings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertBookingsFromList(List<Booking> Bookings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneBooking(Booking booking);

    @Query("SELECT * FROM bookings")
    List<Booking> GetAllBookings();

    //add query to find booking with status = Upcoming and :userId
    //add query to find booking with status = History and :userId
    //add query to find booking with status = Cancelled and :userId


}
