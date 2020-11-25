package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class RankUser {
    @SerializedName("rank")
    private int userRank;

    @SerializedName("UserID")
    private String userID;

    @SerializedName("Time")
    private int Time;

    public int getUserRank() {
        return userRank;
    }

    public String getUserID() {
        return userID;
    }

    public int getTime() {
        return Time;
    }
}