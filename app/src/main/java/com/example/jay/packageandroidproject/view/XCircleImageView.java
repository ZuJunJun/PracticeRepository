package com.example.jay.packageandroidproject.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.util.UICommonUtil;

@SuppressLint("AppCompatCustomView")
public class XCircleImageView extends ImageView {

    private float mCircleRadius;
    private float mRoundRadius;
    private float mBorderWidth;
    private int mBorderColor;
    private Paint mBitmapPaint;
    private Paint mBorderPaint;
    private RectF mDrawableRect = new RectF();
    private TYPE type = TYPE.CIRCLE;
    private RectF mRoundRect;

    public enum TYPE{
        CIRCLE,ROUND,BORDER_CIRCLE,BORDER_ROUND
    }

    public void setType(TYPE type) {
        this.type = type;
        invalidate();
    }

    public TYPE getType() {
        return type;
    }

    public void setmBorderColor(int mBorderColor) {
        this.mBorderColor = mBorderColor;
        invalidate();
    }

    public void setRoundRadius(float mRoundRadius) {
        this.mRoundRadius = mRoundRadius;
        invalidate();
    }

    public XCircleImageView(Context context) {
        super(context);
        init(context, null);
    }

    public XCircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public XCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context mCtx, @Nullable AttributeSet attrs) {
        TypedArray a = mCtx.obtainStyledAttributes(attrs, R.styleable.XCircleImageView);
        mRoundRadius = a.getDimension(R.styleable.XCircleImageView_xround_radius, 0);
        mBorderWidth = a.getDimension(R.styleable.XCircleImageView_border_width, 0);
        mBorderColor = a.getColor(R.styleable.XCircleImageView_border_color, Color.RED);
        a.recycle();
        //图片paint
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        //边框paint
        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setColor(mBorderColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable()==null) return;
        mDrawableRect.set(calculateBounds());
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        float scale = calculateBitmapScale(bitmap);
        Matrix matrix = new Matrix();
        matrix.setScale(scale,scale);
        //通过Matrix将缩放后的Bitmap移动到View的中心位置
        float dx = getMeasuredWidth() - bitmap.getWidth() * scale;
        float dy = getMeasuredHeight() - bitmap.getHeight() * scale;
        matrix.postTranslate(dx / 2, dy / 2);//注意只能用一个set方法，其他的要用post或pre方法
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shader.setLocalMatrix(matrix);
        mBitmapPaint.setShader(shader);
        if (type == TYPE.CIRCLE){
            canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mCircleRadius, mBitmapPaint);
        }else if (type == TYPE.ROUND) {
            mRoundRect = new RectF(0, 0, getWidth(), getHeight());
            canvas.drawRoundRect(mRoundRect,mRoundRadius,mRoundRadius, mBitmapPaint);
        }else if (type == TYPE.BORDER_CIRCLE) {
            canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mCircleRadius - mBorderWidth, mBitmapPaint);
            if (mBorderWidth>=0)
                canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mCircleRadius-mBorderWidth/2, mBorderPaint);
        }else if (type == TYPE.BORDER_ROUND) {
            RectF mRect = new RectF(0 + mBorderWidth, 0 + mBorderWidth, getWidth() - mBorderWidth, getHeight() - mBorderWidth);
            mRoundRect = new RectF(0 + mBorderWidth / 2, 0 + mBorderWidth / 2, getWidth() - mBorderWidth / 2, getHeight() - mBorderWidth / 2);
            canvas.drawRoundRect(mRect,mRoundRadius,mRoundRadius, mBitmapPaint);
            canvas.drawRoundRect(mRoundRect,mRoundRadius,mRoundRadius, mBorderPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (type == TYPE.CIRCLE || type == TYPE.BORDER_CIRCLE) {
            return inTouchableArea(event.getX(),event.getY()) && super.onTouchEvent(event);
        }else if (type == TYPE.CIRCLE || type == TYPE.BORDER_CIRCLE) {
            return inRectArea(event.getX(),event.getY()) && super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    private boolean inRectArea(float x, float y) {
        return mRoundRect.contains(x, y);
    }

    private boolean inTouchableArea(float x, float y) {
        return Math.pow(x - mDrawableRect.centerX(), 2)
                + Math.pow(y - mDrawableRect.centerY(), 2) <= Math.pow(mCircleRadius, 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE.CIRCLE || type == TYPE.BORDER_CIRCLE) {
            int mSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mCircleRadius = mSize/2;
            setMeasuredDimension(mSize, mSize);//实际的大小
        }
    }

    private RectF calculateBounds() {
        int availableWidth  = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        int sideLength = Math.min(availableWidth, availableHeight);
        float left = getPaddingLeft() + (availableWidth - sideLength) / 2f;
        float top = getPaddingTop() + (availableHeight - sideLength) / 2f;
        return new RectF(left, top, left + sideLength, top + sideLength);
    }

    /**
     * 计算Bitmap需要缩放的比例
     */
    private float calculateBitmapScale(Bitmap bitmap) {
        float scale = 1.0f;
        if (type == TYPE.CIRCLE || type == TYPE.BORDER_CIRCLE) {
            int bSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = mCircleRadius * 2.0f / bSize;
            return scale;
        }else if (type == TYPE.ROUND || type == TYPE.BORDER_ROUND){
            // 如果Bitmap的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的Bitmap的宽高，一定要【大于】view的宽高
            if (bitmap.getWidth() != getWidth() || bitmap.getHeight() != getHeight()) {
                float scaleWidth = getWidth() * 1.0f / bitmap.getWidth();
                float scaleHeight = getHeight() * 1.0f / bitmap.getHeight();
                scale = Math.max(scaleWidth, scaleHeight);
                return scale;
            }
        }
        return scale;
    }
}
