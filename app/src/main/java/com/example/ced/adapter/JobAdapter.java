package com.example.ced.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ced.R;
import com.example.ced.data.JobData;

import java.util.List;

public class JobAdapter extends ArrayAdapter {

    // 어뎁터를 만듦
    // 상속받은 class 이므로 super class를 사용한다.
    // 여기서 첫번째는 액티비티, 두번째는 list 레이아웃, 세번째는 정보가 있는 List이다.
    // 레이아웃을 지정하지 않는 이유 : 만들면서 view를 지정하기 때문임
    // 즉, adapter를 만들 때 지정하지 않고, adapter 내부의 view를 만들 때 레이아웃을 지정한다.
    public JobAdapter(@NonNull Context context, List<JobData> list) {
        super(context, 0, list);
    }

    // 뷰를 생성하는 함수(뷰를 생성할 때, 해당하는 자리가 null일 때 새로운 뷰를 만듦)
    // 뷰의 내용을 수정(List의 정보 기반)하고 수정하거나 만들어진 View를 반환한다.
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_job_item, parent, false);
        }

        JobData jobSummary = (JobData) getItem(position);

        TextView jobname = convertView.findViewById(R.id.jobsummaryname);
        TextView jobfield = convertView.findViewById(R.id.jobsummaryfield);

        jobname.setText(jobSummary.getJobName());
        jobfield.setText(jobSummary.getJobField());

        return convertView;
    }
}
