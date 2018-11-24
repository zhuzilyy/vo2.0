package com.zl.vo_.own.util;

/**
 * Created by tianou on 2018/11/24.
 */

import android.telephony.SmsManager;

import com.zl.vo_.R;

import java.util.ArrayList;

/**
 * 调用系统的发信息功能
 */
public class PhoneUtils {

    public static void sendSms(String content){

        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> list = smsManager.divideMessage(content);

        for (int i = 0; i < list.size(); i++) {
            smsManager.sendTextMessage("10086", null, list.get(i), null, null);
        }


    }









}
