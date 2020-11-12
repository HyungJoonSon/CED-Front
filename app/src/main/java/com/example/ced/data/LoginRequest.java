package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("UserId")
    private String userId;

    @SerializedName("UserPwd")
    private String userPwd;

    public LoginRequest(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }
}