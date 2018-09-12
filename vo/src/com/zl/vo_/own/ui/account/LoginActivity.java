package com.zl.vo_.own.ui.account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.zl.vo_.DemoCache;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.Preferences;
import com.zl.vo_.config.preference.UserPreferences;
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.MainActivity;
import com.zl.vo_.own.util.StringToMD5;
import com.zl.vo_.own.views.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.login_name)
    ClearEditText login_name;
    @BindView(R.id.login_pwd)
    ClearEditText login_pwd;
    //------------------------
    private AbortableFuture<LoginInfo> loginRequest;
    private static final String TAG = com.zl.vo_.login.LoginActivity.class.getSimpleName();

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
                login();
               // testLogin();
                break;
            case R.id.login_forgetpwd_tv:
                jumpActivity(this,ForgetPwdActivity.class);
                break;

        }

    }

    private void testLogin() {
        String md5Str = StringToMD5.stringToMD5("userVoUser2018-9-10vo_app");
        Map<String,String> params = new HashMap<>();
        params.put("tel","15524108397");
        params.put("api_token",md5Str);
        ApiAccount.getConfirmCode(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                Log.i("tag",data+"=========22222===");
            }
            @Override
            public void requestFailure(int code, String msg) {
                Log.i("tag",msg);
                Log.i("tag",code+"========11111111=======");
            }
        });
    }
    /*
    登录
     */
    private void login() {
        DialogMaker.showProgressDialog(this, null, getString(R.string.logining), true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (loginRequest != null) {
                    loginRequest.abort();
                    onLoginDone();
                }
            }
        }).setCanceledOnTouchOutside(false);

        final String account = login_name.getText().toString();
        //final String token = login_pwd.getText().toString();
        final String token = tokenFromPassword(login_pwd.getText().toString());

        loginRequest = NimUIKit.login(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {
                LogUtil.i(TAG, "login success");
                onLoginDone();
                DemoCache.setAccount(account);
                saveLoginInfo(account, token);

                // 初始化消息提醒配置
                initNotificationConfig();

                // 进入主界面
                startActivity(new Intent(LoginActivity.this, com.zl.vo_.main.activity.MainActivity.class));
                finish();
            }
            @Override
            public void onFailed(int code) {
                onLoginDone();
                if (code == 302 || code == 404) {
                    Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable throwable) {
                Toast.makeText(LoginActivity.this, R.string.login_exception, Toast.LENGTH_LONG).show();
                onLoginDone();
            }
        });
    }
    /*
    登录成功后，保存账号和token
     */
    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);
    }

    /*
    初始化消息提醒配置
     */
    private void initNotificationConfig() {
        // 初始化消息提醒
        NIMClient.toggleNotification(UserPreferences.getNotificationToggle());

        // 加载状态栏配置
        StatusBarNotificationConfig statusBarNotificationConfig = UserPreferences.getStatusConfig();
        if (statusBarNotificationConfig == null) {
            statusBarNotificationConfig = DemoCache.getNotificationConfig();
            UserPreferences.setStatusConfig(statusBarNotificationConfig);
        }
        // 更新配置
        NIMClient.updateStatusBarNotificationConfig(statusBarNotificationConfig);
    }

    private void onLoginDone() {
        loginRequest = null;
        DialogMaker.dismissProgressDialog();
    }

    //DEMO中使用 username 作为 NIM 的account ，md5(password) 作为 token
    //开发者需要根据自己的实际情况配置自身用户系统和 NIM 用户系统的关系
    private String tokenFromPassword(String password) {
        String appKey = readAppKey(this);
        boolean isDemo = "f09dda3419685a8d64d627c2fe97bafd".equals(appKey);
       // return isDemo ? MD5.getStringMD5(password) : password;
        return password;
    }

    private static String readAppKey(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.zl.vo_");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
