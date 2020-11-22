package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobSummaryResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("result")
    private List<JobSummary> result;

    public int getCode() {
        return code;
    }

    public List<JobSummary> getResult() {
        return result;
    }
}
