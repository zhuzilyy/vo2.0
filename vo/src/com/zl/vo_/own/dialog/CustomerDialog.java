package com.zl.vo_.own.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zl.vo_.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/23.
 */

public class CustomerDialog  extends Dialog{
   @BindView(R.id.tv_dialogTitle)
    TextView tv_dialogTitle;
    @BindView(R.id.tv_dialogContent)
    TextView tv_dialogContent;
    @BindView(R.id.tv_dialog_confrim)
    TextView tv_dialogConfrim;
    private String dialogTitle,dialogContent,dialogConfrimText;
    //确定文本和取消文本的显示内容
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    public CustomerDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customer);
        ButterKnife.bind(this);
        initData();
    }
    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (!TextUtils.isEmpty(dialogTitle)) {
            tv_dialogTitle.setText(dialogTitle);
        }
        if (!TextUtils.isEmpty(dialogContent)) {
            tv_dialogContent.setText(dialogContent);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(dialogConfrimText)) {
            tv_dialogConfrim.setText(dialogConfrimText);
        }
    }
    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setDialogTitle(String title) {
        dialogTitle = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setDialogMessage(String message) {
        dialogContent = message;
    }
    /**
     * 从外界Activity为Dialog确定按钮设置dialog的确定按钮的文本
     *
     * @param confirmText
     */
    public void setDialogConfirmText(String confirmText) {
        dialogConfrimText = confirmText;
    }
    @OnClick({R.id.tv_dialog_confrim,R.id.rl_cancel})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_dialog_confrim:
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
