package com.example.traveliker.Models;

public class PlacesModel {

    private int placeid;
    private String name, location, information, photo;

    public PlacesModel(int placeid, String name, String location, String information, String photo) {
        this.placeid = placeid;
        this.name = name;
        this.location = location;
        this.information = information;
        this.photo = photo;
    }

    public int getPlaceid() {
        return placeid;
    }

    public void setPlaceid(int placeid) {
        this.placeid = placeid;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
