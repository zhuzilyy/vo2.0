package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.uikit.common.ui.widget.SwitchButton;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.mixpush.MixPushService;
import com.zl.vo_.DemoCache;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.UserPreferences;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.views.EaseSwitchButton;

import butterknife.BindView;

public class NewMsgSettingsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.switch_notification)    //是否接受新消息
            SwitchButton switch_notification;
    @BindView(R.id.notifySwitch_details)   //通知是否显示详情
            SwitchButton switch_notify_details;
    @BindView(R.id.notifySwitch_voice)     //是否开启声音
            SwitchButton switch_notify_voice;
    @BindView(R.id.notifySwitch_vibrate)   //是否开启震动
            SwitchButton switch_notify_vibrate;
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

        //新消息通知
        boolean isNotificationToggle = UserPreferences.getNotificationToggle();
        if(isNotificationToggle){
            switch_notification.setCheck(isNotificationToggle);
        }
        //震动
        boolean isvibrate = UserPreferences.getVibrateToggle();
        if(isvibrate){
            switch_notify_vibrate.setCheck(isvibrate);
        }

        //声音
        boolean isSound = UserPreferences.getRingToggle();
        if(isSound){
            switch_notify_voice.setCheck(isSound);
        }

        //通知显示详情
        boolean isNotifyDetails = UserPreferences.getNoticeContentToggle();
        if(isNotifyDetails){
            switch_notify_details.setCheck(isNotifyDetails);
        }






    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.layout_newmsgsettings_activity);

    }
    //switchButton 按钮点击事件
    @Override
    protected void initListener() {
        //接收新消息
        switch_notification.setOnChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void OnChanged(View v, boolean checkState) {
                setMessageNotify(checkState);
            }
        });

        //震动
        switch_notify_vibrate.setOnChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void OnChanged(View v, boolean checkState) {
                UserPreferences.setVibrateToggle(checkState);
                StatusBarNotificationConfig config = UserPreferences.getStatusConfig();
                config.vibrate = checkState;
                UserPreferences.setStatusConfig(config);
                NIMClient.updateStatusBarNotificationConfig(config);
            }
        });
        //声音
        switch_notify_voice.setOnChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void OnChanged(View v, boolean checkState) {
                Toast.makeText(NewMsgSettingsActivity.this, "voice ="+checkState, Toast.LENGTH_SHORT).show();
                UserPreferences.setRingToggle(checkState);
                StatusBarNotificationConfig config = UserPreferences.getStatusConfig();
                config.ring = checkState;
                UserPreferences.setStatusConfig(config);
                NIMClient.updateStatusBarNotificationConfig(config);
            }
        });

        //通知显示详情
        switch_notify_details.setOnChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void OnChanged(View v, boolean checkState) {
                Toast.makeText(NewMsgSettingsActivity.this, "details="+checkState, Toast.LENGTH_SHORT).show();
                UserPreferences.setNoticeContentToggle(checkState);
                StatusBarNotificationConfig config2 = UserPreferences.getStatusConfig();
                config2.titleOnlyShowAppName = checkState;
                UserPreferences.setStatusConfig(config2);
                NIMClient.updateStatusBarNotificationConfig(config2);
            }
        });

    }

    @Override
    protected void setStatusBarColor() {

    }


    private void setMessageNotify(final boolean checkState) {
        // 如果接入第三方推送（小米），则同样应该设置开、关推送提醒
        // 如果关闭消息提醒，则第三方推送消息提醒也应该关闭。
        // 如果打开消息提醒，则同时打开第三方推送消息提醒。
        Toast.makeText(this, "&&+"+checkState, Toast.LENGTH_SHORT).show();
        NIMClient.getService(MixPushService.class).enable(checkState).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void param) {
                Toast.makeText(NewMsgSettingsActivity.this, R.string.user_info_update_success+"---"+checkState, Toast.LENGTH_SHORT).show();
                switch_notification.setCheck(checkState);
                UserPreferences.setNotificationToggle(checkState);
               // setToggleNotification(checkState);
            }

            @Override
            public void onFailed(int code) {
                switch_notification.setCheck(!checkState);
                // 这种情况是客户端不支持第三方推送
                if (code == ResponseCode.RES_UNSUPPORT) {
                    switch_notification.setCheck(checkState);
                    //setToggleNotification(checkState);
                } else if (code == ResponseCode.RES_EFREQUENTLY) {
                    Toast.makeText(NewMsgSettingsActivity.this, R.string.operation_too_frequent, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewMsgSettingsActivity.this, R.string.user_info_update_failed, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onException(Throwable exception) {

            }
        });
    }
}
