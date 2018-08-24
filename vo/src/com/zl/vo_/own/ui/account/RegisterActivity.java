package com.zl.vo_.own.ui.account;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nim.uikit.common.util.sys.NetworkUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.zl.vo_.DemoCache;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.Preferences;
import com.zl.vo_.config.preference.UserPreferences;
import com.zl.vo_.contact.ContactHttpClient;
import com.zl.vo_.login.LoginActivity;
import com.zl.vo_.main.activity.MainActivity;
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.dialog.CustomerDialog;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.util.WeiboDialogUtils;
import com.zl.vo_.own.views.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/12.
 */

public class RegisterActivity extends BaseActivity{
    private Dialog dialog;
    @BindView(R.id.tv_title)
    TextView title;

    @BindView(R.id.register_nick)//昵称
    ClearEditText register_nick;

    @BindView(R.id.register_phone)//电话（账号）
    ClearEditText register_phone;

    @BindView(R.id.register_pwd)//密码
    ClearEditText register_pwd;
    @Override
    protected void initViews() {
        dialog = WeiboDialogUtils.createLoadingDialog(RegisterActivity.this, "加载中");
        title.setText("注册");
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_own_register);
    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.register_submit})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.register_submit:
                register();
                break;
        }
    }
    //注册
    private void register() {
        if (!checkRegisterContentValid()) {
            return;
        }

        if (!NetworkUtil.isNetAvailable(RegisterActivity.this)) {
            Toast.makeText(RegisterActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
            return;
        }
        // 注册流程
        final String account = register_phone.getText().toString();
        final String nickName = register_nick.getText().toString();
        final String password = register_pwd.getText().toString();

        ContactHttpClient.getInstance().register(account, nickName, password, new ContactHttpClient.ContactHttpCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(RegisterActivity.this, R.string.register_success, Toast.LENGTH_SHORT).show();
                DialogMaker.dismissProgressDialog();
            }

            @Override
            public void onFailed(int code, String errorMsg) {
                Toast.makeText(RegisterActivity.this, getString(R.string.register_failed, String.valueOf(code), errorMsg), Toast.LENGTH_SHORT)
                        .show();
                DialogMaker.dismissProgressDialog();
            }
        });
    }

    private boolean checkRegisterContentValid() {


        // 帐号检查
        String account = register_phone.getText().toString().trim();
        if (account.length() <= 0 || account.length() > 20) {
            Toast.makeText(this, R.string.register_account_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        // 昵称检查
        String nick = register_nick.getText().toString().trim();
        if (nick.length() <= 0 || nick.length() > 10) {
            Toast.makeText(this, R.string.register_nick_name_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        // 密码检查
        String password = register_pwd.getText().toString().trim();
        if (password.length() < 6 || password.length() > 20) {
            Toast.makeText(this, R.string.register_password_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;
    }





}
