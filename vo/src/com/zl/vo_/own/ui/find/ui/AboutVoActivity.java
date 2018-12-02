package com.zl.vo_.own.ui.find.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.ui.account.ui.WebViweActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/28.
 */

public class AboutVoActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected void initViews() {
        tv_title.setText("关于VO");
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_about_vo);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.rl_voIntroduce,R.id.rl_update})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_voIntroduce:
                Intent intent=new Intent(AboutVoActivity.this, WebViweActivity.class);
                intent.putExtra("url", ApiConstant.VO_INTRODUCE);
                intent.putExtra("title","VO介绍");
                startActivity(intent);
                break;
            case R.id.rl_update:

                break;

        }
    }
}
