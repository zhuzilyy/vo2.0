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
    /*搜索好友*/
    public static void searchFriend(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "正在搜索");
        excutePost(ApiConstant.SEARCH_FRIEND,"two", context, params,dialog, listener);
    }
    /*搜索好友*/
    public static void addFriendDirectly(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "正在加载");
        excutePost(ApiConstant.ADD_FRIEND_DIRECTLY,"two", context, params,dialog, listener);
    }
    /*添加好友*/
    public static void insertFriend(final Context context, Map<String,String> params, final OnRequestDataListener listener) {
        Dialog dialog = WeiboDialogUtils.createLoadingDialog(context, "正在添加");
        excutePost(ApiConstant.INSERT_FRIEND,"two", context, params,dialog, listener);
    }
}
