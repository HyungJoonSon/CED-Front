package com.example.ced.data;

public class RankData {
    private int rank;
    private String userId;
    private int time;

    public RankData(int rank, String userId, int time) {
        this.rank = rank;
        this.userId = userId;
        this.time = time;
    }

    public int getRank() {
        return rank;
    }

    public int getTime() {
        return time;
    }

    public String getUserId() {
        return userId;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
