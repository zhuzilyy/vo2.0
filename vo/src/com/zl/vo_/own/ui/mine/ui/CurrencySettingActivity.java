package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.views.EaseSwitchButton;

import butterknife.BindView;

public class CurrencySettingActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.switch_Verification)
    EaseSwitchButton switch_Verification;
    @BindView(R.id.switch_Recommended_address_friends)
    EaseSwitchButton switch_Recommended_address_friends;
    @Override
    protected void initViews() {
        title.setText("通用");
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
        setContentView(R.layout.layout_currencysetting_activity);
    }

    @Override
    protected void initListener() {
        //加我为好友时需要验证
        switch_Verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_Verification.isSwitchOpen()){
                    switch_Verification.closeSwitch();
                }else {
                    switch_Verification.openSwitch();
                }
            }
        });
        //向我推荐通讯录好友
        switch_Recommended_address_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_Recommended_address_friends.isSwitchOpen()){
                    switch_Recommended_address_friends.closeSwitch();
                }else {
                    switch_Recommended_address_friends.openSwitch();
                }
            }
        });


    }

    @Override
    protected void setStatusBarColor() {

    }
}
