package com.example.hackathon;

public class LogInRequest {

    private String accountId;
    private String password;

    private String deviceToken;


    public LogInRequest(String accountId, String password, String deviceToken){
        this.accountId = accountId;
        this.password = password;
        this.deviceToken = deviceToken;
    }

    public String get(){
        return accountId;
    }

    public void set(String accountId){
        this.accountId = accountId;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password){
        this.password = password;
    }

    public String getDeviceToken(){
        return  deviceToken;
    }

    public void setDeviceToken(String deviceToken){
        this.deviceToken = deviceToken;
    }

}
