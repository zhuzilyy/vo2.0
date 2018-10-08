package com.zl.vo_.session.action;

import com.netease.nim.uikit.business.session.actions.BaseAction;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.zl.vo_.R;
import com.zl.vo_.session.extension.VoCardAttachment;

public class VoCardAction  extends BaseAction {
    /**
     * 构造函数
     *
     */
    public VoCardAction() {
        super(R.drawable.message_plus_tip_selector, R.string.input_panel_card);
    }

    @Override
    public void onClick() {
        VoCardAttachment attachment = new VoCardAttachment();
        attachment.setVo_nick("冬日的夜猫");
        attachment.setVo_id("vo_963852741");
        attachment.setVo_avatar("https://img04.sogoucdn.com/app/a/100520093/f3ef84e6f0e681f1-9ab23d4aae282666-8b9b47ac55fc38b4abb95453f0e20645.jpg");

        // 以下 "图文链接：" + attachment.getTitle() 用来显示app消息推送时，图片显示的内容。
        IMMessage message = MessageBuilder.createCustomMessage(getAccount(), getSessionType(), "图文链接：" + attachment.getVo_nick(), attachment);
        sendMessage(message);

    }
}
