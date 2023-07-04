package com.example.hackathon;

public class LogInRequest {

    private String accountId;
    private String password;


    public LogInRequest(String accountId, String password){
        this.accountId = accountId;
        this.password = password;
    }

    public String get(){
        return accountId;
    }

    public void set(String accountId){
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
