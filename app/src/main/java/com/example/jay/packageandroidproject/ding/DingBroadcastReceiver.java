package com.example.jay.packageandroidproject.ding;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.util.UICommonUtil;

public class DingBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接收到了DING消息", Toast.LENGTH_SHORT).show();
//        final Dialog mDingDialog = new Dialog(context,R.style.Dialog);

        //看dialog源码重新设置Gravity即可改变位置
//        Window window = mDingDialog.getWindow();
//        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        window.setGravity(Gravity.TOP);
//
//        mDingDialog.setContentView(R.layout.dialog_ding_layout);
//        mDingDialog.setCancelable(false);
//        mDingDialog.setCanceledOnTouchOutside(false);
//        mDingDialog.findViewById(R.id.later).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDingDialog.dismiss();
//            }
//        });
//        mDingDialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDingDialog.dismiss();
//            }
//        });
//        mDingDialog.show();
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_ding_layout, null);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.gravity = Gravity.TOP;
        params.width = widthPixels-UICommonUtil.dp2px(context,40);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        view.findViewById(R.id.later).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeView(view);
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeView(view);
            }
        });
        windowManager.addView(view,params);
    }
}
