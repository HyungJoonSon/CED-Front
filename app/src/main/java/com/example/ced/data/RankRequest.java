package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class RankRequest {
    @SerializedName("UserName")
    private String username;

    @SerializedName("Time")
    private int time;

    public RankRequest(String username, int time) {
        this.username = username;
        this.time = time;
    }
}
