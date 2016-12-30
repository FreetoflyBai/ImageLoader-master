package com.android.imageloader.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/22 18:19
 * QQ         : 904869397@qq.com
 */

public class RoundDrawable extends Drawable {

    Bitmap mBitmap;
    Paint mPaint;
    RectF mRectF;
    int mRound;

    public RoundDrawable(Bitmap bitmap, int round){
        mBitmap = bitmap;
        mRound=round;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom){
        super.setBounds(left, top, right, bottom);
        mRectF = new RectF(left, top, right, bottom);
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(mRectF, mRound, mRound, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
