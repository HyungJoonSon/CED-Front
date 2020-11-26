package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ced.R;
import com.example.ced.adapter.JobAdapter;
import com.example.ced.data.JobDataResponse;
import com.example.ced.data.JobListResponse;
import com.example.ced.network.RetrofitClient;
import com.example.ced.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JobInfoListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton back;
    private ListView listview;
    private List<JobDataResponse> jobList;
    private JobAdapter adapter;
    private ServiceApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info_list);                     // xml과 java 소스 연결

        service = RetrofitClient.getClient().create(ServiceApi.class);  // 통신을 위한 ServiceApi 생성

        back = findViewById(R.id.infobackbtn);
        listview = findViewById(R.id.listview_jobinfo);                 // xml의 컴포넌트와 연결

        setListView(getIntent().getStringExtra("jobSelect"));     // listview에 adapter와 datalist를 연결하는 함수(통신 기반)

        // list의 Item을 클릭했을 때 일어나는 이벤트
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), JobInfoActivity.class);
                intent.putExtra("JobField", jobList.get(position).getJobField());
                intent.putExtra("JobName", jobList.get(position).getJobName());
                intent.putExtra("JobInfo", jobList.get(position).getJobInfo());
                startActivity(intent);                                  // 위 정보를 가지고 다음 액티비티로 넘어간다.
            }
        });

        // 뒤로가기 버튼
        back.setOnClickListener(new View.OnClickListener() {            // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }                   // 해당 액티비티 종료
        });
    }

    protected void setListView(String data) {                           // ListView를 Setting 하기 위한 함수(통신 기반)
        service.readJobSummary(data).enqueue(new Callback<JobListResponse>() {
            @Override
            public void onResponse(Call<JobListResponse> call, Response<JobListResponse> response) {
                JobListResponse temp = response.body();                 // 응답받은 정보를 받아와서
                jobList = temp.getResult();                             // 정보 중에서 code를 제외하고 jobList에 넣어준다.

                adapter = new JobAdapter(JobInfoListActivity.this, jobList);    // listview의 adapter를 만들어주고
                listview.setAdapter(adapter);                                       // listview와 adapter를 연결한다.
            }

            @Override
            public void onFailure(Call<JobListResponse> call, Throwable t) {
                Toast.makeText(JobInfoListActivity.this, "검색 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("검색 오류 발생", t.getMessage());              // 오류가 발생하면 Toast 메시지를 보내고 log를 남긴다.
            }
        });
    }

    @Override
    public void onClick(View v) {                                       // interface용 함수
    }
}