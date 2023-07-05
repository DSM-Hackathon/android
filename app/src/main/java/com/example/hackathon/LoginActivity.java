package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hackathon.databinding.ActivityLoginBinding;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    public static String puToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        binding.loginGoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logIn() {
        String accountId = binding.loginIdEt.getText().toString();
        String password = binding.loginPasswordEt.getText().toString();

        if(accountId.length() == 0) {
            Toast.makeText(LoginActivity.this, "아이디를 입력해주세요",Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(LoginActivity.this , "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }else {
            logInResponse();
        }
    }

    private void logInResponse() {
        String accountId = binding.loginIdEt.getText().toString();
        String password = binding.loginPasswordEt.getText().toString();

        String token;

        token = FirebaseInstanceId.getInstance().getToken();

        LogInRequest logInRequest = new LogInRequest(accountId, password, token);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.Login(logInRequest).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "로그인에 성공 했습니다!",Toast.LENGTH_SHORT).show();
                    puToken = response.body().getAccessToken();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Log.d("TEST", t.toString());
                Toast.makeText(LoginActivity.this, "로그인에 실패 했습니다" ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}