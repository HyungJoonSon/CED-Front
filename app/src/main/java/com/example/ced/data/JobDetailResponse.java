package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class JobDetailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("JobField")
    private String jobField;

    @SerializedName("JobName")
    private String jobName;

    @SerializedName("JobInfo")
    private String jobInfo;

    public int getCode() {
        return code;
    }

    public String getJobField() {
        return jobField;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobInfo() {
        return jobInfo;
    }
}