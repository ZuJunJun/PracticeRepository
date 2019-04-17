package com.example.jay.packageandroidproject.ding;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jay.packageandroidproject.R;

public class DingBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接收到了DING消息", Toast.LENGTH_SHORT).show();
        final Dialog mDingDialog = new Dialog(context,R.style.Dialog);

        //看dialog源码重新设置Gravity即可改变位置
        Window window = mDingDialog.getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        window.setGravity(Gravity.TOP);

        mDingDialog.setContentView(R.layout.dialog_ding_layout);
        mDingDialog.setCancelable(false);
        mDingDialog.setCanceledOnTouchOutside(false);
        mDingDialog.findViewById(R.id.later).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDingDialog.dismiss();
            }
        });
        mDingDialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDingDialog.dismiss();
            }
        });
        mDingDialog.show();
    }
}
