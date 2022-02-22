package com.example.traveliker.Responses;

import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.TransportModel;

import java.util.List;

public class GetTransportResponse {

    String status;
    String message;
    List<TransportModel> data;

    public GetTransportResponse(String status, String message, List<TransportModel> data) {
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

    public List<TransportModel> getData() {
        return data;
    }

    public void setData(List<TransportModel> data) {
        this.data = data;
    }
}
