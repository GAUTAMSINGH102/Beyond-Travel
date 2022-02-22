package com.example.traveliker.Models;

public class TransportModel {

    private int transportId;
    private String transportName, transportType, transporttypeimage,
                    fromplace, toplace,
                    fromtime, totime,
                    departureDate, price,
                    fromimage, toimage,
                    fromairport, toairport;

    public TransportModel(int transportId, String transportName, String transportType, String transporttypeimage,
                          String fromplace, String toplace,
                          String fromtime, String totime,
                          String departureDate, String price,
                          String fromimage, String toimage,
                          String fromairport, String toairport) {

        this.transportId = transportId;
        this.transportName = transportName;
        this.transportType = transportType;
        this.transporttypeimage = transporttypeimage;
        this.fromplace = fromplace;
        this.toplace = toplace;
        this.fromtime = fromtime;
        this.totime = totime;
        this.departureDate = departureDate;
        this.price = price;
        this.fromimage = fromimage;
        this.toimage = toimage;
        this.fromairport = fromairport;
        this.toairport = toairport;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTransporttypeimage() {
        return transporttypeimage;
    }

    public void setTransporttypeimage(String transporttypeimage) {
        this.transporttypeimage = transporttypeimage;
    }

    public String getFromplace() {
        return fromplace;
    }

    public void setFromplace(String fromplace) {
        this.fromplace = fromplace;
    }

    public String getToplace() {
        return toplace;
    }

    public void setToplace(String toplace) {
        this.toplace = toplace;
    }

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFromimage() {
        return fromimage;
    }

    public void setFromimage(String fromimage) {
        this.fromimage = fromimage;
    }

    public String getToimage() {
        return toimage;
    }

    public void setToimage(String toimage) {
        this.toimage = toimage;
    }

    public String getFromairport() {
        return fromairport;
    }

    public void setFromairport(String fromairport) {
        this.fromairport = fromairport;
    }

    public String getToairport() {
        return toairport;
    }

    public void setToairport(String toairport) {
        this.toairport = toairport;
    }
}
