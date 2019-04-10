package com.example.jay.packageandroidproject.second;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.util.UICommonUtil;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private ImageView rotateIv;
    private AnimatorSet animatorSet;
    private DisplayMetrics displayMetrics;

    public static SecondFragment newInstance() {
        SecondFragment messageFragment = new SecondFragment();
        return messageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second_frament, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayMetrics = getResources().getDisplayMetrics();
        rotateIv = view.findViewById(R.id.second_iv);
        startAnimation();
    }

    private double mInch = 0;
    /**
     * 获取屏幕尺寸
     * @return
     */
    public double getScreenInch() {
        if (mInch != 0.0d) {
            return mInch;
        }

        try {
            int realWidth = 0, realHeight = 0;
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            if (android.os.Build.VERSION.SDK_INT >= 17) {
                Point size = new Point();
                display.getRealSize(size);
                realWidth = size.x;
                realHeight = size.y;
            } else if (android.os.Build.VERSION.SDK_INT < 17
                    && android.os.Build.VERSION.SDK_INT >= 14) {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } else {
                realWidth = metrics.widthPixels;
                realHeight = metrics.heightPixels;
            }

            mInch =formatDouble(Math.sqrt((realWidth/metrics.xdpi) * (realWidth /metrics.xdpi) + (realHeight/metrics.ydpi) * (realHeight / metrics.ydpi)),1);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mInch;
    }
    /**
     * Double类型保留指定位数的小数，返回double类型（四舍五入）
     * newScale 为指定的位数
     */
    private static double formatDouble(double d,int newScale) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    private void startAnimation() {
        animatorSet = new AnimatorSet();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(rotateIv, "TranslationX", 0, displayMetrics.widthPixels-rotateIv.getLayoutParams().width);
        translationX.setDuration(2000);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(rotateIv, "TranslationY", 0, displayMetrics.heightPixels-rotateIv.getLayoutParams().height-UICommonUtil.dp2px(getActivity(),80));
        translationY.setDuration(2000);
        ObjectAnimator translation4X = ObjectAnimator.ofFloat(rotateIv, "TranslationX", displayMetrics.widthPixels-rotateIv.getLayoutParams().width, 0);
        translation4X.setDuration(2000);
        ObjectAnimator translation4Y = ObjectAnimator.ofFloat(rotateIv, "TranslationY",  displayMetrics.heightPixels-rotateIv.getLayoutParams().height-UICommonUtil.dp2px(getActivity(),80),0);
        translation4Y.setDuration(2000);

        ObjectAnimator translation2CenterX = ObjectAnimator.ofFloat(rotateIv, "TranslationX", 0, displayMetrics.widthPixels/2-rotateIv.getLayoutParams().width/2);
        translationX.setDuration(2000);
        ObjectAnimator translation2CenterY = ObjectAnimator.ofFloat(rotateIv, "TranslationY", 0, displayMetrics.heightPixels/2-rotateIv.getLayoutParams().height/2-UICommonUtil.dp2px(getActivity(),80)/2);
        translationY.setDuration(2000);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(rotateIv, "ScaleX", 0f, 1.0f,0.5f,1.0f);
        scaleX.setDuration(2000);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(rotateIv, "ScaleY", 0f, 1.0f,0.5f,1.0f);
        scaleY.setDuration(2000);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(rotateIv, "Alpha", 0f, 1.0f);
        alpha.setDuration(2000);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(rotateIv, "RotationY", 0f, 360f);
        rotationY.setDuration(2000);
        rotationY.setRepeatCount(-1);
        animatorSet.playSequentially(translationX,translationY,translation4X,translation4Y);
//        animatorSet.play(alpha).with(scaleY).with(scaleX).before(rotationY);
        animatorSet.start();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && rotateIv != null) {
            startAnimation();
        } else if (animatorSet != null) resetIv();
    }

    private void resetIv() {
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(rotateIv, "RotationY", 0f, 360f);
        rotationY.setDuration(100);
        rotationY.start();
        animatorSet.cancel();
    }
}
