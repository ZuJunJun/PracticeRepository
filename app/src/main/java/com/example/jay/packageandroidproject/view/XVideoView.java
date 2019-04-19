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

public class XVideoView extends VideoView{
    private GestureDetector detector;
    private Context mCtx;

    public XVideoView(Context context) {
        super(context);
    }

    public XVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public OnClickNumListener onClickNumListener;
    public OnDeatachListener onDeatachListener;

    public interface OnClickNumListener{
        void onSingleClickListener();
        void onDoubleClickListener();
    }

    public interface OnDeatachListener{
        void onDetachListener();
    }

    public void setOnClickNumListener(Context mCtx,OnClickNumListener onClickNumListener) {
        this.mCtx = mCtx;
        this.onClickNumListener = onClickNumListener;
        init(mCtx);
    }

    public void setOnDeatachListener(OnDeatachListener onDeatachListener) {
        this.onDeatachListener = onDeatachListener;
    }

    private void init(Context mCtx) {
        this.setClickable(true);
        detector = new GestureDetector(mCtx, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,      //滑动事件
                                   float velocityX, float velocityY) {
                if(Math.abs(velocityX) > Math.abs(velocityY)){   //如果X偏移量大于Y偏移量
                    if(velocityX > 0){
//                        Toast.makeText(getContext(), "Right Fling", Toast.LENGTH_SHORT).show();
                    }else{
//                        Toast.makeText(getContext(), "Left Fling", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(velocityY > 0){
//                        Toast.makeText(getContext(), "Down Fling", Toast.LENGTH_SHORT).show();
                    }else{
//                        Toast.makeText(getContext(), "Up Fling", Toast.LENGTH_SHORT).show();
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

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
                return detector.onTouchEvent(event);
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        onDeatachListener.onDetachListener();
        super.onDetachedFromWindow();
    }
}
