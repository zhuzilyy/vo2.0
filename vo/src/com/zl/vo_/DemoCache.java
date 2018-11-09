package com.zl.vo_;

import android.content.Context;

import com.netease.nim.avchatkit.AVChatKit;
import com.netease.nim.rtskit.RTSKit;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;

/**
 * Created by jezhee on 2/20/15.
 */
public class DemoCache {
    /*测试提交*/
    private static Context context;

    private static String account;

    public static String getPhone_contacts() {
        return phone_contacts;
    }

    public static void setPhone_contacts(String phone_contacts) {
        DemoCache.phone_contacts = phone_contacts;
    }

    /**
     *  获取手机通讯录字符串
     */
    private static String phone_contacts;

    private static StatusBarNotificationConfig notificationConfig;

    public static void clear() {
        account = null;
    }

    public static String getAccount() {
        return account;
    }

    private static boolean mainTaskLaunching;

    public static void setAccount(String account) {
        DemoCache.account = account;
        NimUIKit.setAccount(account);
        AVChatKit.setAccount(account);
        RTSKit.setAccount(account);
    }

    public static void setNotificationConfig(StatusBarNotificationConfig notificationConfig) {
        DemoCache.notificationConfig = notificationConfig;
    }

    public static StatusBarNotificationConfig getNotificationConfig() {
        return notificationConfig;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DemoCache.context = context.getApplicationContext();

        AVChatKit.setContext(context);
        RTSKit.setContext(context);
    }

    public static void setMainTaskLaunching(boolean mainTaskLaunching) {
        DemoCache.mainTaskLaunching = mainTaskLaunching;

        AVChatKit.setMainTaskLaunching(mainTaskLaunching);
    }

    public static boolean isMainTaskLaunching() {
        return mainTaskLaunching;
    }
}
