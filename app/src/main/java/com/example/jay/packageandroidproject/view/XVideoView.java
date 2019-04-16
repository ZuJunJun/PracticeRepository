package com.example.jay.packageandroidproject.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.VideoView;

public class XVideoView extends VideoView {

    private long mTimeout = 400;//双击间四百毫秒延时
    private int mClickCount = 0;//记录连续点击次数
    private Handler mHandler = new Handler();

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

    public interface OnClickNumListener{
        void onSingleClickListener();
        void onDoubleClickListener();
    }

    public void setOnClickNumListener(OnClickNumListener onClickNumListener) {
        this.onClickNumListener = onClickNumListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mClickCount++;
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (onClickNumListener != null) {
                        if (mClickCount == 1) {
                            onClickNumListener.onSingleClickListener();
                        }else if(mClickCount == 2){
                            onClickNumListener.onDoubleClickListener();
                        }
                    }
                    mClickCount = 0;//计数清零
                }
            },mTimeout);//延时timeout后执行run方法中的代码
        }
        //让点击事件继续传播，方便再给View添加其他事件监听
        return false;
    }
}
