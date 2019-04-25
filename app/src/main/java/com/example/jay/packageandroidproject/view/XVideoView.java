package com.example.jay.packageandroidproject.view;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

public class XVideoView extends VideoView implements View.OnTouchListener {
    private GestureDetector detector;

    public XVideoView(Context context) {
        super(context);
        init(context);

    }

    public XVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public OnClickNumListener onClickNumListener;
    public OnDetachListener onDetachListener;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        return false;
    }

    public interface OnClickNumListener{
        void onSingleClickListener();
        void onDoubleClickListener();
    }

    public interface OnDetachListener{
        void onDetachListener();
    }

    public void setOnClickNumListener(OnClickNumListener onClickNumListener) {
        this.onClickNumListener = onClickNumListener;
    }

    public void setOnDetachListener(OnDetachListener onDetachListener) {
        this.onDetachListener = onDetachListener;
    }

    private void init(Context mCtx) {
        this.setClickable(true);
        detector = new GestureDetector(mCtx, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //双击事件
                if (onClickNumListener != null) {
                    onClickNumListener.onDoubleClickListener();
                }
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (onClickNumListener != null) {
                    onClickNumListener.onSingleClickListener();
                }
                return super.onSingleTapConfirmed(e);
            }
        });
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return false;
            }
        });
    }


    @Override
    protected void onDetachedFromWindow() {
        onDetachListener.onDetachListener();
        super.onDetachedFromWindow();
    }
}
