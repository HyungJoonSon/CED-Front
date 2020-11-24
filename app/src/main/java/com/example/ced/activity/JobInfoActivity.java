package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ced.R;
import com.example.ced.data.JobSummary;
import com.example.ced.data.JobSummaryResponse ;
import com.example.ced.network.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class JobInfoActivity extends AppCompatActivity {
    private ImageButton back;
    private ServiceApi service;
    private List<JobSummary> items;
    private String jobselected;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        Intent jobinfoIntent=getIntent();
//        String testString;
//        TextView test=(TextView)findViewById(R.id.tester);
//        testString=jobinfoIntent.getStringExtra("jobfield");
//        if(testString!=null){
//            test.setText();
//        }
        items = new ArrayList<JobSummary>();
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);
//
//
//        jobselected=jobinfoIntent.getStringExtra("jobfeild");
//        joblisting(jobselected);
//        adapter.notifyDataSetChanged();


        //닫기 버튼
        back=(ImageButton) findViewById(R.id.infobackbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

    }

    protected void joblisting(String data){
        service.readJobSummary(data).enqueue(new Callback<JobSummaryResponse>() {
            @Override
            public void onResponse(Call<JobSummaryResponse> call, Response<JobSummaryResponse> response) {
            JobSummaryResponse info =response.body();
                items=info.getResult();


            }

            @Override
            public void onFailure(Call<JobSummaryResponse> call, Throwable t) {

            }
        });

    }
}