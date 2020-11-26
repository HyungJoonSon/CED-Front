package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ced.R;

import java.util.ArrayList;
import java.util.List;

public class JobSelectorActivity extends AppCompatActivity {

    private ImageButton jobbackbnt;
    private Button selectAllButton;
    private Button choicebtn;
    private ListView listview;
    private List<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_selector);                         // xml, java 연결

        listview = findViewById(R.id.listview_job);
        jobbackbnt = findViewById(R.id.jobbackbtn);
        choicebtn = findViewById(R.id.choicebtn);
        selectAllButton = findViewById(R.id.selectAll);                         // xml의 컴포넌트와 연결


        items = new ArrayList<String>();                                        // 빈 데이터 리스트 생성.

        items.add("건설·채굴 연구개발직 및 공학기술직");
        items.add("건설·채굴직");
        items.add("경영·행정·사무직");
        items.add("경찰·소방·교도직");
        items.add("경호·경비직");
        items.add("관리직(임원·부서장)");
        items.add("교육직");
        items.add("군인");
        items.add("금속·재료 설치·정비·생산직");
        items.add("금융·보험직");
        items.add("기계 설치·정비·생산직");
        items.add("농림어업직");
        items.add("돌봄 서비스직(간병·육아)");
        items.add("미용·예식 서비스직");
        items.add("법률직");
        items.add("보건·의료직");
        items.add("사회복지·종교직");
        items.add("섬유·의복 생산직");
        items.add("스포츠·레크리에이션직");
        items.add("식품 가공·생산직");
        items.add("여행·숙박·오락 서비스직");
        items.add("영업·판매직");
        items.add("예술·디자인·방송직");
        items.add("운전·운송직");
        items.add("음식 서비스직");
        items.add("인문·사회과학 연구직");
        items.add("인쇄·목재·공예 및 기타 설치·정비·생산직");
        items.add("자연·생명과학 연구직");
        items.add("전기·전자 설치·정비·생산직");
        items.add("정보통신 연구개발직 및 공학기술직");
        items.add("정보통신 설치·정비직");
        items.add("제조 단순직");
        items.add("제조 연구개발직 및 공학기술직");
        items.add("청소 및 기타 개인서비스직");
        items.add("화학·환경 설치·정비·생산직");                                   // 리스트에 항목 추가

        // ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦.
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);

        listview.setAdapter(adapter);                                           // listview와 Adapter 연결


        jobbackbnt.setOnClickListener(new View.OnClickListener() {              // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        choicebtn.setOnClickListener(new View.OnClickListener() {               // 완료 버튼
            @Override
            public void onClick(View v) {                                       // check한 list의 정보를 boolean List로 받음
                boolean trueCheckList = false;
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();

                for (int i = 0; i < items.size(); i++) {                        // 체크가 없는지 있는지 확인하는 반복
                    if (checkedItems.get(i)) {                                  // 체크가 한번이라도 되었다면
                        trueCheckList = true;                                   // boolean 값을 바꿔주고
                        break;                                                  // 반복을 멈춤
                    }
                }

                if (!trueCheckList) {                                            // 싱글톤 패턴으로 선택된지 확인함(없다면 아무것도 안함)
                    Toast.makeText(JobSelectorActivity.this, "선택된 항목이 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer jobfieldbuffer = new StringBuffer();               // 통신 형식을 위한 StringBuffer

                for (int i = 0; i < items.size(); i++) {                        // 통신 형식을 위한 문자열 변경 작업
                    if (checkedItems.get(i)) {                                  // 체크한 항목만 buffer에 넣어줌
                        jobfieldbuffer.append(items.get(i) + ", ");
                    }
                }

                jobfieldbuffer.deleteCharAt(jobfieldbuffer.lastIndexOf(", "));  // 마지막에 ", "를 삭제해줌
                // 위 과정을 거치면 정보는 "ㅁㄴㅇㄹ, ㅁㄴㅇㄹ, ㅁㄴㅇㄹ" 형식이 됨

                Intent intent = new Intent(getApplicationContext(), JobInfoListActivity.class);
                intent.putExtra("jobSelect", jobfieldbuffer.toString());  // 해당 자료를 넘겨줌
                startActivity(intent);                                          // 다음 화면으로 넘어감
            }
        });

        selectAllButton.setOnClickListener(new View.OnClickListener() {         // select all 버튼
            @Override
            public void onClick(View v) {                                       // 모든 것을 check함
                int count = 0;
                count = adapter.getCount();                                     // list의 크기만큼 반복한다.
                for (int i = 0; i < count; i++) {
                    listview.setItemChecked(i, true);                     // 해당 inde에 있는 list의 아이템 check
                }
            }
        });
    }
}



