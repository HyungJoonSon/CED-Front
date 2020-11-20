package com.example.ced.activity;

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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentmanager;
    private FragmentTransaction fragmenttransaction;
    private FragUser fraguser;
    private FragJob fragjob;
    private FragChallenge fragrank;
    private Intent intent;
    private String UserID;
    private String UserName;
    private String famousName;
    private String famousSaying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSaying();

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
        fragrank = new FragChallenge();

        intent = getIntent();
        Bundle bundle = new Bundle();
        UserID = intent.getStringExtra("UserID");
        UserName = intent.getStringExtra("UserName");
        bundle.putString("UserID", UserID);
        bundle.putString("UserName", UserName);
        bundle.putString("FamousName", famousName);
        bundle.putString("FamousSaying", famousSaying);
        fraguser.setArguments(bundle);

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

    public void loadSaying() {
        Random random = new Random();
        String json = null;

        try {
            InputStream is = getResources().getAssets().open("famousSaying.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JsonParser Parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) Parser.parse(json);
        JsonArray array = (JsonArray) jsonObject.get("list");

        jsonObject = (JsonObject) array.get(random.nextInt(array.size()));
        famousName = jsonObject.get("name").getAsString();
        famousSaying = jsonObject.get("saying").getAsString();
    }
}