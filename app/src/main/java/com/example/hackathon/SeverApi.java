package com.example.hackathon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SeverApi {

    @POST("/auth/login")
    Call<LogInResponse> Login (
            @Body LogInRequest logInRequest
    );

    @POST("/auth/signup")
    Call<Void> Signup (
            @Body SignupRequest signupRequest
    );

    @POST("/report")
    Call<Void> report (
            @Header("Authorization") String Token,
            @Body ReportRequest reportRequest
    );

    @GET("/report/near")
    Call<ReportNearResponse> reportNear (
            @Header("Authorization") String Token
    );

    @GET("/report/main")
    Call<ReportMainResponse> reportMain (
            @Header("Authorization") String Token
    );

    @GET("/report/all")
    Call<ReportAllResponse> reportAll (
            @Header("Authorization") String Token
    );
}
