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
import com.example.ced.data.JobData;
import com.example.ced.data.JobDataResponse;
import com.example.ced.network.RetrofitClient;
import com.example.ced.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JobInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton back;
    private ListView listview;
    private List<JobData> jobList;
    private JobAdapter adapter;
    private ServiceApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        back = (ImageButton) findViewById(R.id.infobackbtn);
        listview = findViewById(R.id.listview_jobinfo);
        setListView(getIntent().getStringExtra("jobSelect"));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), JobDetailActivity.class);
                intent.putExtra("JobField", jobList.get(position).getJobField());
                intent.putExtra("JobName", jobList.get(position).getJobName());
                intent.putExtra("JobInfo", jobList.get(position).getJobInfo());
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setListView(String data) {
        service.readJobSummary(data).enqueue(new Callback<JobDataResponse>() {
            @Override
            public void onResponse(Call<JobDataResponse> call, Response<JobDataResponse> response) {
                JobDataResponse temp = response.body();
                jobList = temp.getResult();

                adapter = new JobAdapter(JobInfoActivity.this, jobList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JobDataResponse> call, Throwable t) {
                Toast.makeText(JobInfoActivity.this, "검색 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("검색 오류 발생", t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
    }
}