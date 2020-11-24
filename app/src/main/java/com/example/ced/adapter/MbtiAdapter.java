package com.example.ced.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ced.R;

import java.util.List;

public class MbtiAdapter extends ArrayAdapter {

    public MbtiAdapter(@NonNull Context context, List<String> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_mbti_item, parent, false);
        }

        String mbtiJob = (String) getItem(position);

        TextView mbtiJobName = convertView.findViewById(R.id.mbtiTextView);

        mbtiJobName.setText(mbtiJob);

        return convertView;
    }
}
