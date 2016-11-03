package com.android.imageloader.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/10/13 15:10
 * QQ         : 904869397@qq.com
 */

public class MyUtils {

    public static void close(Object object){
        if(object==null){
            return;
        }
        try {
            if(object instanceof OutputStream){
                ((OutputStream) object).close();
            }else if(object instanceof InputStream){
                ((InputStream) object).close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
