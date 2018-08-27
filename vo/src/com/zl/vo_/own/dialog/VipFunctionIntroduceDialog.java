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

public class VipFunctionIntroduceDialog extends Dialog{
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    public VipFunctionIntroduceDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_vip_introduce);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.tv_confirm,R.id.rl_cancel})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_confirm:
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
                break;
            case R.id.rl_cancel:
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
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
     * 设置确定按钮的显示内容和监听
     *
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(onYesOnclickListener onYesOnclickListener) {
        this.yesOnclickListener = onYesOnclickListener;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }
    public interface onNoOnclickListener {
        public void onNoClick();
    }
}
