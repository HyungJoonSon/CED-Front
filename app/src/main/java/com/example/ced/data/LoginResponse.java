package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("UserName")
    private String userName;

    @SerializedName("code")
    private int code;

    public String getUserName() {
        return userName;
    }
    public int getCode() {
        return code;
    }
}
