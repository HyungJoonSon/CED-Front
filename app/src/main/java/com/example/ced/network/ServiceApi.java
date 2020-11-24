package com.example.ced.network;

import com.example.ced.data.JobDataResponse;
import com.example.ced.data.JoinRequest;
import com.example.ced.data.CodeResponse;
import com.example.ced.data.LoginRequest;
import com.example.ced.data.LoginResponse;
import com.example.ced.data.RankRequest;
import com.example.ced.data.RankResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginRequest data);

    @POST("/user/join")
    Call<CodeResponse> userJoin(@Body JoinRequest data);

    @GET("/user/checkID")
    Call<CodeResponse> userCheckID(@Query("UserID") String data);

    @GET("/user/checkName")
    Call<CodeResponse> userCheckName(@Query("UserName") String data);

    // 두개 사용
    @GET("/challenge/ranking")
    Call<RankResponse> getRank();

    @PATCH("/challenge/renewal")
    Call<CodeResponse> renewalRank(@Body RankRequest data);

    @GET("/job")
    Call<JobDataResponse> readJobSummary(@Query("JobField") String data);
}