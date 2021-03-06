package com.zl.vo_.team;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.contact.ContactProvider;
import com.netease.nim.uikit.api.wrapper.NimUserInfoProvider;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.impl.provider.DefaultContactProvider;
import com.netease.nimlib.sdk.team.constant.TeamBeInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.VerifyTypeEnum;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.netease.nimlib.sdk.uinfo.model.UserInfo;
import com.zl.vo_.DemoCache;
import com.zl.vo_.main.activity.MainActivity;
import com.zl.vo_.own.util.SPUtils;
import com.zl.vo_.session.SessionHelper;
import com.netease.nim.uikit.business.team.helper.TeamHelper;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.constant.TeamFieldEnum;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.CreateTeamResult;
import com.netease.nimlib.sdk.team.model.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hzxuwen on 2015/9/25.
 */
public class TeamCreateHelper {
    private static final String TAG = TeamCreateHelper.class.getSimpleName();
    private static final int DEFAULT_TEAM_CAPACITY = 200;

    /**
     * 创建讨论组
     */
    public static void createNormalTeam(final Context context, List<String> memberAccounts, final boolean isNeedBack, final RequestCallback<CreateTeamResult> callback) {

        String teamName = "讨论组";

        DialogMaker.showProgressDialog(context, context.getString(com.netease.nim.uikit.R.string.empty), true);
        // 创建群
        HashMap<TeamFieldEnum, Serializable> fields = new HashMap<TeamFieldEnum, Serializable>();
        fields.put(TeamFieldEnum.Name, teamName);
        NIMClient.getService(TeamService.class).createTeam(fields, TeamTypeEnum.Normal, "",
                memberAccounts).setCallback(
                new RequestCallback<CreateTeamResult>() {
                    @Override
                    public void onSuccess(CreateTeamResult result) {
                        DialogMaker.dismissProgressDialog();

                        ArrayList<String> failedAccounts = result.getFailedInviteAccounts();
                        if (failedAccounts != null && !failedAccounts.isEmpty()) {
                            TeamHelper.onMemberTeamNumOverrun(failedAccounts, context);
                        } else {
                            Toast.makeText(DemoCache.getContext(), com.netease.nim.uikit.R.string.create_team_success, Toast.LENGTH_SHORT).show();
                        }

                        if (isNeedBack) {
                            SessionHelper.startTeamSession(context, result.getTeam().getId(), MainActivity.class, null); // 进入创建的群
                        } else {
                            SessionHelper.startTeamSession(context, result.getTeam().getId());
                        }
                        if (callback != null) {
                            callback.onSuccess(result);
                        }
                    }

                    @Override
                    public void onFailed(int code) {
                        DialogMaker.dismissProgressDialog();
                        if (code == 801) {
                            String tip = context.getString(com.netease.nim.uikit.R.string.over_team_member_capacity, DEFAULT_TEAM_CAPACITY);
                            Toast.makeText(DemoCache.getContext(), tip,
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DemoCache.getContext(), com.netease.nim.uikit.R.string.create_team_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        Log.e(TAG, "create team error: " + code);
                    }

                    @Override
                    public void onException(Throwable exception) {
                        DialogMaker.dismissProgressDialog();
                    }
                }
        );
    }

    /**
     * 创建高级群
     */
    public static void createAdvancedTeam(final Context context, List<String> memberAccounts) {

        Log.i("qwertyuiop",memberAccounts.size()+"++++++++++");
        String groupNames = getGroupNames(memberAccounts,context);
        String teamName = "";
        if(!TextUtils.isEmpty(groupNames)){
             teamName = groupNames;
        }


        DialogMaker.showProgressDialog(context, context.getString(com.netease.nim.uikit.R.string.empty), true);
        // 创建群
        TeamTypeEnum type = TeamTypeEnum.Advanced;
        HashMap<TeamFieldEnum, Serializable> fields = new HashMap<>();
        fields.put(TeamFieldEnum.Name, teamName);
        fields.put(TeamFieldEnum.InviteMode, TeamInviteModeEnum.All); //群组邀请模式，所有人都可以邀请
        fields.put(TeamFieldEnum.BeInviteMode, TeamBeInviteModeEnum.NoAuth);//被邀请人身份验证，不需要验证
        fields.put(TeamFieldEnum.VerifyType, VerifyTypeEnum.Free);//申请加入群组的验证模式，不需要验证

        NIMClient.getService(TeamService.class).createTeam(fields, type, "", memberAccounts).setCallback(
                new RequestCallback<CreateTeamResult>() {
                    @Override
                    public void onSuccess(CreateTeamResult result) {
                        Log.i(TAG, "create team success, team id =" + result.getTeam().getId() + ", now begin to update property...");
                        onCreateSuccess(context, result);
                    }

                    @Override
                    public void onFailed(int code) {
                        DialogMaker.dismissProgressDialog();
                        String tip;
                        if (code == 801) {
                            tip = context.getString(com.netease.nim.uikit.R.string.over_team_member_capacity,
                                    DEFAULT_TEAM_CAPACITY);
                        } else if (code == 806) {
                            tip = context.getString(com.netease.nim.uikit.R.string.over_team_capacity);
                        } else {
                            tip = context.getString(com.netease.nim.uikit.R.string.create_team_failed) + ", code=" +
                                    code;
                        }

                        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();

                        Log.e(TAG, "create team error: " + code);
                    }

                    @Override
                    public void onException(Throwable exception) {
                        DialogMaker.dismissProgressDialog();
                    }
                }
        );
    }

    /**
     * xzy:拼接用户名称当作群的名称
     * @param memberAccounts
     * @return
     */
    private static String getGroupNames(List<String> memberAccounts,Context context) {

        StringBuffer sb = new StringBuffer();
        ContactProvider userInfoProvider = new DefaultContactProvider();
        String myNick = (String) SPUtils.get(context,"nickName","");

        Iterator<String> iterator = memberAccounts.iterator();

        while(iterator.hasNext()){
            String account = iterator.next();

            String userDisplayName = getUserDisplayName(account);
//            String nick = userInfoProvider.getAlias(account);
//            NimUserInfo user = NIMClient.getService(UserService.class).getUserInfo(account);

            sb.append(userDisplayName+",");
        }

        return sb.toString()+myNick;


    }


    public static String getUserDisplayName(String account) {
        String alias = NimUIKit.getContactProvider().getAlias(account);
        if (!TextUtils.isEmpty(alias)) {
            return alias;
        } else {
            UserInfo userInfo = NimUIKit.getUserInfoProvider().getUserInfo(account);
            if (userInfo != null && !TextUtils.isEmpty(userInfo.getName())) {
                return userInfo.getName();
            } else {
                return account;
            }
        }
    }


    /**
     * 群创建成功回调
     */
    private static void onCreateSuccess(final Context context, CreateTeamResult result) {
        if (result == null) {
            Log.e(TAG, "onCreateSuccess exception: team is null");
            return;
        }
        final Team team = result.getTeam();
        if (team == null) {
            Log.e(TAG, "onCreateSuccess exception: team is null");
            return;
        }

        Log.i(TAG, "create and update team success");

        DialogMaker.dismissProgressDialog();
        // 检查有没有邀请失败的成员
        ArrayList<String> failedAccounts = result.getFailedInviteAccounts();
        if (failedAccounts != null && !failedAccounts.isEmpty()) {
            TeamHelper.onMemberTeamNumOverrun(failedAccounts, context);
        } else {
            Toast.makeText(DemoCache.getContext(), com.netease.nim.uikit.R.string.create_team_success, Toast.LENGTH_SHORT).show();
        }

        // 演示：向群里插入一条Tip消息，使得该群能立即出现在最近联系人列表（会话列表）中，满足部分开发者需求
        Map<String, Object> content = new HashMap<>(1);
        content.put("content", "成功创建高级群");
        IMMessage msg = MessageBuilder.createTipMessage(team.getId(), SessionTypeEnum.Team);
        msg.setRemoteExtension(content);
        CustomMessageConfig config = new CustomMessageConfig();
        config.enableUnreadCount = false;
        msg.setConfig(config);
        msg.setStatus(MsgStatusEnum.success);
        NIMClient.getService(MsgService.class).saveMessageToLocal(msg, true);

        // 发送后，稍作延时后跳转
        new Handler(context.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                UI.finishActivity(UI.activities);
                SessionHelper.startTeamSession(context, team.getId()); // 进入创建的群
            }
        }, 50);
    }
}
