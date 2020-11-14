package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class JoinRequest {
    @SerializedName("UserID")
    private String userId;

    @SerializedName("UserPwd")
    private String userPwd;

    @SerializedName("UserName")
    private String userName;

    public JoinRequest(String userId, String userPwd, String userName) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
    }
}