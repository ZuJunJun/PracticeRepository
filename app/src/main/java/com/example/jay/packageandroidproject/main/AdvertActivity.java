package com.example.jay.packageandroidproject.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.base.XActivity;

import java.util.Timer;
import java.util.TimerTask;

public class AdvertActivity extends XActivity implements View.OnClickListener {

    private WebView mAdvertWebview;
    private LinearLayout mDismissAdvertLayout;
    private TextView mAdvertTimeTv;
    private int time = 5;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    time--;
                    mAdvertTimeTv.setText(String.valueOf(time));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);
        hideActionBar();
        init();
    }

    private void init() {
        mAdvertWebview = findViewById(R.id.advert_webview);
        mDismissAdvertLayout = findViewById(R.id.dismiss_layout);
        mAdvertTimeTv = findViewById(R.id.advert_time);
        mAdvertWebview.loadUrl("https://www.baidu.com/");
        mDismissAdvertLayout.setOnClickListener(this);
        excuteTimer();
    }

    private void excuteTimer() {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                if (time != 0) {
                    handler.sendEmptyMessage(1);
                } else {
                    timer.cancel();
                    Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dismiss_layout:
                Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
