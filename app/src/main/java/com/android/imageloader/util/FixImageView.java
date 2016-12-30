package com.android.imageloader.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.imageloader.draw.CircleDrawable;
import com.android.imageloader.draw.RoundDrawable;

import static android.R.attr.bitmap;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/10/13 15:44
 * QQ         : 904869397@qq.com
 */

public class FixImageView extends ImageView{

    Context mContext;
    public FixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        Bitmap bitmap=drawableToBitmap(new RoundDrawable(bm,Px2DpUtils.dip2px(mContext,30)));
        super.setImageBitmap(bitmap);
    }

    private Bitmap drawableToBitmap(Drawable drawable)
    {
        if (drawable instanceof BitmapDrawable)
        {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}
