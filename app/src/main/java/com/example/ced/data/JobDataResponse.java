package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class JobDataResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("result")
    private List<JobData> result;

    public int getCode() {
        return code;
    }

    public List<JobData> getResult() {
        return result;
    }
}
