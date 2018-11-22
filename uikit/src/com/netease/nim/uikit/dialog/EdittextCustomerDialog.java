package com.netease.nim.uikit.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nim.uikit.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/23.
 */

public class EdittextCustomerDialog extends Dialog{
    TextView tv_dialogTitle;
    EditText et_dialogContent;
    TextView tv_dialogConfrim;
    TextView tv_dialog_confrim;
    RelativeLayout rl_cancel;
    private String dialogTitle,dialogContent,dialogConfrimText;
    //确定文本和取消文本的显示内容
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    public EdittextCustomerDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edittext_customer);
        ButterKnife.bind(this);
        initViews();
        initData();
    }
    private void initViews() {
        tv_dialogTitle=findViewById(R.id.tv_dialogTitle);
        et_dialogContent=findViewById(R.id.et_dialogContent);
        tv_dialogConfrim=findViewById(R.id.tv_dialog_confrim);
        tv_dialog_confrim=findViewById(R.id.tv_dialog_confrim);
        rl_cancel=findViewById(R.id.rl_cancel);
        tv_dialog_confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick(et_dialogContent.getText().toString());
                }
            }
        });
        rl_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
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
            et_dialogContent.setHint(dialogContent);
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
        public void onYesClick(String content);
    }
    public interface onNoOnclickListener {
        public void onNoClick();
    }


}
