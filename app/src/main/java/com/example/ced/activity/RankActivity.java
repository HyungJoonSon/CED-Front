package com.example.ced.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ced.R;

public class RankActivity extends Activity{

    Button myBtnStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        myBtnStudy=(Button)findViewById(R.id.study);

        myBtnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StopwatchActivity.class);
                startActivity(intent);
            }
        });

    }

}


