package com.example.ced.activity;

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

import com.example.ced.R;
import com.example.ced.data.CodeResponse;
import com.example.ced.data.UpdateTimeRequest;
import com.example.ced.data.RankUserResponse;
import com.example.ced.network.RetrofitClient;
import com.example.ced.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserStopwatchActivity extends AppCompatActivity {

    private TextView UserNameView;
    private TextView UserTimeView;
    private TextView myOutput;
    private Button myBtnStart;
    private Button myBtnBack;
    private ServiceApi service;

    final static int Init = 0;
    final static int Run = 1;
    final static int Pause = 2;

    private int cur_Status = Init;
    private long myBaseTime;
    private long myPauseTime;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stopwatch);                   // xml과 java 소스 연결

        service = RetrofitClient.getClient().create(ServiceApi.class);      // 통신을 위한 ServiceApi 생성

        UserNameView = findViewById(R.id.userId);
        UserTimeView = findViewById(R.id.userTime);
        myOutput = findViewById(R.id.time_out);
        myBtnStart = findViewById(R.id.btn_start);
        myBtnBack = findViewById(R.id.btn_back);                            // xml의 컴포넌트와 연결

        userId = getIntent().getStringExtra("UserID");                  // 전 액티비티에서 UserID에 해당하는 문자열을 받아서 초기화

        getUserRanking(userId);                                             // 서버에서 현재 ID에 해당하는 유저의 정보를 들고 온다.
    }


    public void myOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:    // 시작 버튼을 눌렀을 때 현재 상태값에 따라 다른 동작을 할 수 있게끔 구현
                switch (cur_Status) {
                    case Init:
                        myBaseTime = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);        // myTimer 이라는 핸들러를 빈 메세지를 보내서 호출
                        myBtnStart.setText("멈춤");               // 버튼의 문자 "시작"을 "멈춤"으로 변경
                        cur_Status = Run;                           // 현재상태를 런 상태로 변경
                        break;
                    case Run:
                        myTimer.removeMessages(0);              // 핸들러 메세지 제거
                        myPauseTime = SystemClock.elapsedRealtime();
                        myBtnStart.setText("시작");
                        cur_Status = Pause;
                        break;
                    case Pause:
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += (now - myPauseTime);
                        myBtnStart.setText("멈춤");
                        cur_Status = Run;
                        break;
                }
                break;
            case R.id.btn_back:
                // 해당 시간을 받아와서 시분초로 분할
                String tempTime = myOutput.getText().toString();
                String[] tempSplit = tempTime.split(":");

                // 만약 0초일 라면 작업을 하지 않고 시간이 있다면 작업을 함
                if (Integer.parseInt(tempSplit[0] + tempSplit[1] + tempSplit[2]) != 0) {
                    updateUserTime(new UpdateTimeRequest(userId, changeDBTime(tempSplit)));            // 갱신할 시간을 db에 전송하고 메시지 출력
                    Toast.makeText(UserStopwatchActivity.this, "공부시간 저장을 시작합니다.", Toast.LENGTH_SHORT).show();
                } else {                                                                                // 갱신하지 않고 메시지 출력
                    Toast.makeText(UserStopwatchActivity.this, "공부시간이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }

                finish();       // 현재 액티비티를 종료함
                break;
        }
    }

    Handler myTimer = new Handler() {
        public void handleMessage(Message msg) {
            myOutput.setText(getTimeOut());
            myTimer.sendEmptyMessage(0);    // sendEmptyMessage는 비어있는 메세지를 handler 에게 전송
        }
    };

    // 현재 시간을 계속 구해서 출력하는 메소드
    String getTimeOut() {
        long now = SystemClock.elapsedRealtime(); // 애플리케이션이 실행되고 나서 실제로 경과된 시간
        long outTime = now - myBaseTime;
        String easy_outTime = String.format("%02d:%02d:%02d", outTime / 1000 / 3600, (outTime / 1000 / 60) % 60, (outTime / 1000) % 60);
        return easy_outTime;
    }

    /* 시간을 DB에서 사용하는 Int 형태로 바꿔주는 Method */
    public int changeDBTime(String[] temp) {
        int hour, min, sec;

        sec = Integer.parseInt(temp[2]);
        min = Integer.parseInt(temp[1]) * 60;
        hour = Integer.parseInt(temp[0]) * 3600;

        return hour + min + sec;        // 시간을 초로 만들어서 보내줌(1시간 = 3600초, 1분 = 60초)
    }


    /* DB에 시간 넣는 함수 */
    public void updateUserTime(UpdateTimeRequest data) {                    // 갱신을 위한 함수
        service.updateTime(data).enqueue(new Callback<CodeResponse>() {
            @Override
            public void onResponse(Call<CodeResponse> call, Response<CodeResponse> response) {
                CodeResponse code = response.body();                        // 갱신한 뒤 정보를 받아와서
                if (code.getCode() == 200) {                                // 성공이든 실패이든 메시지 출력
                    Toast.makeText(UserStopwatchActivity.this, "공부시간 저장에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserStopwatchActivity.this, "공부시간 저장에 오류가 생겼습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CodeResponse> call, Throwable t) {   // 통신에 실패하면 메시지를 출력한 뒤, System 로그 출력
                Toast.makeText(UserStopwatchActivity.this, "통신 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("통신 오류 발생", t.getMessage());
            }
        });
    }

    // User의 시간을 받아오는 Method
    public void getUserRanking(String userId) {                             // 유저의 정보를 받아오는 함수
        service.getUserRank(userId).enqueue(new Callback<RankUserResponse>() {
            @Override
            public void onResponse(Call<RankUserResponse> call, Response<RankUserResponse> response) {
                RankUserResponse tempRank = response.body();                // 받아온 정보를 객체에 넣어줌
                UserNameView.setText(tempRank.getUserName());               // 각 TextView의 정보를 갱신함
                UserTimeView.setText(changeUITime(tempRank.getTime()));     // 각 TextView의 정보를 갱신함
            }

            @Override
            public void onFailure(Call<RankUserResponse> call, Throwable t) {   // 통신에 실패하면 System 로그 출력
                Log.e("통신 오류 발생", t.getMessage());
            }
        });
    }

    public String changeUITime(int time) {                      // 유저의 정보를 받아오는 함수
        int tempTime = time;                                    // 시간을 받아옴

        int hour = tempTime / 3600;                             // 3600으로 나누면 '시'가 나옴
        tempTime = tempTime % 3600;                             // 시간의 정보를 뺌

        int min = tempTime / 60;                                // 60으로 나오면 '분'이 나옴
        tempTime = tempTime % 60;                               // 시간의 정보를 뺌

        int sec = tempTime;                                     // 마지막 시간은 자동으로 '초'가 됨

        return String.format("%d:%02d:%02d", hour, min, sec);   // 해당 시간을 String으로 바꿔줌(형태 지정)
    }
}