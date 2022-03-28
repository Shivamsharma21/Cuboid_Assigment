package com.example.cuboidassigment.model;

public class ParamsUserList {

    String token ="";
    int user_id = 0;


      public ParamsUserList(){

       }

    public ParamsUserList(String token, int user_id) {
        this.token = token;
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
