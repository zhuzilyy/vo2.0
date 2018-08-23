package com.zl.vo_.own.ui.account;

import android.widget.ImageView;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.iv_back) public ImageView iv_back;
    @BindView(R.id.tv_title) public TextView tv_title;

    @Override
    protected void initViews() {
        tv_title.setText("登录");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_own_login);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
}
