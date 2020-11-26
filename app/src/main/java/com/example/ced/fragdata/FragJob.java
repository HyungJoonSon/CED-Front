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
import com.example.ced.activity.JobInterviewListActivity;
import com.example.ced.activity.JobSelectorActivity;
import com.example.ced.activity.JobMbtiActivity;

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
        button_INTJ = view.findViewById(R.id.button_INTJ);                // xml의 컴포넌트와 연결

        button_ISTP = view.findViewById(R.id.button_ISTP);
        button_ISFP = view.findViewById(R.id.button_ISFP);
        button_INFP = view.findViewById(R.id.button_INFP);
        button_INTP = view.findViewById(R.id.button_INTP);                // xml의 컴포넌트와 연결

        button_ESTP = view.findViewById(R.id.button_ESTP);
        button_ESFP = view.findViewById(R.id.button_ESFP);
        button_ENFP = view.findViewById(R.id.button_ENFP);
        button_ENTP = view.findViewById(R.id.button_ENTP);                // xml의 컴포넌트와 연결

        button_ESTJ = view.findViewById(R.id.button_ESTJ);
        button_ESFJ = view.findViewById(R.id.button_ESFJ);
        button_ENFJ = view.findViewById(R.id.button_ENFJ);
        button_ENTJ = view.findViewById(R.id.button_ENTJ);                // xml의 컴포넌트와 연결

        jobDataBtn.setOnClickListener(new View.OnClickListener() {              // 직업 정보 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobSelectorActivity.class);
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        jobInterVeiwBtn.setOnClickListener(new View.OnClickListener() {         // 직업 인터뷰 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobInterviewListActivity.class);
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ISTJ.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ISTJ");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ISFJ.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ISFJ");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_INFJ.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "INFJ");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_INTJ.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "INTJ");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ISTP.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ISTP");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ISFP.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ISFP");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_INFP.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "INFP");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_INTP.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "INTP");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ESTP.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ESTP");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ESFP.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ESFP");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ENFP.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ENFP");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ENTP.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ENTP");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ESTJ.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ESTJ");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ESFJ.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ESFJ");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ENFJ.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ENFJ");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        button_ENTJ.setOnClickListener(new View.OnClickListener() {             // MBTI 버튼 연결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobMbtiActivity.class);
                intent.putExtra("mbti", "ENTJ");                    // 다음 액티비티에 전달할 MBTI 정보
                startActivity(intent);                                          // 해당하는 액티비티로 넘어감
            }
        });

        return view;
    }
}
