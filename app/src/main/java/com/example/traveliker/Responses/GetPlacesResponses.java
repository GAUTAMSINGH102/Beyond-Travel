package com.example.traveliker.Responses;

import com.example.traveliker.Models.PlacesModel;

import java.util.List;

public class GetPlacesResponses {

    String status;
    String message;
    List<PlacesModel> data;

    public GetPlacesResponses(String status, String message, List<PlacesModel> data) {
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

    public List<PlacesModel> getData() {
        return data;
    }

    public void setData(List<PlacesModel> data) {
        this.data = data;
    }
}
