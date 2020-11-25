package com.example.ced.fragdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ced.R;
import com.example.ced.activity.RecordListActivity;
import com.example.ced.activity.SettingActivity;
import com.example.ced.activity.StopwatchActivity;

public class FragUser extends Fragment {

    private View view;
    private TextView userName;
    private TextView famousSaying;
    private TextView famousName;
    private Button recordButton;
    private Button studyButton;
    private ImageButton settingButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);                         // xml, java 연결

        userName = view.findViewById(R.id.fragusername);
        famousName = view.findViewById(R.id.fragfname);
        famousSaying = view.findViewById(R.id.fragfsaying);
        recordButton = view.findViewById(R.id.recordbtn);
        studyButton = view.findViewById(R.id.studybtn);
        settingButton = view.findViewById(R.id.settingbtn);                                 // xml의 컴포넌트와 각각 연결

        // 액티비티에서 닉네임, 명언, 명언을 사용한 사람의 이름를 받아와서 출력
        userName.setText(getArguments().getString("UserName") + "님");
        famousName.setText("- " + getArguments().getString("FamousName") + " -");
        famousSaying.setText(getArguments().getString("FamousSaying"));

        settingButton.setOnClickListener(new View.OnClickListener() {                       // setting 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);                                                      //다음화면으로 넘어감
            }
        });

        recordButton.setOnClickListener(new View.OnClickListener() {                        // record 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecordListActivity.class);
                intent.putExtra("UserID",getArguments().getString("UserID"));
                startActivity(intent);                                                      // 유저의 아이디와 함께 다음화면으로 넘어감
            }
        });

        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StopwatchActivity.class);
                intent.putExtra("UserName", getArguments().getString("UserName"));
                intent.putExtra("UserID", getArguments().getString("UserID"));
//                intent.putExtra("Time",getArguments().getString("Time"));
                startActivity(intent); //다음화면으로 넘어감
            }
        });
        
        return view;                                                                        // 해당 뷰를 반환(해당 뷰는 Main 액티비티에서 사용됨)
    }
}
