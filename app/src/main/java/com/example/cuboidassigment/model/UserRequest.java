package com.example.cuboidassigment.model;

public class UserRequest {
    String user_name ="";
    String email_address ="";
    String password ="";
    int device_type =0;
    String android_token ="";

    public UserRequest(){
    }
    public UserRequest(String user_name, String email_address, String password, int device_type, String android_token) {
        this.user_name = user_name;
        this.email_address = email_address;
        this.password = password;
        this.device_type = device_type;
        this.android_token = android_token;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public String getAndroid_token() {
        return android_token;
    }

    public void setAndroid_token(String android_token) {
        this.android_token = android_token;
    }
}
