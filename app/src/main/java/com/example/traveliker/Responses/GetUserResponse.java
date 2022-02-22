package com.example.traveliker.Responses;

import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.UserModel;

import java.util.List;

public class GetUserResponse {

    String status;
    String message;
    List<UserModel> data;

    public GetUserResponse(String status, String message, List<UserModel> data) {
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

    public List<UserModel> getData() {
        return data;
    }

    public void setData(List<UserModel> data) {
        this.data = data;
    }
}
