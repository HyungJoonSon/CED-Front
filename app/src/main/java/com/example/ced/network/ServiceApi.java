package com.example.ced.network;

import com.example.ced.data.JoinRequest;
import com.example.ced.data.CodeResponse;
import com.example.ced.data.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    @POST("/user/join")
    Call<CodeResponse> userJoin(@Body JoinRequest data);

    @GET("/user/checkID")
    Call<CodeResponse> userCheckID(@Query("UserID") String data);

    @GET("/user/checkName")
    Call<CodeResponse> userCheckName(@Query("UserName") String data);

    @POST("/user/login")
    Call<CodeResponse> userLogin(@Body LoginRequest data);
}