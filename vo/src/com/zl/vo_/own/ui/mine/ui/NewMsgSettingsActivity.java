package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.vo_.DemoCache;
import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.views.EaseSwitchButton;

import butterknife.BindView;

public class NewMsgSettingsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.switch_notification)    //是否接受新消息
    EaseSwitchButton switch_notification;
    @BindView(R.id.notifySwitch_details)   //通知是否显示详情
    EaseSwitchButton switch_notify_details;
    @BindView(R.id.notifySwitch_voice)     //是否开启声音
    EaseSwitchButton switch_notify_voice;
    @BindView(R.id.notifySwitch_vibrate)   //是否开启震动
    EaseSwitchButton switch_notify_vibrate;
    @Override
    protected void initViews() {
        title.setText("新消息设置");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        //新消息提醒
        if(switch_notification.isSwitchOpen()){
            switch_notification.closeSwitch();

        }
        //通知是否显示详情
        if(switch_notify_details.isSwitchOpen()){
            switch_notify_details.closeSwitch();
        }
        //通知是否开启声音
        if(switch_notify_voice.isSwitchOpen()){
            switch_notify_voice.closeSwitch();
        }
        //通知是否开启震动
        if(switch_notify_vibrate.isSwitchOpen()){
            switch_notify_vibrate.closeSwitch();
        }
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.layout_newmsgsettings_activity);

    }

    @Override
    protected void initListener() {
        //新消息提醒
        switch_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(switch_notification.isSwitchOpen()){
                    switch_notification.closeSwitch();
                }else {
                    switch_notification.openSwitch();
                }
            }
        });
        //通知是否详情
        switch_notify_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_notify_details.isSwitchOpen()){
                    switch_notify_details.closeSwitch();
                }else {
                    switch_notify_details.openSwitch();
                }
            }
        });
        //通知是否开启声音
        switch_notify_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_notify_voice.isSwitchOpen()){
                    switch_notify_voice.closeSwitch();
                }else{
                    switch_notify_voice.openSwitch();
                }
            }
        });
        //通知是否开启震动
        switch_notify_vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_notify_vibrate.isSwitchOpen()){
                    switch_notify_vibrate.closeSwitch();
                }else {
                    switch_notify_vibrate.openSwitch();
                }
            }
        });
    }

    @Override
    protected void setStatusBarColor() {

    }
}
