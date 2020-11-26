package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RankListResponse {
    @SerializedName("result")
    private List<RankIndexResponse> result;

    public List<RankIndexResponse> getResult() { return result; }
}