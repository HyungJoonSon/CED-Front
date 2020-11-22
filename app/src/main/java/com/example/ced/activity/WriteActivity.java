package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ced.R;
import com.example.ced.data.Record;

import org.joda.time.DateTime;

public class WriteActivity extends AppCompatActivity {

    private Button writeAddButton;
    private ImageButton writeBackButton;
    private EditText writetitle;
    private EditText writecontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);                            // xml, java 연결

        writeAddButton = findViewById(R.id.writeaddbtn);
        writeBackButton = findViewById(R.id.writebackbtn);
        writetitle = findViewById(R.id.writetitleview);
        writecontent = findViewById(R.id.writecontentview);                 // xml의 컴포넌트와 각각 연결

        writeAddButton.setOnClickListener(new View.OnClickListener() {      // 저장 버튼을 누를 때
            @Override
            public void onClick(View v) {
                String title = writetitle.getText().toString();
                String content = writecontent.getText().toString();         // 해당 내용들을 String 객체에 넣고
                DateTime dateTime = new DateTime();
                String date = dateTime.toString("yyyy년 MM월 dd일 HH:mm:ss").substring(0, 13);     // 현재 시간을 생성(년, 월, 일까지)

                // 다른 액티비티를 참조하기 위해 만들어둔 context로 현재 작성된 record 저장
                ((RecordListActivity) RecordListActivity.context_record).saveRecord(new Record(title, date, content));

                finish();                                                   // 해당 액티비티 종료
            }
        });

        writeBackButton.setOnClickListener(new View.OnClickListener() {     // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }                       // 해당 액티비티를 종료함
        });
    }
}