package com.zl.vo_.own.ui.find.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.ui.account.WebViweActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/28.
 */

public class HelpAndFeedBackActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected void initViews() {
        tv_title.setText("帮助与反馈");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_help_feedback);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.rl_function,R.id.rl_feedback})
    public void click(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_function:
                intent = new Intent(this, WebViweActivity.class);
                intent.putExtra("title","功能介绍");
                intent.putExtra("url", ApiConstant.FUNCTION_INTRODUCE);
                startActivity(intent);
                break;
            case R.id.rl_feedback:
                intent = new Intent(this, FeebackActivity.class);
                startActivity(intent);
                break;
        }
    }
}
