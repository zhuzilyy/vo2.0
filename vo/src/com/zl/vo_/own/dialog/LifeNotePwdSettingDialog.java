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

public class LifeNotePwdSettingDialog extends Dialog {
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onSetPwdOnclickListener onSetPwdOnclickListener;//设置密码
    private onFixPwdOnclickListener onFixPwdOnclickListener;//设置密码
    private onCanclePwdOnclickListener onCanclePwdOnclickListener;//设置密码
    private onFindPwdOnclickListener onFindPwdOnclickListener;//设置密码
    public LifeNotePwdSettingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_lifenote_setting);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.rl_cancel,R.id.ll_setPwd,R.id.ll_fixPwd,R.id.ll_canclePwd,R.id.ll_findPwd})
    public void click(View view){
        switch (view.getId()){
            case R.id.rl_cancel:
                if (noOnclickListener!=null){
                    noOnclickListener.onNoClick();
                }
                break;
            case R.id.ll_setPwd:
                if (onSetPwdOnclickListener!=null){
                    onSetPwdOnclickListener.onNoClick();
                }
                break;
            case R.id.ll_fixPwd:
                if (onFixPwdOnclickListener!=null){
                    onFixPwdOnclickListener.onNoClick();
                }
                break;
            case R.id.ll_canclePwd:
                if (onCanclePwdOnclickListener!=null){
                    onCanclePwdOnclickListener.onNoClick();
                }
                break;
            case R.id.ll_findPwd:
                if (onFindPwdOnclickListener!=null){
                    onFindPwdOnclickListener.onNoClick();
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
    public interface onNoOnclickListener {
        public void onNoClick();
    }
    /*点击设置密码*/
    public void setPwdOnclickListener(onSetPwdOnclickListener onSetPwdOnclickListener) {
        this.onSetPwdOnclickListener = onSetPwdOnclickListener;
    }
    public interface onSetPwdOnclickListener {
        public void onNoClick();
    }
    /*点击修改密码*/
    public void setFixPwdOnclickListener(onFixPwdOnclickListener onFixPwdOnclickListener) {
        this.onFixPwdOnclickListener=onFixPwdOnclickListener;
    }
    public interface onFixPwdOnclickListener {
        public void onNoClick();
    }
    /*点击取消密码*/
    public void setCanclePwdOnclickListener(onCanclePwdOnclickListener onCanclePwdOnclickListener) {
        this.onCanclePwdOnclickListener = onCanclePwdOnclickListener;
    }
    public interface onCanclePwdOnclickListener {
        public void onNoClick();
    }
    /*点击找回密码*/
    public void setFindPwdOnclickListener(onFindPwdOnclickListener onFindPwdOnclickListener) {
        this.onFindPwdOnclickListener = onFindPwdOnclickListener;
    }
    public interface onFindPwdOnclickListener {
        public void onNoClick();
    }
}
