package com.example.ced.data;

public class RankData {
    private int rank;
    private String userName;
    private String time;

    public RankData(int rank, String userName, String time) {
        this.rank = rank;
        this.userName = userName;
        this.time = time;
    }

    public int getRank() {
        return rank;
    }

    public String getTime() {
        return time;
    }

    public String getUserName() {
        return userName;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}