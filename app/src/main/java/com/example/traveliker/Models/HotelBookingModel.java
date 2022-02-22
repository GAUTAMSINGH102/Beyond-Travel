package com.example.traveliker.Models;

public class HotelBookingModel {

    private String days, bookTime, roomNo, hotel_id, userId;

    public HotelBookingModel(String days, String bookTime, String roomNo, String hotel_id, String userId) {
        this.days = days;
        this.bookTime = bookTime;
        this.roomNo = roomNo;
        this.hotel_id = hotel_id;
        this.userId = userId;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
