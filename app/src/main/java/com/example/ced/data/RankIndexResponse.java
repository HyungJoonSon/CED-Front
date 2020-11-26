package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class RankIndexResponse {
    @SerializedName("UserName")
    private String userName;

    @SerializedName("Weekly")
    private int weekly;

    public String getUserName() {
        return userName;
    }

    public int getWeekly() {
        return weekly;
    }
}