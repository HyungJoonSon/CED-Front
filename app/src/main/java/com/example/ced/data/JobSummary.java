package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class JobSummary {
    @SerializedName("JobField")
    private String jobField;

    @SerializedName("JobName")
    private String jobName;

    public String getJobField() {
        return jobField;
    }

    public String getJobName() {
        return jobName;
    }
}
