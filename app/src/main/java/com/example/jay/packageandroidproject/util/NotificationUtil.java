package com.example.jay.packageandroidproject.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.base.Constant;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtil {
    /*创建通知栏分类*/
    public static void createNotificationCategory(Context mCtx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);

            String chatName = "聊天消息";
            int high = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(Constant.CHAT_ID, chatName, high);
            notificationManager.createNotificationChannel(notificationChannel);

            String defaultName = "普通消息";
            int mDefault = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationDefaultChannel = new NotificationChannel(Constant.DEFAULT_ID, defaultName, mDefault);
            notificationManager.createNotificationChannel(notificationDefaultChannel);
        }
    }

    /*发送消息Android O*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sendNotification(Context mCtx, String notificationId) {
        NotificationManager notificationManager = ((NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE));
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(notificationId);
        if (notificationChannel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
            Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, mCtx.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, notificationChannel.getId());
            mCtx.startActivity(intent);
        }
        if (notificationChannel.getId().equals(Constant.CHAT_ID)) {
            Notification notification = new NotificationCompat.Builder(mCtx, notificationId)
                    .setContentTitle("你收到一条重要消息")
                    .setContentText("卧槽？！听说你买房啦？")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(mCtx.getResources(), R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .build();
            notificationManager.notify(2, notification);
        } else {
            Notification notification = new NotificationCompat.Builder(mCtx, notificationId)
                    .setContentTitle("你收到一条普通消息")
                    .setContentText("中午吃啥呢？")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(mCtx.getResources(), R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .build();
            notificationManager.notify(3, notification);
        }
    }

    /*发送通知Android O以下*/
    public static void sendNotification(Context mCtx) {
        NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService
                (NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(mCtx)
                .setContentTitle("我是标题")
                .setContentText("我是内容")
                .setLargeIcon(BitmapFactory.decodeResource(mCtx.getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND).build();
        notificationManager.notify(1, notification);
    }
}
