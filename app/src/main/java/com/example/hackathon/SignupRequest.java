package com.example.hackathon;

public class SignupRequest {
    private String accountId;
    private String password;

    private String passwordCheck;

    public SignupRequest(String accountId, String password, String passwordcheck){
        this.accountId = accountId;
        this.password = password;
        this.passwordCheck = passwordcheck;
    }

    public String getaccountId() {
        return accountId;
    }

    public void setaccountId(String accountId){
        this.accountId = accountId;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password){
        this.password = password;
    }

    public String getpasswordcheck() {
        return passwordCheck;
    }
    public void setpasswordcheck(String passwordcheck){
        this.passwordCheck = passwordcheck;
    }
}
