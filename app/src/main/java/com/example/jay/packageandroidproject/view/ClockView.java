package com.example.jay.packageandroidproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.util.UICommonUtil;

import java.util.Calendar;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class ClockView extends View {

    private Context mCtx;
    //圆盘Paint
    private Paint mRoundelPanit;
    //刻度paint
    private Paint mDegressPaint;
    //指针Panit
    private Paint mIndicatorPaint;
    //圆心paint
    private Paint mPointPaint;
    //圆盘颜色
    private int mRoundPaintColor = Color.WHITE;
    //圆心颜色
    private int mPointPaintColor = Color.RED;
    //圆心半径
    private int mPointRadius = 7;
    //圆盘半径
    private int mRoundelRadius = 0;
    //刻度宽度
    private int mDegreeWidth = 0;
    //长刻度颜色
    private int mLongDegreeColor = Color.BLACK;
    //短刻度颜色
    private int mShortDegreeColor = Color.RED;
    //表盘内边距
    private int mRoundelPadding = 5;
    //字体大小
    private float mTextSize = TypedValue.applyDimension(COMPLEX_UNIT_SP,12.0f,getResources().getDisplayMetrics());
    // 时针宽度
    private float mHourIndicatorWidth = 4.0f;
    // 分针宽度
    private float mMinuteIndicatorWidth = 3.0f;
    // 秒针宽度
    private float mSecondIndicatorWidth = 2.0f;
    // 指针圆角
    private float mIndicatorRadius = 5.0f;
    // 指针末尾长度
    private float mIndicatorEndLength = 30.0f;
    // 时针的颜色
    private int mHourIndicatorColor = Color.BLACK;
    // 分针的颜色
    private int mMinuteIndicatorColor = Color.BLACK;
    // 秒针的颜色
    private int mSecondIndicatorColor = Color.RED;

    public ClockView(Context context) {
        super(context);
        this.mCtx = context;
        init(null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mCtx = context;
        init(attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCtx = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = mCtx.obtainStyledAttributes(attrs, R.styleable.ClockView);
        mRoundPaintColor = typedArray.getColor(R.styleable.ClockView_cv_roundColor,Color.WHITE);
        mPointPaintColor = typedArray.getColor(R.styleable.ClockView_cv_pointColor,Color.RED);
        mPointRadius = (int) typedArray.getDimension(R.styleable.ClockView_cv_pointRadius,7);
        mLongDegreeColor = typedArray.getColor(R.styleable.ClockView_cv_longDegreeColor,Color.BLACK);
        mShortDegreeColor = typedArray.getColor(R.styleable.ClockView_cv_shortDegreeColor,Color.WHITE);
        mRoundelPadding = (int) typedArray.getDimension(R.styleable.ClockView_cv_roundPadding,3);
        mTextSize = (int) typedArray.getDimension(R.styleable.ClockView_cv_degreeTextSize,mTextSize);
        mHourIndicatorWidth = typedArray.getDimension(R.styleable.ClockView_cv_hourIndicatorWidth,4.0f);
        mMinuteIndicatorWidth = typedArray.getDimension(R.styleable.ClockView_cv_minuteIndicatorWidth,3.0f);
        mSecondIndicatorWidth = typedArray.getDimension(R.styleable.ClockView_cv_secondIndicatorWidth,3.0f);
        mIndicatorRadius = typedArray.getDimension(R.styleable.ClockView_cv_indicatorRadius,5.0f);
        mIndicatorEndLength = typedArray.getDimension(R.styleable.ClockView_cv_indicatorEndLength,30.0f);
        mHourIndicatorColor = typedArray.getColor(R.styleable.ClockView_cv_hourIndicatorColor,Color.BLACK);
        mMinuteIndicatorColor = typedArray.getColor(R.styleable.ClockView_cv_minuteIndicatorColor,Color.BLACK);
        mSecondIndicatorColor = typedArray.getColor(R.styleable.ClockView_cv_secondIndicatorColor,Color.RED);
        typedArray.recycle();
        //初始化圆盘paint
        mRoundelPanit = new Paint();
        mRoundelPanit.setAntiAlias(true);
        mRoundelPanit.setStyle(Paint.Style.FILL);
        mRoundelPanit.setColor(mRoundPaintColor);
        //初始化刻度paint
        mDegressPaint = new Paint();
        mDegressPaint.setAntiAlias(true);
        //初始化指针paint
        mIndicatorPaint = new Paint();
        mIndicatorPaint.setAntiAlias(true);
        //初始化圆心paint
        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setColor(mPointPaintColor);
        mPointPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画圆盘
        canvas.drawCircle(mRoundelRadius,mRoundelRadius,mRoundelRadius,mRoundelPanit);
        //画刻度
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                mDegressPaint.setStrokeWidth(TypedValue.applyDimension(COMPLEX_UNIT_DIP,1.5f,getResources().getDisplayMetrics()));
                mDegressPaint.setColor(mLongDegreeColor);
                mDegreeWidth = 20;
                // 这里是字体的绘制
                mDegressPaint.setTextSize(mTextSize);
                String text = ((i / 5) == 0 ? 12 : (i / 5)) + "";
                Rect textBound = new Rect();
                mDegressPaint.getTextBounds(text, 0, text.length(), textBound);
                mDegressPaint.setColor(Color.BLACK);
                canvas.drawText(text, mRoundelRadius - textBound.width() / 2, textBound.height() + UICommonUtil.dp2px(getContext(),5) + mDegreeWidth + mRoundelPadding, mDegressPaint);
            } else {
                mDegreeWidth = 15;
                mDegressPaint.setColor(mShortDegreeColor);
                mDegressPaint.setStrokeWidth(TypedValue.applyDimension(COMPLEX_UNIT_DIP,1.0f,getResources().getDisplayMetrics()));
            }
            canvas.drawLine(mRoundelRadius,  mRoundelPadding, mRoundelRadius,  mRoundelPadding + mDegreeWidth, mDegressPaint);
            canvas.rotate(6,mRoundelRadius,mRoundelRadius);
        }
        //画指针
        Calendar calendar = Calendar.getInstance();
        //时分秒
        int hour = calendar.get(Calendar.HOUR);// 时
        int minute = calendar.get(Calendar.MINUTE);// 分
        int second = calendar.get(Calendar.SECOND);// 秒
        // 转过的角度
        float angleHour = (hour + (float) minute / 60) * 360 / 12;
        float angleMinute = (minute + (float) second / 60) * 360 / 60;
        int angleSecond = second * 360 / 60;
        // 绘制时针
        canvas.save();
        canvas.rotate(angleHour,mRoundelRadius,mRoundelRadius); // 旋转到时针的角度
        RectF rectHour = new RectF(mRoundelRadius - mHourIndicatorWidth / 2, mRoundelRadius - (mRoundelRadius-mRoundelPadding) * 3 / 5, mRoundelRadius + mHourIndicatorWidth / 2, mRoundelRadius + mIndicatorEndLength);
        mIndicatorPaint.setColor(mHourIndicatorColor);
        mIndicatorPaint.setStyle(Paint.Style.STROKE);
        mIndicatorPaint.setStrokeWidth(mHourIndicatorWidth);
        canvas.drawRoundRect(rectHour, mIndicatorRadius, mIndicatorRadius, mIndicatorPaint);
        canvas.restore();
        // 绘制分针
        canvas.save();
        canvas.rotate(angleMinute,mRoundelRadius,mRoundelRadius); // 旋转到分针的角度
        RectF rectMinute = new RectF(mRoundelRadius-mMinuteIndicatorWidth / 2, mRoundelRadius- (mRoundelRadius-mRoundelPadding) * 3.5f / 5, mRoundelRadius + mMinuteIndicatorWidth / 2, mRoundelRadius + mIndicatorEndLength);
        mIndicatorPaint.setColor(mMinuteIndicatorColor);
        mIndicatorPaint.setStrokeWidth(mMinuteIndicatorWidth);
        canvas.drawRoundRect(rectMinute, mIndicatorRadius, mIndicatorRadius, mIndicatorPaint);
        canvas.restore();
        // 绘制分针
        canvas.save();
        canvas.rotate(angleSecond,mRoundelRadius,mRoundelRadius); // 旋转到分针的角度
        RectF rectSecond = new RectF(mRoundelRadius - mSecondIndicatorWidth / 2, mRoundelRadius - (mRoundelRadius-mRoundelPadding) + UICommonUtil.dp2px(getContext(),10), mRoundelRadius + mSecondIndicatorWidth / 2, mRoundelRadius+mIndicatorEndLength);
        mIndicatorPaint.setStrokeWidth(mSecondIndicatorWidth);
        mIndicatorPaint.setColor(mSecondIndicatorColor);
        canvas.drawRoundRect(rectSecond, mIndicatorRadius, mIndicatorRadius, mIndicatorPaint);
        canvas.restore();
        //画圆心
        canvas.drawCircle(mRoundelRadius,mRoundelRadius,mPointRadius,mPointPaint);
        // 每一秒刷新一次
        postInvalidateDelayed(1000);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mMinSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRoundelRadius = mMinSize/2;
        setMeasuredDimension(mMinSize,mMinSize);
    }
}
