package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class RankUserResponse {
    @SerializedName("rank")
    private int userRank;

    @SerializedName("UserName")
    private String userName;

    @SerializedName("Time")
    private int Time;

    public int getUserRank() {
        return userRank;
    }

    public String getUserName() {
        return userName;
    }

    public int getTime() {
        return Time;
    }
}