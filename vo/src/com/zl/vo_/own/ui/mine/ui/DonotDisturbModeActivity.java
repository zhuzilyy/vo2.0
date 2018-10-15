package com.zl.vo_.own.ui.mine.ui;


import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;

import butterknife.BindView;

public class DonotDisturbModeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;
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

    }

    @Override
    protected void setStatusBarColor() {

    }
}
