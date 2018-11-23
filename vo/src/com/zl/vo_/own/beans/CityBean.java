package com.zl.vo_.own.beans;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class CityBean extends BaseIndexPinyinBean {

    private String city;//城市名字
    private boolean isTop;//是否是最上面的 不需要被转化成拼音的

    public String getVo_code() {
        return vo_code;
    }

    public void setVo_code(String vo_code) {
        this.vo_code = vo_code;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    private String vo_code;
    private String avatar;
    private String name;

    public int getIs_friend() {
        return is_friend;
    }

    public void setIs_friend(int is_friend) {
        this.is_friend = is_friend;
    }

    public int getIs_app() {
        return is_app;
    }

    public void setIs_app(int is_app) {
        this.is_app = is_app;
    }

    private String mobile;
    private int is_friend;
    private int is_app;





    public CityBean() {
    }

    public CityBean(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public CityBean setCity(String city) {
        this.city = city;
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public CityBean setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public String getTarget() {
        return city;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }


    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}
