package com.zl.vo_.own.api;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.zl.vo_.own.callback.RequestCallBack;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.LoginActivity;
import com.zl.vo_.own.util.InternetUtil;
import com.zl.vo_.own.util.OkHttpManager;
import com.zl.vo_.own.util.SPUtils;
import com.zl.vo_.own.util.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
        excutePost(ApiConstant.REGISTER,"one", context, params,dialog, listener);
    }
    /*登录的方法*/
    public static void doLogin(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excutePost(ApiConstant.LOGIN, "one",context, params,dialog, listener);
    }
    /*获取个人信息*/
    public static void getUserInfo(final Context context, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excuteGet(ApiConstant.GET_USERINFO,"two" ,context,dialog, listener);
    }
    /*获取验证码*/
    public static void getConfirmCode(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        //excutePost("http://www.xfxhfgs.cn/index.php/user/VoUser/sendCode/2", context, params,dialog, listener);
    }
    protected static void excutePost(final String url,String typeHeader,final Context context, Map<String,String> params,final Dialog dialog, final OnRequestDataListener listener) {
        if (!InternetUtil.hasInternet()){
            Toast.makeText(context, "没有网，请检查网络", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.show();
        OkHttpManager.getInstance().postRequest(url, params, typeHeader,new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                String token=response.header("Authorization");
                if (!TextUtils.isEmpty(token)){
                    SPUtils.put(context,"cloudToken",token);
                }
                Log.i("tag",token+"===token====");
                if(null != context){
                    if(dialog != null && null != dialog.getContext() && null != dialog.getWindow())
                        dialog.dismiss();
                    if (!TextUtils.isEmpty(s)) {
                        listener.requestSuccess(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String code = jsonObject.getString("code");
                            if (code.equals(ApiConstant.OVERDUE_CODE)){
                                Intent intent = new Intent(context, LoginActivity.class);
                                context.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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


    protected static void excuteGet(String url, String typeHeader,final Context context,final Dialog dialog, final OnRequestDataListener listener) {
        if (!InternetUtil.hasInternet()){
            Toast.makeText(context, "没有网，请检查网络", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.show();
        OkHttpManager.getInstance().getRequest(url,typeHeader, new RequestCallBack<String>() {
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
    //退出登录
     /*注册的方法*/
    public static void logoutServer(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excutePost(ApiConstant.LOGOUT_SERVER,"two", context, params,dialog, listener);
    }

}
