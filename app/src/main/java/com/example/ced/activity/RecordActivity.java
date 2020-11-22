package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ced.R;

public class RecordActivity extends AppCompatActivity {

    private ImageButton backbtn;
    private TextView titleview;
    private TextView dateview;
    private TextView contentview;
    private String title;
    private String date;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);                           // xml, java 연결

        backbtn = findViewById(R.id.recorddatabackbtn);
        titleview = findViewById(R.id.recordtitleview);
        dateview = findViewById(R.id.recorddateview);
        contentview = findViewById(R.id.recordcontentview);                 // xml의 컴포넌트와 각각 연결

        title = getIntent().getStringExtra("title");                    // intent를 통해 넘어온 데이터를 변수에 할당해줌
        date = getIntent().getStringExtra("date");                      // intent를 통해 넘어온 데이터를 변수에 할당해줌
        content = getIntent().getStringExtra("content");                // intent를 통해 넘어온 데이터를 변수에 할당해줌

        titleview.setText(title);                                           // 할당해준 변수로 TextView 초기화
        dateview.setText("만든 날짜: " + date);                              // 할당해준 변수로 TextView 초기화
        contentview.setText(content);                                       // 할당해준 변수로 TextView 초기화

        backbtn.setOnClickListener(new View.OnClickListener() {             // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }                       // 해당 액티비티를 끝냄
        });
    }
}