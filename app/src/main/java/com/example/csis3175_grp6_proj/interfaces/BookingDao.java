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


    //add query to find booking with status = Upcoming and :userId -> return list booking
    @Query("SELECT * FROM bookings WHERE userId = :userId AND status = 'Upcoming'")
    List<Booking> GetUpcomingBookings(String userId);

    //add query to find booking with status = History and :userId -> return list
    @Query("SELECT * FROM bookings WHERE userId = :userId AND status = 'History'")
    List<Booking> GetHistoryBookings(String userId);

    //add query to find booking with status = Cancelled and :userId
    @Query("SELECT * FROM bookings WHERE userId = :userId AND status = 'Cancelled'")
    List<Booking> GetCancelledBookings(String userId);



    //add query to find sport name with inner join sport table and booking table

    @Query("SELECT sportname FROM bookings JOIN sports ON bookings.sportid = sports.sportid WHERE bookings.bookingid = :bookingId")
    String GetSportName(int bookingId);


    //add query to find facility name with inner join sport table and booking table
    @Query("SELECT facility FROM bookings JOIN sports ON bookings.sportid = sports.sportid WHERE bookings.bookingid = :bookingId")
    String GetFacility(int bookingId);

    //add query to find venue name with inner join venue table and booking table
    @Query("SELECT venuename FROM bookings JOIN venues ON bookings.venueid = venues.venueid WHERE bookings.bookingid = :bookingId")
    String GetVenueName(int bookingId);


    //add query to find timeslot day of week with inner join timeslot table
    @Query("SELECT dayofweek FROM bookings JOIN timeslots ON bookings.timeslotid = timeslots.timeslotid WHERE bookings.bookingid = :bookingId")
    int GetDayOfWeek(int bookingId);
    //add query to find timeslot hour with inner join timeslot table
    @Query("SELECT hour FROM bookings JOIN timeslots ON bookings.timeslotid = timeslots.timeslotid WHERE bookings.bookingid = :bookingId")
    int GetHour(int bookingId);

    //add query to show mode of payment
    @Query("SELECT beactivepassholder FROM bookings JOIN users ON bookings.userid = users.userid WHERE bookings.bookingid = :bookingId")
    boolean GetUserPassStatus(int bookingId);

    //show activity date
    //show venue id





}
