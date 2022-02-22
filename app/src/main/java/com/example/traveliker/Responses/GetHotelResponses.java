package com.example.traveliker.Responses;

import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.PlacesModel;

import java.util.List;

public class GetHotelResponses {

    String status;
    String message;
    List<HotelModel> data;

    public GetHotelResponses(String status, String message, List<HotelModel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HotelModel> getData() {
        return data;
    }

    public void setData(List<HotelModel> data) {
        this.data = data;
    }
}
