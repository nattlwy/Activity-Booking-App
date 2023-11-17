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

    public Sport() {
    }

    public Sport(@NonNull String sportId, @NonNull String sportName, @NonNull String facility) {
        SportId = sportId;
        SportName = sportName;
        Facility = facility;
    }
}
