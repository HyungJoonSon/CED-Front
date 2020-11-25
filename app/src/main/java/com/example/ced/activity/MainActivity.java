package com.example.ced.activity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ced.R;
import com.example.ced.fragdata.FragJob;
import com.example.ced.fragdata.FragChallenge;
import com.example.ced.fragdata.FragUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentmanager;
    private FragmentTransaction fragmenttransaction;
    private FragUser fraguser;
    private FragJob fragjob;
    private FragChallenge fragchallenge;
    private Intent intent;
    private String UserID;
    private String UserName;
    private String UserTime;
    private String famousName;
    private String famousSaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                             // xml, java 연결
        loadSaying();                                                       // 함수를 통해 명언을 정함

        bottomNavigationView = findViewById(R.id.bottomnavibar);                // xml의 컴포넌트와 연결
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {   // 네비바의 터치를 기반으로 fragment 전환
                switch (item.getItemId()) {                                     // user, job, rank 순으로 0,1,2
                    case R.id.action_user:                                      // user라면 0으로 프래그먼트 전환
                        setFragment(0);
                        break;
                    case R.id.action_job:                                       // job라면 1으로 프래그먼트 전환
                        setFragment(1);
                        break;
                    case R.id.action_challenge:                                 // challenge라면 2으로 프래그먼트 전환
                        setFragment(2);
                        break;
                }
                return true;                                                    // 프래그먼트는 항상 바꿀 수 있으므로 true 리턴
            }
        });
        fragjob = new FragJob();                                            // 각 프래그먼트를 연결
        fraguser = new FragUser();                                          // 각 프래그먼트를 연결
        fragchallenge = new FragChallenge();                                     // 각 프래그먼트를 연결

        intent = getIntent();                                               // 현재 intent를 받아옴
        Bundle bundle = new Bundle();                                       // 데이터를 위한 객체 생성
        UserID = intent.getStringExtra("UserID");                     // login에서 넘겨준 정보 할당
        UserName = intent.getStringExtra("UserName");
//        UserTime = intent.getStringExtra("Time");
        // login에서 넘겨준 정보 할당
        bundle.putString("UserID", UserID);                                 // 프래그먼트에 넘겨줄 정보를 번들에 넣어줌
        bundle.putString("UserName", UserName);
//        bundle.putString("Time",UserTime);
        // 프래그먼트에 넘겨줄 정보를 번들에 넣어줌
        fragchallenge.setArguments(bundle);                                 // 해당 정보는 알아서 사용하면 됨

        bundle.putString("FamousName", famousName);                         // 프래그먼트에 넘겨줄 정보를 번들에 넣어줌
        bundle.putString("FamousSaying", famousSaying);                     // 프래그먼트에 넘겨줄 정보를 번들에 넣어줌
        fraguser.setArguments(bundle);                                      // user 프래그먼트에 정보를 전달
                                                                            // 각자 필요한 정보를 전달 받으면 됨(challenge라면 똑같이 적으면 됨)
        setFragment(0);                                                     // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택
    }

    // 프래그먼트 교체가 일어나는 함수(메소드)
    public void setFragment(int n) {
        fragmentmanager = getSupportFragmentManager();                      // 해당 액티비티의 프래그먼트 매니저를 받음
        fragmenttransaction = fragmentmanager.beginTransaction();           // 매니저로부터 화면 전환을 위한 객체를 받음
        switch (n) {
            case 0:
                fragmenttransaction.replace(R.id.main_frame, fraguser);         // 전환을 위한 객체에 정보를 전달
                fragmenttransaction.commit();                                   // 정보를 기반으로 분활화면(프래그먼트) 전환
                break;
            case 1:
                fragmenttransaction.replace(R.id.main_frame, fragjob);          // 전환을 위한 객체에 정보를 전달
                fragmenttransaction.commit();                                   // 정보를 기반으로 분활화면(프래그먼트) 전환
                break;
            case 2:
                fragmenttransaction.replace(R.id.main_frame, fragchallenge);    // 전환을 위한 객체에 정보를 전달
                fragmenttransaction.commit();                                   // 정보를 기반으로 분활화면(프래그먼트) 전환
                break;
        }
    }

    public void loadSaying() {
        Random random = new Random();                           // 랜덤으로 뽑기위한 객체
        String json = null;                                     // json을 문자열로 바꾼 정보를 저장하기 위한 객체

        try {
            InputStream is = getResources().getAssets().open("famousSaying.json");      // asset 폴더에서 읽을 파일 지정

            int size = is.available();                          // 파일 내의 크기를 확인함
            byte[] buffer = new byte[size];                     // 크기만큼 byte 배열 할당

            is.read(buffer);                                    // byte 배열에 읽어온 정보 저장
            is.close();                                         // Stream을 close함

            json = new String(buffer, "UTF-8");     // byte 배열을 UTF-8의 형태로 문자열 변환
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JsonParser Parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) Parser.parse(json);
        JsonArray array = (JsonArray) jsonObject.get("list");   // 변환한 문자열에서 list 배열을 받아옴

        jsonObject = (JsonObject) array.get(random.nextInt(array.size()));
        famousName = jsonObject.get("name").getAsString();
        famousSaying = jsonObject.get("saying").getAsString();  // 랜덤으로 명언을 정함
    }
}