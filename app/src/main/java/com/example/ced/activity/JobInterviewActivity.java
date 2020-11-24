package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ced.R;
import com.example.ced.adapter.InterviewAdapter;
import com.example.ced.data.InterviewData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JobInterviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton backButton;
    private ListView interviewList;
    private InterviewAdapter adapter;
    private List<InterviewData> interviewDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_interview);

        backButton = findViewById(R.id.interviewBackBtn);
        interviewList = findViewById(R.id.interviewList);

        interviewDataList = readInterview();
        adapter = new InterviewAdapter(JobInterviewActivity.this, interviewDataList);
        interviewList.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        interviewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), InterviewActivity.class);
                intent.putExtra("jobName", interviewDataList.get(position).getJobName());
                intent.putExtra("jobUrl", interviewDataList.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    public List<InterviewData> readInterview() {
        List<InterviewData> list = new ArrayList<InterviewData>();
        String nonJson = null;
        try {
            InputStream is = getResources().getAssets().open("jobInterview.json");

            int byteSize = is.available();
            byte[] buffer = new byte[byteSize];

            is.read(buffer);                                    // byte 배열에 읽어온 정보 저장
            is.close();                                         // Stream을 close함

            nonJson = new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonParser Parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) Parser.parse(nonJson);
        JsonArray array = (JsonArray) jsonObject.get("list");     // 읽은 정보를 array에 담아줌

        for(int i = 0; i < array.size(); i++) {
            jsonObject = (JsonObject) array.get(i);
            list.add(new InterviewData(jsonObject.get("job").getAsString(), jsonObject.get("url").getAsString()));
        }

        return list;
    }

    @Override
    public void onClick(View v) {
    }
}