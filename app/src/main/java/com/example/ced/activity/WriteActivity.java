package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ced.R;
import com.example.ced.data.Record;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

public class WriteActivity extends AppCompatActivity {

    private Button writeAddButton;
    private ImageButton writeBackButton;
    private EditText writetitle;
    private EditText writecontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        JodaTimeAndroid.init(this);

        writeAddButton = findViewById(R.id.writeaddbtn);
        writeBackButton = findViewById(R.id.writebackbtn);
        writetitle = findViewById(R.id.writetitleview);
        writecontent = findViewById(R.id.writecontentview);

        writeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = writetitle.getText().toString();
                String content = writecontent.getText().toString();
                DateTime dateTime = new DateTime();
                String date = dateTime.toString("yyyy년 MM월 dd일 HH:mm:ss").substring(0, 13);
                ((RecordListActivity) RecordListActivity.context_record).saveRecord(new Record(title, date, content));
                finish();
            }
        });

        writeBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}