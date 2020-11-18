package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ced.R;

public class RecordActivity extends AppCompatActivity {

    private ImageButton recordAddButton;
    private ImageButton recordBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recordAddButton = findViewById(R.id.recordaddbtn);
        recordBackButton = findViewById(R.id.recordbackbtn);

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
    }
}