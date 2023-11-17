package com.example.csis3175_grp6_proj.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.csis3175_grp6_proj.models.Sport;

import java.util.List;

@Dao
public interface SportDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSports(Sport... sports);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneSport(Sport sports);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertSportsFromList(List<Sport> Sports);

    @Query("SELECT * FROM sports")
    List<Sport> GetAllSports();
}
