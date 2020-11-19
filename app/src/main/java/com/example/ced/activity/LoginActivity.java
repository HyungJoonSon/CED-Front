package com.example.ced.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ced.R;
import com.example.ced.data.CodeResponse;
import com.example.ced.data.LoginRequest;
import com.example.ced.data.LoginResponse;
import com.example.ced.network.RetrofitClient;
import com.example.ced.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText login_id;
    private EditText login_pwd;
    private Button login_btn;
    private Button join_btn;
    private String id;
    private String pwd;
    private boolean input_id;
    private boolean input_pwd;
    private ProgressBar login_progressbar;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        login_id = findViewById(R.id.login_id);
        login_pwd = findViewById(R.id.login_pwd);

        login_btn = findViewById(R.id.login_btn);
        join_btn = findViewById(R.id.join_btn);
        login_progressbar = findViewById(R.id.login_pbar);

        input_id = false;
        input_pwd = false;

        login_btn.setEnabled(false);

        login_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cor_id = s.toString();

                if (cor_id.length() > 0) {
                    input_id = true;
                    if (input_pwd)
                        login_btn.setEnabled(true);
                    else
                        login_btn.setEnabled(false);
                } else {
                    input_id = false;
                    login_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        login_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cor_pwd = s.toString();

                if (cor_pwd.length() > 0) {
                    input_pwd = true;
                    if (input_id)
                        login_btn.setEnabled(true);
                    else
                        login_btn.setEnabled(false);
                } else {
                    input_pwd = false;
                    login_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
    }

    private void tryLogin() {
        id = login_id.getText().toString();
        pwd = login_pwd.getText().toString();

        // 테스트 구현 바람
        login_progressbar.setVisibility(View.VISIBLE);
        startLogin(new LoginRequest(id, pwd));
    }

    private void startLogin(LoginRequest data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse user = response.body();
                if (user.getCode() == 200) {
                    Toast.makeText(LoginActivity.this, user.getUserName() + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("UserID", id);
                    intent.putExtra("UserName", user.getUserName());
                    startActivity(intent); //다음화면으로 넘어감
                    finish();
                } else if (user.getCode() == 204)
                    Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                login_progressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "통신 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("통신 오류 발생", t.getMessage());
                login_progressbar.setVisibility(View.INVISIBLE);
            }
        });
    }
}