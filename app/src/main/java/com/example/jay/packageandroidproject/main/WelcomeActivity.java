package com.example.jay.packageandroidproject.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.base.XActivity;

public class WelcomeActivity extends XActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_avtivity);
        hideActionBar();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, AdvertActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
