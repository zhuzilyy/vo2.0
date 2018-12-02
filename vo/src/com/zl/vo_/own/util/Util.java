package com.zl.vo_.own.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zl.vo_.own.ui.account.bean.WXAccessTokenInfo;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/12/2.
 */

public class Util {
    //保存微信的accessToken
    public static boolean saveAccessInfotoLocation(WXAccessTokenInfo tokenInfo, Context context){
        SharedPreferences preferences = context.getSharedPreferences("tokenInfo",MODE_PRIVATE);
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(tokenInfo);
            // 将字节流编码成base64的字符窜
            String oAuth_Base64 = new String(Base64.encodeBase64(baos
                    .toByteArray()));
            Log.i("xxx","save的字符串=="+oAuth_Base64);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Info", oAuth_Base64);

            editor.commit();
            Log.i("xxx","tokenInfo存储成功");
            return true;
        } catch (IOException e) {

            Log.i("xxx","tokenInfo存储失败"+e);
            return false;
        }

    }
}
