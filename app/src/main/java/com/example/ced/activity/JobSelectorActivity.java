package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ced.R;

import java.util.ArrayList;
import java.util.List;

public class JobSelectorActivity extends AppCompatActivity {
    private Bundle savedInstanceState;
    private List<String> items;
    private ArrayAdapter adapter;
    private ListView listview;
    private ImageButton jobbackbnt;
    private Button selectAllButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_job_selector);



        // listview 생성 및 adapter 지정.
        listview = (ListView) findViewById(R.id.listview_job);

        // 빈 데이터 리스트 생성.
        items = new ArrayList<String>();

        // ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦.
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);

        listview.setAdapter(adapter);

        items.add("관리직(임원 부서장)");
        items.add("경영 행정 사무직");
        items.add("금융 보험직");
        items.add("인문 사회과학 연구직");
        items.add("자연 생명과학 연구진");
        items.add("정보통신 연구개발직 및 공학기술직");
        items.add("건설 채굴 연구개발직 및 공학기술직");
        items.add("제조 연구개발직 및 공학 기술직");
        items.add("교육직");
        items.add("법률직");
        items.add("사화복지 종교직");
        items.add("경찰 소방 교도직");
        items.add("군인");
        items.add("보건 의료직");
        items.add("예술 디자인 방송직");
        items.add("스포츠 레크리에이션직");
        items.add("미용 예식 서비스직");
        items.add("여행 숙박 오락 서비스직");
        items.add("음식 서비스직");
        items.add("경호 경비직");
        items.add("돌봄 서비스직(간호,육아)");
        items.add("청소 및 기타 개인 서비스직");
        items.add("영업 판매직");
        items.add("운전 운송직");
        items.add("건설 채굴직");
        items.add("기계 설치 정비 생산직");
        items.add("금속 재료 설치 정비 생산직");
        items.add("전기전자");
        items.add("정보톤신 설치 정비직");
        items.add("화학 환경 설치 정비 생산직");
        items.add("섬유 의복 생산직");
        items.add("식품 가공 생산직");
        items.add("인쇄 목제 공예 및 기타 설치 정비 생산직");
        items.add("제조 단순직");
        items.add("농림 어업직");

        adapter.notifyDataSetChanged();

        // 뒤로가기 버튼 만들기 finish();
        jobbackbnt=(ImageButton)findViewById(R.id.jobbackbtn);
        jobbackbnt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // 완료 버튼 만들기

        // selectAll button에 대한 이벤트 처리.
        selectAllButton = (Button) findViewById(R.id.selectAll);
        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count =0;
                count=adapter.getCount();
                for(int i=0; i<count;i++){
                    listview.setItemChecked(i,true);
                }

            }
        });
    }
}



