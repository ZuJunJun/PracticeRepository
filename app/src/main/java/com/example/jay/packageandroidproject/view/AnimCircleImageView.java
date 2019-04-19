package com.example.jay.packageandroidproject.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.jay.packageandroidproject.R;

@SuppressLint("AppCompatCustomView")
public class AnimCircleImageView extends ImageView {

    private float mCircleRadius;
    private Paint mBitmapPaint;
    private RectF mDrawableRect = new RectF();
    private TYPE type = TYPE.CIRCLE;
    private RectF mRoundRect;
    private ObjectAnimator mMusicalPhotoRotationAnim;

    public enum TYPE {
        CIRCLE
    }

    public AnimCircleImageView(Context context) {
        super(context);
    }

    public AnimCircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setAnimPause(){
        if (mMusicalPhotoRotationAnim != null) {
            mMusicalPhotoRotationAnim.pause();
        }
    }

    public void setAnimResume(){
        if (mMusicalPhotoRotationAnim != null) {
            mMusicalPhotoRotationAnim.resume();
        }
    }

    public void setAnimStart(){
        if (mMusicalPhotoRotationAnim != null) {
            mMusicalPhotoRotationAnim.start();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMusicalPhotoRotationAnim = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f);
        mMusicalPhotoRotationAnim.setDuration(5000);
        mMusicalPhotoRotationAnim.setRepeatCount(-1);
        mMusicalPhotoRotationAnim.setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) return;
        //图片paint
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        mDrawableRect.set(calculateBounds());
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        float scale = calculateBitmapScale(bitmap);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        //通过Matrix将缩放后的Bitmap移动到View的中心位置
        float dx = getMeasuredWidth() - bitmap.getWidth() * scale;
        float dy = getMeasuredHeight() - bitmap.getHeight() * scale;
        matrix.postTranslate(dx / 2, dy / 2);//注意只能用一个set方法，其他的要用post或pre方法
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shader.setLocalMatrix(matrix);
        mBitmapPaint.setShader(shader);
        if (type == TYPE.CIRCLE) {
            canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mCircleRadius, mBitmapPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (type == TYPE.CIRCLE) {
            return inTouchableArea(event.getX(), event.getY()) && super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    private boolean inTouchableArea(float x, float y) {
        return Math.pow(x - mDrawableRect.centerX(), 2)
                + Math.pow(y - mDrawableRect.centerY(), 2) <= Math.pow(mCircleRadius, 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE.CIRCLE) {
            int mSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mCircleRadius = mSize / 2;
            setMeasuredDimension(mSize, mSize);//实际的大小
        }
    }

    private RectF calculateBounds() {
        int availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();
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
        if (type == TYPE.CIRCLE) {
            int bSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = mCircleRadius * 2.0f / bSize;
            return scale;
        }
        return scale;
    }
}
