package com.example.ced.network;

import com.example.ced.data.JobListResponse;
import com.example.ced.data.JoinRequest;
import com.example.ced.data.CodeResponse;
import com.example.ced.data.LoginRequest;
import com.example.ced.data.LoginResponse;
import com.example.ced.data.UpdateTimeRequest;
import com.example.ced.data.RankListResponse;
import com.example.ced.data.RankUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @POST("/login")                                             // 로그인 API
    Call<LoginResponse> userLogin(@Body LoginRequest data);

    @POST("/join")                                              // 회원가입 API
    Call<CodeResponse> userJoin(@Body JoinRequest data);

    @GET("/check/id")                                           // 아이디 중복검사 API
    Call<CodeResponse> userCheckID(@Query("UserId") String userId);

    @GET("/check/name")                                         // 이름 중복검사 API
    Call<CodeResponse> userCheckName(@Query("UserName") String userName);

    @GET("/challenge")                                          // 전체 랭킹 조회 API
    Call<RankListResponse> getListRank();

    @PATCH("/challenge")                                        // 유저 시간 갱신 API
    Call<CodeResponse> updateTime(@Body UpdateTimeRequest data);

    @GET("/challenge/{UserId}")                                 // 유저 랭킹 조회 API
    Call<RankUserResponse> getUserRank(@Path("UserId") String userId);

    @GET("/job")                                                // 직업 조회 API
    Call<JobListResponse> readJobSummary(@Query("JobField") String jobField);
}