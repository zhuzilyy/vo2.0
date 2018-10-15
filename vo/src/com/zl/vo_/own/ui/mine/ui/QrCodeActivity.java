package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netease.nim.rtskit.common.imageview.CircleImageView;
import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.util.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/27.
 */

public class QrCodeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv__head)
    CircleImageView iv__head;
    @Override
    protected void initViews() {
        tv_title.setText("我的二维码");
        //设置默认值
        String avatar= (String) SPUtils.get(this,"avatar","");
        Glide.with(this).load(avatar).into(iv__head);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_qr_code);
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
