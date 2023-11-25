package com.example.csis3175_grp6_proj.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookings", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "userid", childColumns = "userid"),
        @ForeignKey(entity = Venue.class, parentColumns = "venueid", childColumns = "venueid"),
        @ForeignKey(entity = TimeSlot.class, parentColumns = "timeslotid", childColumns = "timeslotid"),
        @ForeignKey(entity = Sport.class, parentColumns = "sportid", childColumns = "sportid")})

public class Booking {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="bookingid")
    private int BoookingId;

    @NonNull
    @ColumnInfo(name="userid")
    private int UserId;

    @NonNull
    @ColumnInfo(name="venueid")
    private String VenueId;

    @NonNull
    @ColumnInfo(name="timeslotid")
    private String TimeSlotId;

    @NonNull
    @ColumnInfo(name="sportid")
    private String SportId;

    @NonNull
    @ColumnInfo(name="status")
    private String Status;

    @NonNull
    @ColumnInfo(name="acivitydate")
    private String ActivityDate;

    @NonNull
    @ColumnInfo(name="bookingtimestamp")
    private Long BookingTimeStamp;

    public int getBoookingId() {
        return BoookingId;
    }

    public void setBoookingId(int boookingId) {
        BoookingId = boookingId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    @NonNull
    public String getVenueId() {
        return VenueId;
    }

    public void setVenueId(@NonNull String venueId) {
        VenueId = venueId;
    }

    @NonNull
    public String getTimeSlotId() {
        return TimeSlotId;
    }

    public void setTimeSlotId(@NonNull String timeSlotId) {
        TimeSlotId = timeSlotId;
    }

    @NonNull
    public String getSportId() {
        return SportId;
    }

    public void setSportId(@NonNull String sportId) {
        SportId = sportId;
    }

    @NonNull
    public String getStatus() {
        return Status;
    }

    public void setStatus(@NonNull String status) {
        Status = status;
    }

    @NonNull
    public String getActivityDate() {
        return ActivityDate;
    }

    public void setActivityDate(@NonNull String activityDate) {
        ActivityDate = activityDate;
    }

    @NonNull
    public Long getBookingTimeStamp() {
        return BookingTimeStamp;
    }

    public void setBookingTimeStamp(@NonNull Long bookingTimeStamp) {
        BookingTimeStamp = bookingTimeStamp;
    }

    public Booking(int boookingId, int userId, @NonNull String venueId, @NonNull String timeSlotId, @NonNull String sportId, @NonNull String status, @NonNull String activityDate, @NonNull Long bookingTimeStamp) {
        BoookingId = boookingId;
        UserId = userId;
        VenueId = venueId;
        TimeSlotId = timeSlotId;
        SportId = sportId;
        Status = status;
        ActivityDate = activityDate;
        BookingTimeStamp = bookingTimeStamp;
    }

    public Booking() {
    }


}
