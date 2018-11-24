package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ui.widget.SwitchButton;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.UserPreferences;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.views.EaseSwitchButton;

import butterknife.BindView;

public class ChatSettingActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.switch_Usehandset)
    SwitchButton switch_Usehandset;
    @BindView(R.id.clearAllmsg_re)
    RelativeLayout clearAllmsg_re;
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

        boolean isEarPhone = NimUIKit.isEarPhoneModeEnable();
        Toast.makeText(this, "isEarPhone="+isEarPhone, Toast.LENGTH_SHORT).show();
        NimUIKit.setEarPhoneModeEnable(isEarPhone);
        switch_Usehandset.setCheck(isEarPhone);


    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.layout_chatsetting_activity);

    }

    @Override
    protected void initListener() {
        //听筒设置
        switch_Usehandset.setOnChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void OnChanged(View v, boolean checkState) {
                Toast.makeText(ChatSettingActivity.this, "chec="+checkState,Toast.LENGTH_SHORT).show();
                NimUIKit.setEarPhoneModeEnable(checkState);

            }
        });

        //清空聊天记录
        clearAllmsg_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    protected void setStatusBarColor() {

    }
}
