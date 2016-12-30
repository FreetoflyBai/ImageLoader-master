package com.android.imageloader.util;

import android.content.Context;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/11/24 12:03
 * QQ         : 904869397@qq.com
 */

public class Px2DpUtils {
    /**
     * dip to px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px to dip
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
