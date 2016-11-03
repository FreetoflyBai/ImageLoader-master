package com.android.imageloader.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/10/13 11:40
 * QQ         : 904869397@qq.com
 */

public class ImageResizer {
    private static final String TAG="ImageResizer";

    public ImageResizer(){

    }

    public static Bitmap decodeSampleBitmapFromResource(Resources res,int resId,
                                                        int reqWidth,int reqHeight){
        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,resId,options);
        //Calculate inSampleSize
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);
        //Decode bitmap with inSampleSize set
        options.inJustDecodeBounds=false;
        return  BitmapFactory.decodeResource(res,resId,options);
    }

    public Bitmap decodeSampleBitmapFromFileDescriptor
            (FileDescriptor fd,int reqWidth,int reqHeight){
        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFileDescriptor(fd,null,options);
        //Calculate inSampleSize
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);

        //Decode bitmap with inSampleSize set
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeFileDescriptor(fd,null,options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options,int reqWidth,int reqHeight){
        //Raw height and width of image
        final int height = options.outHeight;
        final int width=options.outWidth;
        int inSampleSize=1;
        if(height>reqHeight||width>reqWidth){
            final int halfHeight=height/2;
            final int halfWidth=width/2;
            //Calculate the largest than the requested height and width
            while ((halfHeight/inSampleSize)>=reqHeight
                    &&(halfWidth/inSampleSize)>=reqWidth){
                inSampleSize*=2;
            }
        }
        return inSampleSize;
    }




}
