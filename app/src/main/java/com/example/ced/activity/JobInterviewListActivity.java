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
import com.example.ced.data.JobInterview;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JobInterviewListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton backButton;
    private ListView interviewList;
    private InterviewAdapter adapter;
    private List<JobInterview> jobInterviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_interview_list);                    // xml과 java 소스 연결

        backButton = findViewById(R.id.interviewBackBtn);
        interviewList = findViewById(R.id.interviewList);                   // xml의 컴포넌트와 연결

        jobInterviewList = readInterview();                                // ListView를 위한 정보를 받아옴(함수 사용)
        adapter = new InterviewAdapter(JobInterviewListActivity.this, jobInterviewList);
        interviewList.setAdapter(adapter);                                  // 통신 기반이 아니라서 어뎁터와 ListView를 Create 함수에서 연결

        backButton.setOnClickListener(new View.OnClickListener() {          // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }                       // 해당 액티비티 종료
        });

        // list의 Item을 클릭했을 때 일어나는 이벤트
        interviewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), JobInterviewActivity.class);
                intent.putExtra("jobName", jobInterviewList.get(position).getJobName());
                intent.putExtra("jobUrl", jobInterviewList.get(position).getUrl());
                startActivity(intent);                                      // 해당 정보를 가지고 다음 액티비티로 넘어감
            }
        });
    }

    public List<JobInterview> readInterview() {                            // asset 폴더에서 정보를 읽기위한 함수
        List<JobInterview> list = new ArrayList<JobInterview>();                      // 읽은 정보를 담을 List(ListView에 사용됨)
        String nonJson = null;                                              // string 형태의 json을 담을 변수
        try {
            InputStream is = getResources().getAssets().open("jobInterview.json");  // 해당 파일을 asset폴더에서 읽음

            int byteSize = is.available();                                  // byte 단위로 읽으므로 byte의 총 개수 구해서
            byte[] buffer = new byte[byteSize];                             // string 전의 byte 코드를 넣을 buffer 생성

            is.read(buffer);                                                // byte 배열에 읽어온 정보 저장
            is.close();                                                     // Stream을 close함

            nonJson = new String(buffer, "UTF-8");               // 해당 byte 배열을 string으로 변환해줌
        } catch (IOException e) {
            e.printStackTrace();                                            // 예외처리
        }

        JsonParser Parser = new JsonParser();                               // 파싱을 위한 객체
        JsonObject jsonObject = (JsonObject) Parser.parse(nonJson);         // 해당 문자열을 json 객체로 만들어줌
        JsonArray array = (JsonArray) jsonObject.get("list");               // json 객체에서 list라는 항목을 json 배열에 담아줌

        for (int i = 0; i < array.size(); i++) {                             // json 배열의 크기만큼 반복
            JsonObject jObject = (JsonObject) array.get(i);                 // 임시로 담을 공간과 해당 json객체를 list에 넣어주는 작업
            list.add(new JobInterview(jObject.get("job").getAsString(), jObject.get("url").getAsString()));
        }

        return list;                                                        // 만들어진 list 반환
    }

    @Override
    public void onClick(View v) {                                           // interface용 함수
    }
}