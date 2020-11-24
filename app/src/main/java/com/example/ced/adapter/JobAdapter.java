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

    public JobAdapter(@NonNull Context context, List<JobData> list) {
        super(context, 0,list);
    }

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
