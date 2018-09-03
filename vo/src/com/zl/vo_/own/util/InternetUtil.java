package com.zl.vo_.own.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zl.vo_.NimApplication;

/**
 * Created by Administrator on 2018/9/3.
 */

public class InternetUtil {
    /**
     * 检查是否有网络
     * @return
     */
    public static boolean hasInternet() {
        ConnectivityManager cm = (ConnectivityManager) NimApplication.getAppliaction()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }

    /**
     * 判断网络是不是wifi
     */
    public static boolean isWifi(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info.getType()==ConnectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }
}
