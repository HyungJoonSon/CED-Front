package com.example.ced.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.example.ced.data.JoinRequest;
import com.example.ced.network.RetrofitClient;
import com.example.ced.network.ServiceApi;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    private EditText join_id;
    private EditText join_pwd;
    private EditText join_name;
    private Button join_btn;
    private Button check_id;
    private Button check_name;
    private String id;
    private String pwd;
    private String name;
    private int noNest_id;
    private int noNest_name;
    private boolean input_id;
    private boolean input_pwd;
    private boolean input_name;
    private ProgressBar join_progressbar;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        join_id = findViewById(R.id.join_id);
        join_pwd = findViewById(R.id.join_pwd);
        join_name = findViewById(R.id.join_name);

        join_btn = findViewById(R.id.join_resigter);
        check_id = findViewById(R.id.check_id);
        check_name = findViewById(R.id.check_name);
        join_progressbar = findViewById(R.id.join_pbar);

        input_id = false;
        input_pwd = false;
        input_name = false;
        noNest_id = 0;
        noNest_name = 0;

        join_btn.setEnabled(false);
        check_id.setEnabled(false);
        check_name.setEnabled(false);

        join_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cor_id = s.toString();

                if (cor_id.length() > 3)
                    check_id.setEnabled(true);
                else
                    check_id.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        join_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cor_name = s.toString();

                if (cor_name.length() > 0)
                    check_name.setEnabled(true);
                else
                    check_name.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // 아이디 검사 버튼
        check_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join_progressbar.setVisibility(View.VISIBLE);
                boolean noSpecial = join_id.getText().toString().matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$");
                if (!noSpecial) {
                    Toast.makeText(JoinActivity.this, "아이디에 특수문자가 들어갔습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 특수문자 사용 여부 검사

                checkID(join_id.getText().toString());
                // 인터넷 연결 검사, 중복 검사

                input_id = true;

                if (input_name)
                    join_btn.setEnabled(true);
                else
                    join_btn.setEnabled(false);
            }
        });

        // 이름 검사 버튼
        check_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join_progressbar.setVisibility(View.VISIBLE);
                boolean noSpecial = join_name.getText().toString().matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$");
                if (!noSpecial) {
                    Toast.makeText(JoinActivity.this, "이름에 특수문자가 들어갔습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 특수문자 사용 여부 검사

                checkName(join_name.getText().toString());
                // 인터넷 연결 검사, 중복 검사

                input_name = true;

                if (input_id)
                    join_btn.setEnabled(true);
                else
                    join_btn.setEnabled(false);
            }
        });

        // 회원가입 버튼
        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd = join_pwd.getText().toString();

                if (pwd.length() < 8) {
                    Toast.makeText(JoinActivity.this, "비밀번호가 8자리 미만입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                id = join_id.getText().toString();
                name = join_name.getText().toString();

                // 테스트 구현 바람
                join_progressbar.setVisibility(View.VISIBLE);
                startJoin(new JoinRequest(id, pwd, name));
            }
        });
    }

    public void startJoin(JoinRequest data) {
        service.userJoin(data).enqueue(new Callback<CodeResponse>() {
            @Override
            public void onResponse(Call<CodeResponse> call, Response<CodeResponse> response) {
                CodeResponse code = response.body();
                join_progressbar.setVisibility(View.INVISIBLE);
                if (code.getCode() == 200) {
                    Toast.makeText(JoinActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<CodeResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "통신 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("통신 오류 발생", t.getMessage());
                join_progressbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void checkID(String checkId) {
        service.userCheckID(checkId).enqueue(new Callback<CodeResponse>() {
            @Override
            public void onResponse(Call<CodeResponse> call, Response<CodeResponse> response) {
                CodeResponse code = response.body();
                join_progressbar.setVisibility(View.INVISIBLE);

                if (code.getCode() == 200) {
                    Toast.makeText(JoinActivity.this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                    join_id.setClickable(false);
                    join_id.setFocusable(false);
                    check_id.setEnabled(false);
                } else
                    Toast.makeText(JoinActivity.this, "중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CodeResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "아이디 검사 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("아이디 검사 오류 발생", t.getMessage());
                join_progressbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void checkName(String checkName) {
        service.userCheckName(checkName).enqueue(new Callback<CodeResponse>() {
            @Override
            public void onResponse(Call<CodeResponse> call, Response<CodeResponse> response) {
                CodeResponse code = response.body();
                join_progressbar.setVisibility(View.INVISIBLE);

                if (code.getCode() == 200) {
                    Toast.makeText(JoinActivity.this, "사용 가능한 이름입니다.", Toast.LENGTH_SHORT).show();
                    join_name.setClickable(false);
                    join_name.setFocusable(false);
                    check_name.setEnabled(false);
                } else {
                    Toast.makeText(JoinActivity.this, "중복된 이름이 존재합니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CodeResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "이름 검사 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("이름 검사 오류 발생", t.getMessage());
                join_progressbar.setVisibility(View.INVISIBLE);
            }
        });
    }
}