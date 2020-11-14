package com.example.ced.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ced.R;
import com.example.ced.fragdata.FragJob;
import com.example.ced.fragdata.FragRank;
import com.example.ced.fragdata.FragUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentmanager;
    private FragmentTransaction fragmenttransaction;
    private FragUser fraguser;
    private FragJob fragjob;
    private FragRank fragrank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomnavibar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_user:
                        setFragment(0);
                        break;
                    case R.id.action_job:
                        setFragment(1);
                        break;
                    case R.id.action_rank:
                        setFragment(2);
                        break;
                }
                return true;
            }
        });
        fragjob = new FragJob();
        fraguser = new FragUser();
        fragrank = new FragRank();
        setFragment(0); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택
    }

    // 프래그먼트 교체가 일어나는 함수(메소드)
    public void setFragment(int n) {
        fragmentmanager = getSupportFragmentManager();
        fragmenttransaction = fragmentmanager.beginTransaction();
        switch (n) {
            case 0:
                fragmenttransaction.replace(R.id.main_frame, fraguser);
                fragmenttransaction.commit();
                break;
            case 1:
                fragmenttransaction.replace(R.id.main_frame, fragjob);
                fragmenttransaction.commit();
                break;
            case 2:
                fragmenttransaction.replace(R.id.main_frame, fragrank);
                fragmenttransaction.commit();
                break;

        }

    }
}