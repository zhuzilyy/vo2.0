package com.netease.nim.uikit.business.recent.holder;

import android.text.TextUtils;

import com.netease.nim.uikit.R;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.business.recent.TeamMemberAitHelper;
import com.netease.nim.uikit.business.team.helper.TeamHelper;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseQuickAdapter;
import com.netease.nim.uikit.common.ui.teamavatar.utils.DensityUtils;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachment;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.team.model.Team;

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

            int imageSize = DensityUtils.dp2px(NimUIKit.getContext(), 55);



            List<String> imageUrls = new ArrayList<>();
            imageUrls.add("http://www.zhlzw.com/UploadFiles/Article_UploadFiles/201204/20120412123914329.jpg");
            imageUrls.add("http://pic.58pic.com/58pic/15/14/14/18e58PICMwt_1024.jpg");
            imageUrls.add("http://dynamic-image.yesky.com/740x-/uploadImages/2014/289/01/IGS09651F94M.jpg");
            /*  imageUrls.add("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg");
            imageUrls.add("http://www.bz55.com/uploads/allimg/150701/140-150F1142638.jpg");
            imageUrls.add("http://pic.58pic.com/58pic/15/36/00/73b58PICgvY_1024.jpg");
            imageUrls.add("http://pic.58pic.com/58pic/15/35/96/97j58PICUhD_1024.jpg");*/
            this.teamImageView.displayImage(imageUrls)
                    .synthesizedWidthHeight(imageSize, imageSize)
                    .defaultImage(R.drawable.nim_image_default)
                    .load();

        }
    }
}
