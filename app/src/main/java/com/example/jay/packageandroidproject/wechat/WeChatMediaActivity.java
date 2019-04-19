package com.example.jay.packageandroidproject.wechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.util.CameraUtil;

public class WeChatMediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_media);
        findViewById(R.id.open_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtil.openPicture4All(WeChatMediaActivity.this);
            }
        });
        findViewById(R.id.take_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtil.takeSinglePhoto(WeChatMediaActivity.this);
            }
        });
        findViewById(R.id.take_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtil.takeVideo(WeChatMediaActivity.this);
            }
        });
        findViewById(R.id.open_audio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtil.open4Audio(WeChatMediaActivity.this);
            }
        });
    }
}
