package com.example.ced.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String BASE_URL = "http://ec2-3-128-254-212.us-east-2.compute.amazonaws.com:3000";
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()   // 길 만듦
                    .baseUrl(BASE_URL)  // 목적지
                    .addConverterFactory(GsonConverterFactory.create()) // 형태
                    .build();   // 시작
        }

        return retrofit;    // 길 리턴
    }
}