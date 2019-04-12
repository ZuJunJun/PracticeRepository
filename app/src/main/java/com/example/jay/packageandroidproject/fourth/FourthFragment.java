package com.example.jay.packageandroidproject.fourth;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.base.Constant;


/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment {


    private NotificationManager notificationManager;

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
        notificationManager = ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE));
        view.findViewById(R.id.category_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDefaultNotification();
            }
        });
        view.findViewById(R.id.category_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendChatNotification();
            }
        });
    }

    private void sendDefaultNotification() {
        Notification notification = new NotificationCompat.Builder(getContext(), Constant.CHAT_ID)
                .setContentTitle("你收到一条聊天消息")
                .setContentText("今天中午吃啥呢？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1,notification);
    }

    private void sendChatNotification() {
        Notification notification = new NotificationCompat.Builder(getContext(), Constant.DEFAULT_ID)
                .setContentTitle("你收到一条普通消息")
                .setContentText("卧槽？！听说你买房啦？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        notificationManager.notify(2,notification);
    }
}
