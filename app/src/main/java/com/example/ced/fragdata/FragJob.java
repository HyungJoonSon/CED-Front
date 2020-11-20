package com.example.ced.fragdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ced.R;
import com.example.ced.activity.JobSelectorActivity;
import com.example.ced.activity.RecordListActivity;

public class FragJob extends Fragment {
    private View view;
    private Button jobDataBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_job, container, false);

        jobDataBtn = view.findViewById(R.id.button_information);

        jobDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobSelectorActivity.class);
                startActivity(intent); //다음화면으로 넘어감
            }
        });

        return view;
    }
}
