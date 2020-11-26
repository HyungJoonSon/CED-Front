package com.example.ced.data;

import com.google.gson.annotations.SerializedName;

public class CodeResponse {
    @SerializedName("ResultCode")
    private int resultCode;

    public int getCode() {
        return resultCode;
    }
}