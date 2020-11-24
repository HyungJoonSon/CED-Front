package com.example.ced.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ced.R;
import com.example.ced.data.InterviewData;

import java.util.List;

public class InterviewAdapter extends ArrayAdapter {

    public InterviewAdapter(@NonNull Context context, List<InterviewData> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_interview_item, parent, false);
        }

        InterviewData interviewData = (InterviewData) getItem(position);

        TextView jobName = convertView.findViewById(R.id.jobinterview_name);

        jobName.setText(interviewData.getJobName());

        return convertView;
    }
}

