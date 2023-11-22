package com.example.csis3175_grp6_proj.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "sports")
public class Sport {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "sportid")
    private String SportId;

    @NonNull
    @ColumnInfo(name = "sportname")
    private String SportName;

    @NonNull
    @ColumnInfo(name = "facility")
    private String Facility;

    @NonNull
    @ColumnInfo(name = "capacity")
    private int Capacity;

    @NonNull
    public String getSportId() {
        return SportId;
    }

    public void setSportId(@NonNull String sportId) {
        SportId = sportId;
    }

    @NonNull
    public String getSportName() {
        return SportName;
    }

    public void setSportName(@NonNull String sportName) {
        SportName = sportName;
    }

    @NonNull
    public String getFacility() {
        return Facility;
    }

    public void setFacility(@NonNull String facility) {
        Facility = facility;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public Sport() {
    }

    public Sport(@NonNull String sportId, @NonNull String sportName, @NonNull String facility, int capacity) {
        SportId = sportId;
        SportName = sportName;
        Facility = facility;
        Capacity = capacity;
    }
}
