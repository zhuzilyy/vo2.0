package com.zl.vo_.own.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.zl.vo_.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/24.
 */

public class LifeNotePwdDialog extends Dialog {
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    //确定文本和取消文本的显示内容
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    public LifeNotePwdDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_life_note);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.tv_confirm,R.id.rl_cancel})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_confirm:
                String content=et_pwd.getText().toString().trim();
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick(content);
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
    public interface onYesOnclickListener{
        public void onYesClick(String content);
    }
    public interface onNoOnclickListener {
        public void onNoClick();
    }
}
