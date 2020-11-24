package com.example.ced.data;

public class InterviewData {
    private String jobName;
    private String Url;

    public InterviewData(String jobName, String url) {
        this.jobName = jobName;
        this.Url = url;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getJobName() {
        return jobName;
    }

    public String getUrl() {
        return Url;
    }
}
