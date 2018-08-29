package com.zl.vo_.own.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zl.vo_.R;

/**
 * Created by Administrator on 2018/5/9.
 */

public class VipDialog extends Dialog {
    private Button btn_openvip;
    private TextView vipfunction;

    private View.OnClickListener defaultClickLister=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cancel();
        }
    };

    private View.OnClickListener openVipListener=defaultClickLister;
    private View.OnClickListener vipFunctionListener=defaultClickLister;

    public VipDialog(Context context) {
        super(context, R.style.MyDialog);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_vip);
        btn_openvip = findViewById(R.id.btn_openvip);
        vipfunction = findViewById(R.id.tv_vipfunction);

    }
    @Override
    public void show() {
        super.show();
        show(this);
    }
    private void show(VipDialog dialog){
        if(btn_openvip!=null){
            dialog.btn_openvip.setOnClickListener(openVipListener);
        }
        if(vipfunction!=null){
            dialog.vipfunction.setOnClickListener(vipFunctionListener);
        }
    }
    public static class Builder{
        VipDialog dialog;
        public Builder(Context context) {
            dialog=new VipDialog(context);
        }
        public VipDialog.Builder setOpenVipListener(View.OnClickListener l){
            dialog.openVipListener=l;
            return this;
        }
        public VipDialog.Builder setVipFunctionListener(View.OnClickListener l){
            dialog.vipFunctionListener=l;
            return this;
        }
        public VipDialog create(){
            return  dialog;
        }

    }
}
