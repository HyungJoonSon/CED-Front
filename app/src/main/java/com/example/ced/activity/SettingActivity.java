package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ced.R;

public class SettingActivity extends AppCompatActivity {

    private ImageButton settingBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);                            // xml, java 연결

        settingBackButton = findViewById(R.id.settingbackbtn);                // xml의 컴포넌트와 각각 연결
                                                                              // 해당 액티비티는 상의 필요

        settingBackButton.setOnClickListener(new View.OnClickListener() {     // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }                         // 해당 액티비티 종료
        });
    }
}