package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class UserRank {
    @SerializedName("UserID")
    private String userid;

    @SerializedName("Weekly")
    private int weekly;

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }

    public String getUserid() {
        return userid;
    }

    public int getWeekly() {
        return weekly;
    }
}