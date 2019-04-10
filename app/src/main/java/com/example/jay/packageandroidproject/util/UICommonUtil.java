package com.example.jay.packageandroidproject.util;

import android.content.Context;
import android.util.TypedValue;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/*
 * 未采用TypedValue.applyDimension方式,是因为这个方式不能将px转化回来
 * 代码统一性考虑,其实代码实现原理一样
 */
public class UICommonUtil {
	/*
	 * 把sp转化成px
	 */
	public static int sp2px(Context context, int value) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (value * fontScale + 0.5f); //四舍五入
	}
	/*
	 * 把dp转化成px
	 */
	public static int dp2px(Context context, int value) {
		final float scale = context.getResources().getDisplayMetrics().density; 
        return (int) (value * scale + 0.5f); 
	}

	/*
	 * dp转为px
	 */
	public static int dpTopx(Context context, int value) {
		return (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP,value,context.getResources().getDisplayMetrics());
	}

	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}
}
