package com.zl.vo_.own.ui.mine.ui;


import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nim.avchatkit.controll.AVChatSoundPlayer;
import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.views.EaseSwitchButton;

import butterknife.BindView;

public class DonotDisturbModeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.switch_disrupt)
    EaseSwitchButton switch_disrupt;
    @BindView(R.id.ll_set_time)
    LinearLayout ll_set_time;
    @Override
    protected void initViews() {
        title.setText("勿扰模式");
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
        setContentView(R.layout.layout_donotdisturbmode_activity);
    }

    @Override
    protected void initListener() {
        //是否开启勿扰模式
        switch_disrupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_disrupt.isSwitchOpen()){
                    switch_disrupt.closeSwitch();
                    setNoDisruptTime(false);
                }else {
                    switch_disrupt.openSwitch();
                    setNoDisruptTime(true);
                }
            }
        });

    }

    @Override
    protected void setStatusBarColor() {

    }

    /**
     * 是否开启勿扰模式时间
     * @param isSetTime
     */
    private void setNoDisruptTime(boolean isSetTime){
        if(isSetTime){
           ll_set_time.setVisibility(View.VISIBLE);
        }else{
            ll_set_time.setVisibility(View.GONE);
        }
    }

}
