package com.zl.vo_.own.ui.friend.bean;

import com.zl.vo_.own.beans.CityBean;
import com.zl.vo_.own.ui.account.bean.UserInfoData;

import java.util.List;

/**
 * Created by tianou on 2018/11/23.
 */

public class BookUserInfoBean {
    private String code;
    private String message;
    private List<CityBean> data;


    public List<CityBean> getData() {
        return data;
    }

    public void setData(List<CityBean> data) {
        this.data = data;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
