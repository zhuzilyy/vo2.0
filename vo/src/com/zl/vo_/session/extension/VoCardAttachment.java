package com.zl.vo_.session.extension;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class VoCardAttachment extends CustomAttachment implements Serializable{
    private final String VOCARD_NICK = "vo_nick";
    private final String VOCARD_VOID= "vo_id";
    private final String VOCARD_AVATAR_URL = "vo_avatar";

    private String vo_nick;
    private String vo_id;
    private String vo_avatar;


    public String getVo_nick() {
        return vo_nick;
    }

    public void setVo_nick(String vo_nick) {
        this.vo_nick = vo_nick;
    }

    public String getVo_id() {
        return vo_id;
    }

    public void setVo_id(String vo_id) {
        this.vo_id = vo_id;
    }

    public String getVo_avatar() {
        return vo_avatar;
    }

    public void setVo_avatar(String vo_avatar) {
        this.vo_avatar = vo_avatar;
    }

    public VoCardAttachment() {
        super(CustomAttachmentType.VoCard);
    }

    @Override
    protected void parseData(JSONObject data) {

        vo_nick = data.getString(VOCARD_NICK);
        vo_id = data.getString(VOCARD_VOID);
        vo_avatar = data.getString(VOCARD_AVATAR_URL);



    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();

        data.put(VOCARD_NICK, getVo_nick());
        data.put(VOCARD_VOID, getVo_id());
        data.put(VOCARD_AVATAR_URL, getVo_avatar());

        return data;
    }
}
