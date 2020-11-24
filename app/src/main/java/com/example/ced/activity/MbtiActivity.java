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
        setContentView(R.layout.activity_mbti);                     // xml과 java 소스 연결

        backButton = findViewById(R.id.mbtibackbtn);
        mbtiJobListView = findViewById(R.id.mbtiListView);
        mbtiTitle = findViewById(R.id.mbtiTitle);                   // xml의 컴포넌트와 연결

        mbti = getIntent().getStringExtra("mbti");              // mbti 정보를 받아와서 변수를 초기화 해준다.
        mbtiTitle.setText(mbti);                                      // 상단 바의 내용을 수정한다.

        adapter = new MbtiAdapter(MbtiActivity.this, getMbtiList(mbti));    // ListView를 위한 adapter 생성(List는 함수를 통해 생성함)
        mbtiJobListView.setAdapter(adapter);                        // 해당 ListView와 Adapter 연결(통신 기반이 아니므로 onCreate에서 구현함)

        backButton.setOnClickListener(new View.OnClickListener() {  // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }               // 해당 액티비티를 종료한다.
        });
    }

    public List<String> getMbtiList(String mbti) {                              // asset 폴더에서 정보를 읽기위한 함수
        List<String> list = new ArrayList<String>();                                 // 읽은 정보를 담을 List(ListView에 사용됨)
        String nonJson = null;                                                  // string 형태의 json을 담을 변수
        try {
            InputStream is = getResources().getAssets().open("mbtijoblist.json");  // 해당 파일을 asset폴더에서 읽음

            int byteSize = is.available();                                  // byte 단위로 읽으므로 byte의 총 개수 구해서
            byte[] buffer = new byte[byteSize];                             // string 전의 byte 코드를 넣을 buffer 생성

            is.read(buffer);                                                // byte 배열에 읽어온 정보 저장
            is.close();                                                     // Stream을 close함

            nonJson = new String(buffer,"UTF-8");               // 해당 byte 배열을 string으로 변환해줌
        } catch (IOException e) {
            e.printStackTrace();                                            // 예외처리
        }

        JsonParser Parser = new JsonParser();                               // 파싱을 위한 객체
        JsonObject jsonObject = (JsonObject) Parser.parse(nonJson);         // 해당 문자열을 json 객체로 만들어줌
        JsonArray array = (JsonArray) jsonObject.get("list");               // json 객체에서 list라는 항목을 json 배열에 담아줌
        String temp = null;                                                 // 해당 mbti 직업들의 문자열을 담을 변수

        for (int i = 0; i < array.size(); i++) {
            JsonObject object = (JsonObject) array.get(i);                  // 배열에서 해당 index의 객체를 얻어온 뒤
            if(object.get("mbtiname").getAsString().equals(mbti)) {         // 현재 액티비티와 같은 mbti라면
                temp = object.get("job").getAsString();                     // 내부에 있는 직업을 문자열로 받아오고
                break;                                                      // 반복을 멈춘다.
            }
        }

        String[] tempList = temp.split(", ");                         // 문자열을 "직업1, 직업2, 직업3"의 형태이므로 잘라서 배열로 저장

        for(int i = 0; i< tempList.length; i++)                             // 자른 문자열배열을 list에 추가해준다.
            list.add(tempList[i]);

        return list;                                                        // 해당 mbti 직업들이 있는 list를 반환
    }
}