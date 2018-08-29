package com.zl.vo_.own.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.zl.vo_.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/27.
 */

public class PrivateFriendsDialog extends Dialog {
    //确定文本和取消文本的显示内容
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onFriendsOnclickListener onFriendsOnclickListener;//点击设置加密好友
    private onAddOrReduceFriendsOnclickListener onAddOrReduceFriendsOnclickListener;//点击增减加密好友
    public PrivateFriendsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_private_friend);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.ll_setPrivateFriends,R.id.ll_addOrReducePrivateFriends,R.id.rl_cancel})
    public void click(View view){
        switch (view.getId()){
            case R.id.rl_cancel:
                if (noOnclickListener!=null){
                    noOnclickListener.onNoClick();
                }
                break;
            case R.id.ll_setPrivateFriends:
                if (onFriendsOnclickListener!=null){
                    onFriendsOnclickListener.onClick();
                }
                break;
            case R.id.ll_addOrReducePrivateFriends:
                if (onAddOrReduceFriendsOnclickListener!=null){
                    onAddOrReduceFriendsOnclickListener.onClick();
                }
                break;
        }
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(onNoOnclickListener onNoOnclickListener) {
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置将好友加密按钮的显示内容和监听
     *
     * @param onFriendsOnclickListener
     */
    public void setFriendsOnclickListener(onFriendsOnclickListener onFriendsOnclickListener) {
        this.onFriendsOnclickListener = onFriendsOnclickListener;
    }
    /**
     * 设置增减加密好友按钮的显示内容和监听
     *
     * @param onAddOrReduceFriendsOnclickListener
     */
    public void setAddOrReduceFriendsOnclickListener(onAddOrReduceFriendsOnclickListener onAddOrReduceFriendsOnclickListener) {
        this.onAddOrReduceFriendsOnclickListener = onAddOrReduceFriendsOnclickListener;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onFriendsOnclickListener {
        public void onClick();
    }
    public interface onAddOrReduceFriendsOnclickListener {
        public void onClick();
    }
    public interface onNoOnclickListener {
        public void onNoClick();
    }

}
