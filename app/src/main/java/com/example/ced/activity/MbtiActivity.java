package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ced.R;
import com.example.ced.adapter.MbtiAdapter;
import com.example.ced.data.InterviewData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MbtiActivity extends AppCompatActivity {

    private ImageButton backButton;
    private ListView mbtiJobListView;
    private TextView mbtiTitle;
    private String mbti;
    private List<String> mbtiJobList;
    private MbtiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbti);

        backButton = findViewById(R.id.mbtibackbtn);
        mbtiJobListView = findViewById(R.id.mbtiListView);
        mbtiTitle = findViewById(R.id.mbtiTitle);

        mbti = getIntent().getStringExtra("mbti");
        mbtiTitle.setText(mbti);

        adapter = new MbtiAdapter(MbtiActivity.this, getMbtiList(mbti));
        mbtiJobListView.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public List<String> getMbtiList(String mbti) {
        List<String> list = new ArrayList<String>();
        String nonJson = null;
        try {
            InputStream is = getResources().getAssets().open("mbtijoblist.json");

            int byteSize = is.available();
            byte[] buffer = new byte[byteSize];

            is.read(buffer);                                    // byte 배열에 읽어온 정보 저장
            is.close();                                         // Stream을 close함

            nonJson = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonParser Parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) Parser.parse(nonJson);
        JsonArray array = (JsonArray) jsonObject.get("list");     // 읽은 정보를 array에 담아줌
        String temp = null;

        for (int i = 0; i < array.size(); i++) {
            JsonObject object = (JsonObject) array.get(i);
            if(object.get("mbtiname").getAsString().equals(mbti)) {
                temp = object.get("job").getAsString();
                break;
            }
        }

        String[] tempList = temp.split(", ");

        for(int i = 0; i< tempList.length; i++)
            list.add(tempList[i]);

        return list;
    }
}