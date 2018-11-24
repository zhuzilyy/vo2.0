package com.netease.nim.uikit.business.recent.holder;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.netease.nim.uikit.R;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.wrapper.NimUserInfoProvider;
import com.netease.nim.uikit.business.recent.TeamMemberAitHelper;
import com.netease.nim.uikit.business.team.helper.TeamHelper;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseQuickAdapter;
import com.netease.nim.uikit.common.ui.teamavatar.utils.DensityUtils;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachment;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamMember;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;

import java.util.ArrayList;
import java.util.List;

public class TeamRecentViewHolder extends CommonRecentViewHolder {

    public TeamRecentViewHolder(BaseQuickAdapter adapter) {
        super(adapter);
    }

    @Override
    protected String getContent(RecentContact recent) {
        String content = descOfMsg(recent);

        String fromId = recent.getFromAccount();
        if (!TextUtils.isEmpty(fromId)
                && !fromId.equals(NimUIKit.getAccount())
                && !(recent.getAttachment() instanceof NotificationAttachment)) {
            String tid = recent.getContactId();
            String teamNick = getTeamUserDisplayName(tid, fromId);
            content = teamNick + ": " + content;

            if (TeamMemberAitHelper.hasAitExtension(recent)) {
                if (recent.getUnreadCount() == 0) {
                    TeamMemberAitHelper.clearRecentContactAited(recent);
                } else {
                    content = TeamMemberAitHelper.getAitAlertString(content);
                }
            }
        }

        return content;
    }

    private String getTeamUserDisplayName(String tid, String account) {
        return TeamHelper.getTeamMemberDisplayName(tid, account);
    }

    @Override
    protected void loadPortrait(RecentContact recent) {
        super.loadPortrait(recent);
        // 设置头像
        if (recent.getSessionType() == SessionTypeEnum.P2P) {
            imgHead.loadBuddyAvatar(recent.getContactId());
        } else if (recent.getSessionType() == SessionTypeEnum.Team) {
            Team team = NimUIKit.getTeamProvider().getTeamById(recent.getContactId());

            final int imageSize = DensityUtils.dp2px(NimUIKit.getContext(), 55);

            NIMClient.getService(TeamService.class).queryMemberList(recent.getContactId()).setCallback(new RequestCallbackWrapper<List<TeamMember>>() {
                @Override
                public void onResult(int code, final List<TeamMember> members, Throwable exception) {
                    if (members.size() > 2) {
                        List<String> imageUrls = new ArrayList<>();
                        List<String> headUrls = getHeadUrl(members);
                        for (int i = 0; i < headUrls.size(); i++) {
                            imageUrls.add(headUrls.get(i));
                        }

                        teamImageView.displayImage(imageUrls)
                                .synthesizedWidthHeight(imageSize, imageSize)
                                .defaultImage(R.drawable.nim_image_default)
                                .load();

                    }
                }
            });


        }
    }

    /**
     * 获取指定组成员的头像
     *
     * @param members
     * @return
     */
    private List<String> getHeadUrl(List<TeamMember> members) {

        List<String> headUels = new ArrayList<>();

        int count = members.size() >= 9?9:members.size();

        for (int i = 0; i < count; i++) {

            headUels.add(getTeamMemberAvatar(members.get(i).getAccount()));

        }

        return headUels;

    }

    /**
     * 根据ID获取群成员的头像
     * @param account
     * @return
     */
    private String getTeamMemberAvatar(String account) {
        UserInfoProvider userInfoProvider = new NimUserInfoProvider(NimUIKit.getContext());


        return userInfoProvider.getUserInfo(account).getAvatar();
    }
}
