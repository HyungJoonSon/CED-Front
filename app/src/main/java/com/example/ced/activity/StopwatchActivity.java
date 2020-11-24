package com.example.ced.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ced.R;
import com.example.ced.data.CodeResponse;
import com.example.ced.data.JoinRequest;
import com.example.ced.data.RankRequest;
import com.example.ced.fragdata.FragChallenge;
import com.example.ced.network.RetrofitClient;
import com.example.ced.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class StopwatchActivity extends AppCompatActivity {

    TextView UserName;
    TextView UserTime;
    TextView myOutput;
    Button myBtnStart;
    Button myBtnBack;

    final static int Init=0;
    final static int Run=1;
    final static int Pause=2;

    int cur_Status=Init;
    long myBaseTime;
    long myPauseTime;
    String Name;
    String ID;
    String Time;
    String totalTime;
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        UserName=(TextView)findViewById(R.id.userId);
        UserTime=(TextView)findViewById(R.id.userTime);
        myOutput=(TextView)findViewById(R.id.time_out);
        myBtnStart=(Button)findViewById(R.id.btn_start);
        myBtnBack=(Button)findViewById(R.id.btn_back);

        Intent intent1 = getIntent();
        Name =intent1.getStringExtra("UserName");
        ID = intent1.getStringExtra("UserId");
        Time = intent1.getStringExtra("Time");
        UserName.setText(Name+"'s 누적시간");   // stopwatch 페이지
        UserTime.setText(Time); // 누적시간 출력
    }

    @Override
    protected void onDestroy(){
        //TODO Auto-generated method stub
        super.onDestroy();
    }

    public void myOnClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:    // 시작 버튼을 눌렀을 때 현재 상태값에 따라 다른 동작을 할 수 있게끔 구현
                switch(cur_Status){
                    case Init:
                        myBaseTime= SystemClock.elapsedRealtime();
                        System.out.println(myBaseTime);
                        // myTimer 이라는 핸들러를 빈 메세지를 보내서 호출
                        myTimer.sendEmptyMessage(0);
                        myBtnStart.setText("멈춤");   //버튼의 문자 "시작"을 "멈춤"으로 변경
                        cur_Status=Run; // 현재상태를 런 상태로 변경
                        break;
                    case Run:
                        myTimer.removeMessages(0);  // 핸들러 메세지 제거
                        myPauseTime=SystemClock.elapsedRealtime();
                        myBtnStart.setText("시작");
                        cur_Status=Pause;
                        break;
                    case Pause:
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime+=(now-myPauseTime);
                        myBtnStart.setText("멈춤");
                        cur_Status=Run;
                        break;
                }
                break;
            case R.id.btn_back:
                String tempTime = myOutput.getText().toString();
                Toast.makeText(StopwatchActivity.this, "공부시간이 기록되었습니다.", Toast.LENGTH_SHORT).show();

                int t;  // 전체시간
                if(Time==null||Time.length()==0){
                    t=changeTime("00:00:00", tempTime);
                }else{
                    t=changeTime(Time, tempTime);
                }
                int hour = t/10000; //시간
                int min = t/100 - hour*100; // (시간+분) - 시간
                int sec = t - (t/100)*100; // 전체시간 - (시간+분)
                totalTime = String.format("%d:%02d:%02d", hour, min, sec);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("UserName", Name);
                intent.putExtra("UserID", ID);
                intent.putExtra("Time", totalTime);
                //intent.putExtra("Check", true);
                startActivity(intent);
                break;
        }
    }

    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){
            myOutput.setText(getTimeOut());
            // sendEmptyMessage는 비어있는 메세지를 handler 에게 전송
            myTimer.sendEmptyMessage(0);
        }
    };

    // 현재 시간을 계속 구해서 출력하는 메소드
    String getTimeOut(){
        long now=SystemClock.elapsedRealtime(); // 애플리케이션이 실행되고 나서 실제로 경과된 시간
        long outTime = now-myBaseTime;
        String easy_outTime=String.format("%02d:%02d:%02d", outTime/1000/3600, (outTime/1000/60)%60, (outTime/1000)%60);
        return easy_outTime;
    }

    /* 시간 누적하는 함수 */
    public int changeTime(String Time1, String Time2){ // Time1: 저장되어있는 시간, Time2: 새로 측정된 시간
        int hour, min, sec, result;

        String[] curTime = Time1.split(":");    // split함수 사용하여 문자열 나눔
        int curHour = Integer.parseInt(curTime[0]);
        int curMin = Integer.parseInt(curTime[1]);
        int curSec = Integer.parseInt(curTime[2]);

        String[] recTime = Time2.split(":");    // split함수 사용하여 문자열 나눔
        int recHour = Integer.parseInt(recTime[0]);
        int recMin = Integer.parseInt(recTime[1]);
        int recSec = Integer.parseInt(recTime[2]);

        int temp = curMin+recMin+((curSec+recSec)/60);
        sec = (curSec+recSec)%60;
        min = temp%60;
        hour = curHour+recHour+(temp/60);

        String tempHour=Integer.toString(hour); 
        String tempMin; // 두 
        if(min<10){ // 분이 일의자리 수 이면
            tempMin="0"+Integer.toString(min);  // 앞에 0 자리 붙여줌
        }else if(min==0) {  // 0분이면
            tempMin="00";   // 앞에 00 자리 붙여줌
        }else{  // 분이 두 자리 수이면
            tempMin=Integer.toString(min);  // 그대로 string으로 변환하여 tempMin에 저장
        }
        String tempSec;
        if(sec<10){ // 초가 일의자리 수 이면
            tempSec="0"+Integer.toString(sec);  // 초 앞에 0 자리 붙여줌
        }else if(sec==0) {
            tempSec="00";
        }else{
            tempSec=Integer.toString(sec);
        }
        result = Integer.parseInt(tempHour+tempMin+tempSec);    // 문자열들을 붙여 누적된 숫자로 변환
        return result;
    }
}