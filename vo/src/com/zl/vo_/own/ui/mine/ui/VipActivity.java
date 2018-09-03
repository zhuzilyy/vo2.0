package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.ui.mine.adapter.VipAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/29.
 */

public class VipActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.lv_vip)
    ListView lv_vip;
    private VipAdapter vipAdapter;
    @Override
    protected void initViews() {
        tv_title.setText("VO会员");
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_vip);
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
