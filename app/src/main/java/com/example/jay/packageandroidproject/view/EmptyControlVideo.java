package com.example.jay.packageandroidproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.jay.packageandroidproject.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

/**
 * 无任何控制ui的播放
 * Created by guoshuyu on 2017/8/6.
 */

public class EmptyControlVideo extends GSYVideoPlayer {

    public EmptyControlVideo(Context context) {
        super(context);
    }

    public EmptyControlVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.only_pause_video;
    }

    @Override
    public void startPlayLogic() {
        prepareVideo();
    }

    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        //不给触摸快进，如果需要，屏蔽下方代码即可
        mChangePosition = false;
        //不给触摸音量，如果需要，屏蔽下方代码即可
        mChangeVolume = false;
        //不给触摸亮度，如果需要，屏蔽下方代码即可
        mBrightness = false;
    }

    @Override
    protected void showWifiDialog() {

    }

    @Override
    protected void showProgressDialog(float deltaX, String seekTime, int seekTimePosition, String totalTime, int totalTimeDuration) {

    }

    @Override
    protected void dismissProgressDialog() {

    }

    @Override
    protected void showVolumeDialog(float deltaY, int volumePercent) {

    }

    @Override
    protected void dismissVolumeDialog() {

    }

    @Override
    protected void showBrightnessDialog(float percent) {

    }

    @Override
    protected void dismissBrightnessDialog() {

    }

    @Override
    protected void onClickUiToggle() {

    }

    @Override
    protected void hideAllWidget() {

    }

    @Override
    protected void changeUiToNormal() {
        setViewShowState(mStartButton,VISIBLE);
        setViewShowState(mLoadingProgressBar,VISIBLE);
    }

    @Override
    protected void changeUiToPreparingShow() {

    }

    @Override
    protected void changeUiToPlayingShow() {
        setViewShowState(mStartButton,GONE);
        setViewShowState(mLoadingProgressBar,GONE);
    }

    @Override
    protected void changeUiToPauseShow() {
        setViewShowState(mStartButton,VISIBLE);
        setViewShowState(mLoadingProgressBar,GONE);
    }

    @Override
    protected void changeUiToError() {
        setViewShowState(mStartButton,VISIBLE);
        setViewShowState(mLoadingProgressBar,GONE);
    }

    @Override
    protected void changeUiToCompleteShow() {

    }

    @Override
    protected void changeUiToPlayingBufferingShow() {

    }

    public OnDoubleClickListener onDoubleClickListener;

    public interface OnDoubleClickListener{
        void onDoubleClickListener();
    }

    public void setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener) {
        this.onDoubleClickListener = onDoubleClickListener;
    }
    /**
     * 双击
     */
    protected GestureDetector gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (onDoubleClickListener != null) {
                onDoubleClickListener.onDoubleClickListener();
            }
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            touchDoubleUp();
            return super.onSingleTapConfirmed(e);
        }
    });

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return false;
    }
}
