package com.zl.vo_.own.api;

import android.app.Dialog;
import android.content.Context;

import com.zl.vo_.contact.activity.AddFriendActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.util.WeiboDialogUtils;

import java.util.Map;

import static com.zl.vo_.own.api.ApiAccount.excutePost;

/**
 * Created by Administrator on 2018/8/23.
 */

public class ApiFriends {
    /*注册的方法*/
    public static void searchFriend(final AddFriendActivity context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "正在搜索");
        excutePost(ApiConstant.SEARCH_FRIEND,"two", context, params,dialog, listener);
    }
}
