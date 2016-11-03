package com.android.imageloader.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;

import java.lang.reflect.Method;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/10/13 15:10
 * QQ         : 904869397@qq.com
 */
public class NetworkUtils {

    /**
     * 返回网络活动连接信息
     * returns information on the active network connection
     *
     * @param context
     * @return
     */
    private static NetworkInfo getActivityNetWorkInfo(Context context) {
        if (context == null) {
            return null;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return null;
        }
        //注意在没有网络的条件下这里也有可能是返回null。
        //note that this may return null if no network is currently active
        return cm.getActiveNetworkInfo();
    }

    /**
     * 如果有网络活动连接，通过ConnectivityManager.TYPE返回对应类型，其它返回未知
     * returns the ConnectivityManager.TYPE_xxx if there's an active connection, otherwise
     * returns TYPE_UNKNOWN
     *
     * @param context
     * @return
     */
    public static int getActivityNetworkType(Context context) {
        NetworkInfo info = getActivityNetWorkInfo(context);
        assert info != null;
        return info.getType();
    }

    /**
     * 如果网络连接正常返回true
     * returns true if a network connection is available
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo info = getActivityNetWorkInfo(context);
        return (info != null && info.isConnected());
    }

    /**
     * 如果连接类型是Wifi返回true
     * returns true if the user is connected to WiFi
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        return (getActivityNetworkType(context) == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * 飞行模式被启动返回true
     * returns true if airplane mode has been enabled
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressWarnings("deprecation")
    public static boolean isAirPlaneModeOn(Context context) {
        // prior to JellyBean 4.2 this was Settings.System.AIRPLANE_MODE_ON, JellyBean 4.2
        // moved it to Settings.Global
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    /**
     * 检查网络连接是否为true，否则返回false
     * returns true if there's an active network connection, otherwise displays a toast error
     * and returns false
     *
     * @param context
     * @return
     */
    public static boolean checkConnection(Context context) {
        return context != null && isNetworkAvailable(context);
    }


    /**
     * 切换WiFi(requires android.permission.ACCESS_NETWORK_STATE)
     *
     * @param context
     * @param status
     */
    public static void toggleWiFi(Context context, boolean status) {

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        // 如果需要打开wifi并且wifi并没连接，则打开
        if (status && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (!status && wifiManager.isWifiEnabled()) {// 如果需要关闭wifi并且wifi已连接
            wifiManager.setWifiEnabled(false);
        }

    }


    /**
     * 切换移动数据(requires android.permission.ACCESS_NETWORK_STATE)
     *
     * @param context
     * @param state
     */
    public static void toggleMobileData(Context context, boolean state) {
        ConnectivityManager connectivityManager = null;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Method method = connectivityManager.getClass().getMethod("setMobileDataEnabled", boolean.class);
            method.invoke(connectivityManager, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
