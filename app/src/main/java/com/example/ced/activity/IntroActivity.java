package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ced.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);        // xml, java 연결

        Handler handler = new Handler();                // 해당 스레드에 핸들러 연결
        handler.postDelayed(new Runnable() {            // Runnalbe 객체를 해당 쓰레드에서 지연 실행
            @Override
            public void run() {                         // Runnalbe 객체의 run method를 오버라이딩(다음화면으로 넘어감)
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500); // 0.5초 뒤에 Runner객체 실행하도록 함
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}