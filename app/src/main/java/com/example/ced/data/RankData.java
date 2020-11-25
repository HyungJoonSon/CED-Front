package com.example.ced.data;

//public class RankData {
//    private int rank;
//    private String userName;
//    private int time;
//
//    public RankData(int rank, String userId, int time) {
//        this.rank = rank;
//        this.userName = userName;
//        this.time = time;
//    }
//
//    public int getRank() {
//        return rank;
//    }
//
//    public int getTime() {
//        return time;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setRank(int rank) {
//        this.rank = rank;
//    }
//
//    public void setTime(int time) {
//        this.time = time;
//    }
//
//    public void setUserId(String userName) {
//        this.userName = userName;
//    }
//}


public class RankData {
    private int rank;
    private String userId;
    private String time;

    public RankData(int rank, String userId, String time) {
        this.rank = rank;
        this.userId = userId;
        this.time = time;
    }

    public int getRank() { return rank; }

    public String getTime() { return time; }

    public String getUserId() {
        return userId;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}