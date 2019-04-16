package com.example.jay.packageandroidproject.fourth;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.base.Constant;
import com.example.jay.packageandroidproject.util.NotificationUtil;
import com.example.jay.packageandroidproject.util.VideoUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment {


    private NotificationManager notificationManager;
    private RecyclerView mVideoRecyclerView;
    private VideoAdapter videoAdapter;

    public static FourthFragment newInstance() {
        FourthFragment messageFragment = new FourthFragment();
        return messageFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        sendNotification(view);
        mVideoRecyclerView = view.findViewById(R.id.video_recycler_view);
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mVideoRecyclerView.setNestedScrollingEnabled(false);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mVideoRecyclerView);
        videoAdapter = new VideoAdapter(getContext(), VideoUtil.getDatas());
        mVideoRecyclerView.setAdapter(videoAdapter);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            videoAdapter.setVideoPause();
        }else {
            videoAdapter.setVideoStart();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        videoAdapter.releaseMediaPlayer();
    }

    private void sendNotification(View view) {
        notificationManager = ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE));
        view.findViewById(R.id.category_one).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                sendChatNotification();
            }
        });
        view.findViewById(R.id.category_three).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                sendDefaultNotification();
            }
        });
        view.findViewById(R.id.category_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLowerONotification();
            }
        });
    }

    private void sendLowerONotification() {
        NotificationUtil.sendNotification(getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendChatNotification() {
        NotificationUtil.sendNotification(getContext(),Constant.CHAT_ID);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendDefaultNotification() {
        NotificationUtil.sendNotification(getContext(),Constant.DEFAULT_ID);
    }
}
