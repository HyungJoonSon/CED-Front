package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class UpdateTimeRequest {
    @SerializedName("UserId")
    private String userId;

    @SerializedName("Time")
    private int time;

    public UpdateTimeRequest(String userId, int time) {
        this.userId = userId;
        this.time = time;
    }
}
