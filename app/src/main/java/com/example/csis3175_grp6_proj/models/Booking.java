package com.example.csis3175_grp6_proj.models;

import androidx.room.Entity;

public class Booking {

    private String BookingName;
    private int BookingActivityPic;
    private String BookingCenter;
    private String BookingFacility;
    //private String BookingDateAndTime;

    private String BookingDate;

    private String BookingTime;

    private String BookingStatus;

    public Booking(String bookingName, int bookingActivityPic, String bookingCenter, String bookingFacility, String bookingDate, String bookingTime, String bookingStatus) {
        BookingName = bookingName;
        BookingActivityPic = bookingActivityPic;
        BookingFacility = bookingFacility;
        BookingCenter = bookingCenter;
        BookingDate = bookingDate;
        BookingTime = bookingTime;
        BookingStatus = bookingStatus;
    }

    public String getBookingName() {
        return BookingName;
    }

    public void setBookingName(String bookingName) {
        BookingName = bookingName;
    }


    public int getBookingActivityPic() {
        return BookingActivityPic;
    }

    public void setBookingActivityPic(int bookingActivityPic) {
        BookingActivityPic = bookingActivityPic;
    }

    public String getBookingCenter() {
        return BookingCenter;
    }

    public void setBookingCenter(String bookingCenter) {
        BookingCenter = bookingCenter;
    }

    public String getBookingFacility() {
        return BookingFacility;
    }

    public void setBookingFacility(String bookingFacility) {
        BookingFacility = bookingFacility;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(String bookingTime) {
        BookingTime = bookingTime;
    }

    public String getBookingStatus() {
        return BookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        BookingStatus = bookingStatus;
    }
}
