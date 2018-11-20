package com.zl.vo_.own.ui.mine.ui;

import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.vo_.R;
import com.zl.vo_.config.preference.Preferences;
import com.zl.vo_.login.LogoutHelper;
import com.zl.vo_.main.activity.NoDisturbActivity;
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.Login_Register_Acitivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.re_newmsgNotify)
    RelativeLayout re_newmsgNotify;
    @BindView(R.id.re_do_not_disturb_mode)
    RelativeLayout re_do_not_disturb_mode;
    @BindView(R.id.re_chat)
    RelativeLayout re_chat;
    @BindView(R.id.re_currency)
    RelativeLayout re_currency;
    @BindView(R.id.re_quite)
    RelativeLayout re_quite;
    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView title;

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        title.setText("设置");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.re_newmsgNotify,R.id.re_do_not_disturb_mode,R.id.re_chat,
            R.id.re_currency, R.id.re_quite,R.id.iv_back})
    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.iv_back:
                 finish();
                 break;
             case R.id.re_newmsgNotify:
                 /*新消息提醒*/
                 startActivity(new Intent(SettingsActivity.this,NewMsgSettingsActivity.class));
                 break;
             case R.id.re_do_not_disturb_mode:
                /*勿扰模式*/
                //startActivity(new Intent(SettingsActivity.this,DonotDisturbModeActivity.class));
                 startActivity(new Intent(SettingsActivity.this,NoDisturbActivity.class));
                 break;
             case R.id.re_chat:
                 /*聊天设置*/
                 startActivity(new Intent(SettingsActivity.this,ChatSettingActivity.class));
                 break;
             case R.id.re_currency:
                 /*通用设置*/
                 startActivity(new Intent(SettingsActivity.this,CurrencySettingActivity.class));
                 break;
             case R.id.re_quite:
                 //退出登录
                 logout();
                 break;
                 }
    }

    /**
     * 退出登录
     */
    private void logout() {
        final Dialog dialog = new Dialog(SettingsActivity.this);
        View vv = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.dia_logout,null);
        dialog.setContentView(vv);
        //退出登录
        LinearLayout ll_quite_login =vv.findViewById(R.id.ll_quite_login);
        //退出vo
        LinearLayout ll_quite_vo =vv.findViewById(R.id.ll_quite_vo);

        ll_quite_vo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              dialog.dismiss();
            }
        });

        ll_quite_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = false;
                flag = logoutServer();
                if(flag){
                    Preferences.saveUserToken("");
                    // 清理缓存&注销监听
                    LogoutHelper.logout();
                    // 启动登录
                    Login_Register_Acitivity.start(SettingsActivity.this);
                    finish();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }


    //
    public boolean logoutServer(){
         final boolean flag = false;
        Map<String,String> params = new HashMap<>();

        ApiAccount.logoutServer(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    if("2000".equals(code)){
                       // flag = true;
                    }
                }catch (Exception e){

                }

            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });








        return flag;
    }

}
