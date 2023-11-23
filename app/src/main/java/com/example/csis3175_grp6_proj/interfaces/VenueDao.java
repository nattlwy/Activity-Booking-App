package com.example.csis3175_grp6_proj.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.csis3175_grp6_proj.models.Sport;
import com.example.csis3175_grp6_proj.models.Venue;

import java.util.List;

@Dao
public interface VenueDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertVenues(Venue... venues);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneVenue(Venue venue);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertVenuesFromList(List<Venue> Venues);

    @Query("SELECT * FROM venues")
    List<Venue> GetAllVenues();
}
