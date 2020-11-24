package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ced.R;

public class JobDetailActivity extends AppCompatActivity {
    private ImageButton backButton;
    private TextView jobName;
    private TextView jobField;
    private TextView jobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        backButton = findViewById(R.id.jobdetailBack);
        jobName = findViewById(R.id.jobdetailname);
        jobField = findViewById(R.id.jobdetailfield);
        jobInfo = findViewById(R.id.jobdetailInfo);

        jobName.setText(getIntent().getStringExtra("JobName"));
        jobField.setText(getIntent().getStringExtra("JobField"));
        jobInfo.setText(getIntent().getStringExtra("JobInfo"));

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}