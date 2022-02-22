package com.example.traveliker.Models;

public class UserModel {

    private int userId;
    private String name, email_id, password, mobileNo, address, age;

    public UserModel(int userId, String name, String email_id, String password, String mobileNo, String address, String age) {
        this.userId = userId;
        this.name = name;
        this.email_id = email_id;
        this.password = password;
        this.mobileNo = mobileNo;
        this.address = address;
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
