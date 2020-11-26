package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ced.R;

public class JobInterviewActivity extends AppCompatActivity {

    private WebView interviewWebView;
    private ImageButton backButton;
    private TextView jobNameTop;
    private String jobName;
    private String jobUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_interview);                    // xml과 java 소스 연결

        interviewWebView = findViewById(R.id.interviewWeb);
        jobNameTop = findViewById(R.id.InterviewTop);
        backButton = findViewById(R.id.jobinterviewBack);               // xml의 컴포넌트와 연결

        jobName = getIntent().getStringExtra("jobName");        // 전 액티비티에서 받은 내용으로 초기화
        jobUrl = getIntent().getStringExtra("jobUrl");          // 전 액티비티에서 받은 내용으로 초기화
        jobNameTop.setText(jobName);                                    // 해당 TextView의 내용을 변수의 문자열로 만듦

        interviewWebView.getSettings().setJavaScriptEnabled(true);      // WebView에서 JavaScript의 사용을 허용함
        interviewWebView.loadUrl(jobUrl);                               // 받은 Url문자열로 WebView를 이동함
        interviewWebView.setWebChromeClient(new WebChromeClient());     // Chrom으로 세팅함
        interviewWebView.setWebViewClient(new WebViewClientClass());    // Web에서 뒤로가기 위한 클래스

        backButton.setOnClickListener(new View.OnClickListener() {      // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }                   // 해당 액티비티 종료
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {             // 안드로이드의 key를 기반으로 움직이는 함수
        if ((keyCode == KeyEvent.KEYCODE_BACK) && interviewWebView.canGoBack()) {   // key가 뒤로가기이면서 뒤로 갈 때 history가 있다면
            interviewWebView.goBack();                                  // Web뷰의 페이지에서 뒤로가고
            return true;                                                // 뒤로 갓으므로 true를 반환함
        }
        return super.onKeyDown(keyCode, event);
    }


    private class WebViewClientClass extends WebViewClient {            // WebView를 위한 클래스

        @Override                                                       // 특수한 기능을 하게 만들 수도 있다.
        public boolean shouldOverrideUrlLoading(WebView view, String url) { // 현재 page의 url을 받아온다.
            view.loadUrl(jobUrl);                                       // view의 url을 지정한다.
            return true;                                                // true 반환
        }
    }
}