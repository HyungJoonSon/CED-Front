package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class RankRequest {
    @SerializedName("UserID")
    private String userid;

    @SerializedName("Time")
    private int time;

    public RankRequest(String userid, int time) {
        this.userid = userid;
        this.time = time;
    }
}
