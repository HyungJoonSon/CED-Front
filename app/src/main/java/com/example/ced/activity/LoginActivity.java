package com.example.ced.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ced.R;
import com.example.ced.data.CodeResponse;
import com.example.ced.data.LoginRequest;
import com.example.ced.network.RetrofitClient;
import com.example.ced.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText login_id;
    private EditText login_pwd;
    private Button login_button;
    private String id;
    private String pwd;
    private ProgressBar login_progressbar;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        service = RetrofitClient.getClient().create(ServiceApi.class);

        login_id = findViewById(R.id.login_id);
        login_pwd = findViewById(R.id.login_pwd);
        login_button = findViewById(R.id.login_btn);
        login_progressbar = findViewById(R.id.login_pbar);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });

    }

    private void tryLogin() {
        id = login_id.getText().toString();
        pwd = login_pwd.getText().toString();
        
        // 테스트 구현
        login_progressbar.setVisibility(View.VISIBLE);
        startLogin(new LoginRequest(id, pwd));
    }

    private void startLogin(LoginRequest data) {
        service.userLogin(data).enqueue(new Callback<CodeResponse>() {

            @Override
            public void onResponse(Call<CodeResponse> call, Response<CodeResponse> response) {
                CodeResponse code = response.body();
                if(code.getCode() == 200)
                    Toast.makeText(LoginActivity.this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                login_progressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<CodeResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                login_progressbar.setVisibility(View.INVISIBLE);
            }
        });
    }
}