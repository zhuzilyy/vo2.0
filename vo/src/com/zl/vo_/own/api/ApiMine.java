package com.zl.vo_.own.api;

import android.app.Dialog;
import android.content.Context;

import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.util.WeiboDialogUtils;

import java.util.Map;

import static com.zl.vo_.own.api.ApiAccount.excutePost;

/**
 * Created by Administrator on 2018/8/23.
 */

public class ApiMine {




























































































    /**
     * xzy:接口
     */
    //设置签名
    public static void setSignature(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中");
        excutePost(ApiConstant.REGISTER, context, params,dialog, listener);
    }
}
