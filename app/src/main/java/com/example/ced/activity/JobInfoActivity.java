package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ced.R;

public class JobInfoActivity extends AppCompatActivity {
    private ImageButton backButton;
    private TextView jobName;
    private TextView jobField;
    private TextView jobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);                   // xml과 java 소스 연결

        backButton = findViewById(R.id.jobdetailBack);
        jobName = findViewById(R.id.jobdetailname);
        jobField = findViewById(R.id.jobdetailfield);
        jobInfo = findViewById(R.id.jobdetailInfo);                     // xml의 컴포넌트와 연결

        jobName.setText(getIntent().getStringExtra("JobName"));     // TextView의 내용을 전 액티비티에서 받은 내용으로 수정
        jobField.setText(getIntent().getStringExtra("JobField"));   // TextView의 내용을 전 액티비티에서 받은 내용으로 수정
        jobInfo.setText(getIntent().getStringExtra("JobInfo"));     // TextView의 내용을 전 액티비티에서 받은 내용으로 수정

        backButton.setOnClickListener(new View.OnClickListener() {      // 닫기 버튼

            @Override
            public void onClick(View v) {
                finish();
            }                   // 해당 액티비티를 끝냄
        });
    }
}