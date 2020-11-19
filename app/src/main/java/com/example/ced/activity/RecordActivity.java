package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ced.R;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    private ImageButton recordAddButton;
    private ImageButton recordBackButton;
    private ListView recordlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recordAddButton = findViewById(R.id.recordaddbtn);
        recordBackButton = findViewById(R.id.recordbackbtn);
        recordlist =  findViewById(R.id.recordlist);

        recordAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent); //다음화면으로 넘어감
            }
        });

        recordBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<String> data = new ArrayList<String>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        recordlist.setAdapter(adapter);

        data.add("창");
        data.add("의");
        data.add("적");
        data.add("공");
        data.add("학");
        data.add("설");
        data.add("계");
        data.add("창");
        data.add("의");
        data.add("적");
        data.add("공");
        data.add("학");
        data.add("설");
        data.add("계");
        data.add("창");
        data.add("의");
        data.add("적");
        data.add("공");
        data.add("학");
        data.add("설");
        data.add("계");
        data.add("창");
        data.add("의");
        data.add("적");
        data.add("공");
        data.add("학");
        data.add("설");
        data.add("계");

        adapter.notifyDataSetChanged();
    }
}