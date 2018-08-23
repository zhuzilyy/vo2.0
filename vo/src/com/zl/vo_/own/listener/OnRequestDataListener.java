package com.zl.vo_.own.listener;


/**
 * Created by Administrator on 2016/8/10.
 * Author: XuDeLong
 */
public interface OnRequestDataListener {
    void requestSuccess(String data);

    void requestFailure(int code, String msg);
}
