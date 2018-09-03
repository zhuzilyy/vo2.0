package com.zl.vo_.own.ui.mine.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/31.
 */

public class PersonSignActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @Override
    protected void initViews() {
        tv_title.setText("个性签名");
        tv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_person_sign);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
