package com.example.traveliker.Models;

public class HotelModel {

    private int hotel_id;
    private String photo, name, location, information, price;

    public HotelModel(int hotel_id, String photo, String name, String location, String information, String price) {
        this.hotel_id = hotel_id;
        this.photo = photo;
        this.name = name;
        this.location = location;
        this.information = information;
        this.price = price;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
