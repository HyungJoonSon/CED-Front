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
        setContentView(R.layout.activity_record);

        backbtn = findViewById(R.id.recorddatabackbtn);
        titleview = findViewById(R.id.recordtitleview);
        dateview = findViewById(R.id.recorddateview);
        contentview = findViewById(R.id.recordcontentview);

        title = getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("date");
        content = getIntent().getStringExtra("content");

        titleview.setText(title);
        dateview.setText("만든 날짜: " + date);
        contentview.setText(content);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}