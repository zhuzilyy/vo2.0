package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.views.EaseSwitchButton;

import butterknife.BindView;

public class ChatSettingActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.switch_Usehandset)
    EaseSwitchButton switch_Usehandset;
    @Override
    protected void initViews() {
        title.setText("聊天");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.layout_chatsetting_activity);

    }

    @Override
    protected void initListener() {

        //使用听筒播放语音
        switch_Usehandset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_Usehandset.isSwitchOpen()){
                    switch_Usehandset.closeSwitch();
                }else {
                    switch_Usehandset.openSwitch();
                }
            }
        });


    }

    @Override
    protected void setStatusBarColor() {

    }
}
