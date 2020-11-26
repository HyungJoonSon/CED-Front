package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class JoinRequest {
    @SerializedName("UserId")
    private String userId;

    @SerializedName("UserPassWord")
    private String userPassWord;

    @SerializedName("UserName")
    private String userName;

    public JoinRequest(String userId, String userPassWord, String userName) {
        this.userId = userId;
        this.userPassWord = userPassWord;
        this.userName = userName;
    }
}