package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RankResponse {
    @SerializedName("result")
    private List<UserRank> result;

    public void setResult(List<UserRank> result) {
        this.result = result;
    }

    public List<UserRank> getResult() {
        return result;
    }
}
