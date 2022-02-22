package com.example.traveliker.Responses;

import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.ReviewModel;

import java.util.List;

public class GetReviewResponse {

    String status;
    String message;
    List<ReviewModel> data;

    public GetReviewResponse(String status, String message, List<ReviewModel> data) {
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

    public List<ReviewModel> getData() {
        return data;
    }

    public void setData(List<ReviewModel> data) {
        this.data = data;
    }
}
