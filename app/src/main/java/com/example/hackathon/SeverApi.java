package com.example.hackathon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SeverApi {

    @POST("/login")
    Call<LogInResponse> Login (
            @Body LogInRequest logInRequest
    );

    @POST("/signup")
    Call<SignupResponse> Signup (
            @Body SignupRequest signupRequest
    );
}
