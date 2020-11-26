package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobListResponse {
    @SerializedName("ResultCode")
    private int resultCode;

    @SerializedName("result")
    private List<JobDataResponse> result;

    public int getCode() {
        return resultCode;
    }

    public List<JobDataResponse> getResult() {
        return result;
    }
}
