package com.zl.vo_.own.ui.account;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.ui.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    public TextView tv_title;

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


    @OnClick({R.id.iv_back,R.id.login_submit,R.id.login_forgetpwd_tv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.login_submit:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                break;
            case R.id.login_forgetpwd_tv:

                break;

        }

    }
}
