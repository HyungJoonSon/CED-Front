package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ced.R;

import com.example.ced.data.JobSummaryResponse;
import com.example.ced.data.JobDetailResponse;
import com.example.ced.data.JobSummary;




public class JobInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        Intent jobinfoIntent=getIntent();
        String testString;
        Button back;
        TextView test=(TextView)findViewById(R.id.intendtest);
        testString=jobinfoIntent.getStringExtra("jobfield");
        if(testString!=null){
            test.setText(testString);
        }

        back=(Button)findViewById(R.id.testback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

    }
}