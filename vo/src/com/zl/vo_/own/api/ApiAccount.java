package com.zl.vo_.own.api;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;

import com.zl.vo_.own.callback.RequestCallBack;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.util.OkHttpManager;
import com.zl.vo_.own.util.WeiboDialogUtils;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/23.
 */

public class ApiAccount {
    /*注册的方法*/
    public static void doRegister(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excutePost(ApiConstant.REGISTER, context, params,dialog, listener);
    }
    /*获取验证码*/
    public static void getConfirmCode(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excutePost("http://www.xfxhfgs.cn/index.php/user/VoUser/sendCode/2", context, params,dialog, listener);
    }
    protected static void excutePost(String url, final Context context, Map<String,String> params,final Dialog dialog, final OnRequestDataListener listener) {
        OkHttpManager.getInstance().postRequest(url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                if(null != context){
                    if(dialog != null && null != dialog.getContext() && null != dialog.getWindow())
                        dialog.dismiss();
                    if (!TextUtils.isEmpty(s)) {
                        listener.requestSuccess(s);
                    }
                }
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                if(null != context){
                    if(dialog != null && null != dialog.getContext() && null != dialog.getWindow())
                        dialog.dismiss();
                    if (listener != null) {
                        listener.requestFailure(statusCode, e.getMessage());
                    }
                }
            }
        });
    }
}
