package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hackathon.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signupSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
    }

    private void Signup() {
        String accountId = binding.signupIdEt.getText().toString();
        String email = binding.signupEmail.getText().toString();
        String password = binding.signupPwEt.getText().toString();

        if(accountId.length()==0|| email.length()==0||password.length()==0){
            Toast.makeText(SignUpActivity.this, "모든 항목을 입력해주세요",Toast.LENGTH_SHORT).show();
        }else {
            SignupResponse(accountId,email, password);
        }
    }

    public void SignupResponse(String accountId, String email, String emailCherck) {
        SeverApi severApi = ApiProvider.getInstnace().create(SeverApi.class);

        SignupRequest signupRequest = new SignupRequest(accountId, email, emailCherck);

        severApi.Signup(signupRequest).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "회원가입에 성공했습니다",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUpActivity.this,"오류", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}