package com.example.csis3175_grp6_proj.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.csis3175_grp6_proj.interfaces.BookingDao;
import com.example.csis3175_grp6_proj.interfaces.SportDao;
import com.example.csis3175_grp6_proj.interfaces.TimeSlotDao;
import com.example.csis3175_grp6_proj.interfaces.UserDao;
import com.example.csis3175_grp6_proj.interfaces.VenueDao;
import com.example.csis3175_grp6_proj.models.Booking;
import com.example.csis3175_grp6_proj.models.Sport;
import com.example.csis3175_grp6_proj.models.TimeSlot;
import com.example.csis3175_grp6_proj.models.User;
import com.example.csis3175_grp6_proj.models.Venue;

@Database(entities = {Sport.class, User.class, TimeSlot.class, Venue.class, Booking.class}, version = 1, exportSchema = false)
public abstract class LeisureLinkDatabase extends RoomDatabase {
    public abstract SportDao sportDao();
    public abstract UserDao userDao();
    public abstract TimeSlotDao timeSlotDao();
    public abstract VenueDao venueDao();
    public abstract BookingDao bookingDao();
}
