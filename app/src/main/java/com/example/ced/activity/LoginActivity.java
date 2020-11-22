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
        setContentView(R.layout.activity_login);                        // xml, java 연결

        service = RetrofitClient.getClient().create(ServiceApi.class);  // 통신을 위한 ServiceApi 생성

        login_id = findViewById(R.id.login_id);
        login_pwd = findViewById(R.id.login_pwd);

        login_btn = findViewById(R.id.login_btn);
        join_btn = findViewById(R.id.join_btn);
        login_progressbar = findViewById(R.id.login_pbar);              // xml의 컴포넌트와 각각 연결

        input_id = false;
        input_pwd = false;                                              // EditText에 문자가 있는지 확인하는 변수 초기화

        login_btn.setEnabled(false);                                    // 버튼 비활성화

        login_id.addTextChangedListener(new TextWatcher() {             // EditText에서 문자의 변화를 감지하는 함수
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {    // 바뀌기 전에는 할 필요 없음
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {       // text가 바뀌고 있는 중 모션
                String cor_id = s.toString();

                if (cor_id.length() > 0) {                              // 만약 입력된 문자가 있다면
                    input_id = true;                                    // input_id을 true로 초기화하고
                    if (input_pwd)                                          // password도 입력이 되어 있다면
                        login_btn.setEnabled(true);                         // 버튼을 활성화 해줌
                    else                                                    // 입력이 안되어 있다면
                        login_btn.setEnabled(false);                        // 버튼을 비활성화 함
                } else {                                                // 만약 입력된 문자가 없다면
                    input_id = false;                                   // input_id를 false로 초기화하고
                    login_btn.setEnabled(false);                        // 버튼을 비활성화 함
                }
            }

            @Override
            public void afterTextChanged(Editable s) {                  // text가 바뀐 후에는 할 필요 없음(어차피 바뀌면서 함)
            }
        });

        login_pwd.addTextChangedListener(new TextWatcher() {             // EditText에서 문자의 변화를 감지하는 함수
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {    // 바뀌기 전에는 할 필요 없음
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {       // text가 바뀌고 있는 중 모션
                String cor_pwd = s.toString();

                if (cor_pwd.length() > 0) {                             // 만약 입력된 문자가 있다면
                    input_pwd = true;                                   // input_pwd을 true로 초기화하고
                    if (input_id)                                           // password도 입력이 되어 있다면
                        login_btn.setEnabled(true);                         // 버튼을 활성화 해줌
                        else                                                // 입력이 안되어 있다면
                        login_btn.setEnabled(false);                        // 버튼을 비활성화 함
                } else {                                                // 만약 입력된 문자가 없다면
                    input_pwd = false;                                  // input_pwd을 false로 초기화하고
                    login_btn.setEnabled(false);                        // 버튼을 비활성화 함
                }
            }

            @Override
            public void afterTextChanged(Editable s) {                  // text가 바뀐 후에는 할 필요 없음(어차피 바뀌면서 함)
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {       // 버튼을 클릭 했을 때 모션을 정의
            @Override
            public void onClick(View v) {
                tryLogin();
            }                 // 로그인을 시도함
        });

        join_btn.setOnClickListener(new View.OnClickListener() {        // 버튼을 클릭 했을 때 모션을 정의
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);                                  // 회원가입 액티비티로 넘어감
            }
        });
    }

    private void tryLogin() {
        id = login_id.getText().toString();                             // id에 입력된 input을 넣어줌
        pwd = login_pwd.getText().toString();                           // password에 입력된 input을 넣어줌

        login_progressbar.setVisibility(View.VISIBLE);                  // progressbar를 활성화 해주고
        startLogin(new LoginRequest(id, pwd));                          // 로그인을 시작함
    }

    private void startLogin(LoginRequest data) {                        // 로그인을 하는 함수(이전에 설명했으므로 요약함)
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse user = response.body();                   // 응답받은 body의 객체를 넣고 code에 따라 활동이 나뉨
                if (user.getCode() == 200) {                            // 로그인 성공이라면
                    Toast.makeText(LoginActivity.this, user.getUserName() + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("UserID", id);
                    intent.putExtra("UserName", user.getUserName());
                    startActivity(intent);                              // 성공이라면 Main 액티비티로 넘어가고 현 액티비티 종료
                    finish();
                } else if (user.getCode() == 204)                       // 아이디가 존재하지 않을 경우
                    Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                else                                                    // 비밀번호가 일치하지 않을 경우
                    Toast.makeText(LoginActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                login_progressbar.setVisibility(View.INVISIBLE);        // 로그인 모션이 끝났으니 progressbar 비활성화
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "통신 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("통신 오류 발생", t.getMessage());
                login_progressbar.setVisibility(View.INVISIBLE);        // 통신의 오류가 생김, progressbar 비활성화
            }
        });
    }
}