package com.example.jay.packageandroidproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.jay.packageandroidproject.R;

public class CircleAddView extends View {
    private int mCircleRadius;

    public CircleAddView(Context context) {
        super(context);
    }

    public CircleAddView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleAddView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawCircle(mCircleRadius,mCircleRadius,mCircleRadius,paint);
        Paint bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.add);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        canvas.drawBitmap(bitmap,mCircleRadius-width/2,mCircleRadius-height/2,bitmapPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mCircleRadius = mSize/2;
        setMeasuredDimension(mSize, mSize);//实际的大小
    }
}
