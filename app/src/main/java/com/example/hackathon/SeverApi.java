package com.example.hackathon;

import retrofit2.Call;
import retrofit2.http.Body;
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
}
