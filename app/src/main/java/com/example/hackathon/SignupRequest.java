package com.example.hackathon;

public class SignupRequest {
    private String accountId;
    private String password;

    private String passwordcheck;

    public SignupRequest(String accountId, String password, String passwordcheck){
        this.accountId = accountId;
        this.password = password;
        this.passwordcheck = passwordcheck;
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

    public String getPasswordcheck() {
        return passwordcheck;
    }
    public void setPasswordcheck(String passwordcheck){
        this.passwordcheck = passwordcheck;
    }
}
