package com.zl.vo_.own.api;

import android.app.Dialog;
import android.content.Context;

import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.util.WeiboDialogUtils;

import java.util.Map;

import static com.zl.vo_.own.api.ApiAccount.excuteGet;
import static com.zl.vo_.own.api.ApiAccount.excutePost;

/**
 * Created by Administrator on 2018/8/23.
 */
public class ApiMine {

    /*修改vo号*/
    public static void changeVoId(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excutePost(ApiConstant.CHANGE_VO_CODE,"two", context, params,dialog, listener);
    }
    /*获取用户二维码*/
    public static void getQrCode(final Context context,final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excuteGet(ApiConstant.GET_ERWEIMA,"two", context,dialog, listener);
    }
























































































    /**
     * xzy:接口
     */
    //设置签名
    public static void setSignature(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excutePost(ApiConstant.REGISTER,"one",context, params,dialog, listener);
    }
}
