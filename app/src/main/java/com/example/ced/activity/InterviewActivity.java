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

public class InterviewActivity extends AppCompatActivity {

    private WebView interviewWebView;
    private ImageButton backButton;
    private TextView jobNameTop;
    private String jobName;
    private String jobUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        interviewWebView = findViewById(R.id.interviewWeb);
        jobNameTop = findViewById(R.id.InterviewTop);
        backButton = findViewById(R.id.jobinterviewBack);

        jobName = getIntent().getStringExtra("jobName");
        jobUrl = getIntent().getStringExtra("jobUrl");

        jobNameTop.setText(jobName);
        interviewWebView.getSettings().setJavaScriptEnabled(true);
        interviewWebView.loadUrl(jobUrl);
        interviewWebView.setWebChromeClient(new WebChromeClient());
        interviewWebView.setWebViewClient(new WebViewClientClass());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && interviewWebView.canGoBack()) {
            interviewWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private class WebViewClientClass extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(jobUrl);
            return true;
        }
    }
}