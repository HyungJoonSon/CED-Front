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
import com.example.ced.activity.JobInfoActivity;
import com.example.ced.activity.JobInterviewActivity;
import com.example.ced.activity.JobSelectorActivity;
import com.example.ced.activity.MbtiActivity;
import com.example.ced.activity.RecordListActivity;

public class FragJob extends Fragment {
    private View view;
    private Button jobDataBtn;
    private Button jobInterVeiwBtn;

    private Button button_ISTJ;
    private Button button_ISFJ;
    private Button button_INFJ;
    private Button button_INTJ;

    private Button button_ISTP;
    private Button button_ISFP;
    private Button button_INFP;
    private Button button_INTP;

    private Button button_ESTP;
    private Button button_ESFP;
    private Button button_ENFP;
    private Button button_ENTP;

    private Button button_ESTJ;
    private Button button_ESFJ;
    private Button button_ENFJ;
    private Button button_ENTJ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_job, container, false);

        jobDataBtn = view.findViewById(R.id.button_information);
        jobInterVeiwBtn = view.findViewById(R.id.button_interview);
        button_ISTJ = view.findViewById(R.id.button_ISTJ);
        button_ISFJ = view.findViewById(R.id.button_ISFJ);
        button_INFJ = view.findViewById(R.id.button_INFJ);
        button_INTJ = view.findViewById(R.id.button_INTJ);

        button_ISTP = view.findViewById(R.id.button_ISTP);
        button_ISFP = view.findViewById(R.id.button_ISFP);
        button_INFP = view.findViewById(R.id.button_INFP);
        button_INTP = view.findViewById(R.id.button_INTP);

        button_ESTP = view.findViewById(R.id.button_ESTP);
        button_ESFP = view.findViewById(R.id.button_ESFP);
        button_ENFP = view.findViewById(R.id.button_ENFP);
        button_ENTP = view.findViewById(R.id.button_ENTP);

        button_ESTJ = view.findViewById(R.id.button_ESTJ);
        button_ESFJ = view.findViewById(R.id.button_ESFJ);
        button_ENFJ = view.findViewById(R.id.button_ENFJ);
        button_ENTJ = view.findViewById(R.id.button_ENTJ);

        jobDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobSelectorActivity.class);
                startActivity(intent); //다음화면으로 넘어감
            }
        });

        jobInterVeiwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobInterviewActivity.class);
                startActivity(intent);
            }
        });

        button_ISTJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ISTJ");
                startActivity(intent);
            }
        });

        button_ISFJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ISFJ");
                startActivity(intent);
            }
        });

        button_INFJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "INFJ");
                startActivity(intent);
            }
        });

        button_INTJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "INTJ");
                startActivity(intent);
            }
        });

        button_ISTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ISTP");
                startActivity(intent);
            }
        });

        button_ISFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ISFP");
                startActivity(intent);
            }
        });

        button_INFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "INFP");
                startActivity(intent);
            }
        });

        button_INTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "INTP");
                startActivity(intent);
            }
        });

        button_ESTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ESTP");
                startActivity(intent);
            }
        });

        button_ESFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ESFP");
                startActivity(intent);
            }
        });

        button_ENFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ENFP");
                startActivity(intent);
            }
        });

        button_ENTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ENTP");
                startActivity(intent);
            }
        });

        button_ESTJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ESTJ");
                startActivity(intent);
            }
        });

        button_ESFJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ESFJ");
                startActivity(intent);
            }
        });

        button_ENFJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ENFJ");
                startActivity(intent);
            }
        });

        button_ENTJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MbtiActivity.class);
                intent.putExtra("mbti", "ENTJ");
                startActivity(intent);
            }
        });

        return view;
    }
}
