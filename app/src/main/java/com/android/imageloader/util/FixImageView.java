package com.android.imageloader.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/10/13 15:44
 * QQ         : 904869397@qq.com
 */

public class FixImageView extends ImageView{
    public FixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
