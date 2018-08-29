package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/27.
 */

public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected void initViews() {
        tv_title.setText("个人信息");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_useinfo);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.rl_nickName,R.id.rl_voId,R.id.rl_qrcode,R.id.rl_avatar,R.id.rl_more})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_avatar:
                jumpActivity(UserInfoActivity.this,AvatarActivity.class);
                break;
            //修改昵称
            case R.id.rl_nickName:
                jumpActivity(UserInfoActivity.this,ChangeNickNameActivity.class);
                break;
            //修改vo号
            case R.id.rl_voId:
                jumpActivity(UserInfoActivity.this,ChangeVoIDActivity.class);
                break;
            //二维码
            case R.id.rl_qrcode:
                jumpActivity(UserInfoActivity.this,QrCodeActivity.class);
                break;
            //更多
            case R.id.rl_more:
                jumpActivity(UserInfoActivity.this,ChangeMoreActivity.class);
                break;
        }
    }
}
