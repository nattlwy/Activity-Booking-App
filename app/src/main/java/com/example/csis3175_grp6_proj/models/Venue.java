package com.example.csis3175_grp6_proj.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName="venues")
public class Venue {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="venueid")
    private String VenueId;


    @NonNull
    @ColumnInfo(name="venuename")
    private String VenueName;

    @NonNull
    @ColumnInfo(name="latitude")
    private String Latitude;

    @NonNull
    @ColumnInfo(name = "longitude")
    private String Longitude;

    @NonNull
    public String getVenueId() {
        return VenueId;
    }

    public void setVenueId(@NonNull String venueId) {
        VenueId = venueId;
    }

    @NonNull
    public String getVenueName() {
        return VenueName;
    }

    public void setVenueName(@NonNull String venueName) {
        VenueName = venueName;
    }

    @NonNull
    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(@NonNull String latitude) {
        Latitude = latitude;
    }

    @NonNull
    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(@NonNull String longitude) {
        Longitude = longitude;
    }

    public Venue() {
    }

    public Venue(@NonNull String venueId, @NonNull String venueName, @NonNull String latitude, @NonNull String longitude) {
        VenueId = venueId;
        VenueName = venueName;
        Latitude = latitude;
        Longitude = longitude;
    }
}
