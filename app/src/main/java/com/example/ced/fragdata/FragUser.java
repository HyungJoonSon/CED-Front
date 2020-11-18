package com.example.ced.fragdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ced.R;
import com.example.ced.activity.LoginActivity;
import com.example.ced.activity.MainActivity;
import com.example.ced.activity.RecordActivity;
import com.example.ced.activity.SettingActivity;

public class FragUser extends Fragment {
    private View view;
    private TextView userName;
    private TextView famousSaying;
    private TextView famousName;
    private Button recordButton;
    private ImageButton settingButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);

        userName = view.findViewById(R.id.fragusername);
        famousName = view.findViewById(R.id.fragfname);
        famousSaying = view.findViewById(R.id.fragfsaying);
        recordButton = view.findViewById(R.id.recordbtn);
        settingButton = view.findViewById(R.id.settingbtn);

        // 액티비티에서 아이디를 받아와서 출력
        userName.setText(getArguments().getString("UserName") + "님");
        famousName.setText("- " + getArguments().getString("FamousName") + " -");
        famousSaying.setText(getArguments().getString("FamousSaying"));

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent); //다음화면으로 넘어감
            }
        });

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecordActivity.class);
                startActivity(intent); //다음화면으로 넘어감
            }
        });

        return view;
    }
}
