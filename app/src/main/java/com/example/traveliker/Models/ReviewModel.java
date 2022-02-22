package com.example.traveliker.Models;

public class ReviewModel {

    private String review, time;

    public ReviewModel(String review, String time) {
        this.review = review;
        this.time = time;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
