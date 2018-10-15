package com.zl.vo_.own.ui.account;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.zl.vo_.DemoCache;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.Preferences;
import com.zl.vo_.config.preference.UserPreferences;
import com.zl.vo_.contact.ContactHttpClient;
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.MainActivity;
import com.zl.vo_.own.util.MobileNumberUtil;
import com.zl.vo_.own.util.WeiboDialogUtils;
import com.zl.vo_.own.views.ClearEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/12.
 */

public class RegisterActivity extends BaseActivity{
    private Dialog dialog;
    @BindView(R.id.tv_title)
    TextView title;

    @BindView(R.id.register_nick)
    ClearEditText register_nick;
    @BindView(R.id.register_phone)
    ClearEditText register_phone;
    @BindView(R.id.register_code)
    ClearEditText register_code;
    @BindView(R.id.register_pwd)
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
                String nickName = register_nick.getText().toString();
                String phone = register_phone.getText().toString();
                String confirmCode = register_code.getText().toString();
                String pwd= register_pwd.getText().toString();
                if (checkParamsIsLegal(nickName,phone,confirmCode,pwd)){
                    doRegister(phone,nickName,pwd);
                }
                break;
        }
    }
    //检查注册的参数是不是空
    private boolean checkParamsIsLegal(String nickName, String phone, String confirmCode, String pwd) {
        if (TextUtils.isEmpty(nickName)){
            Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        //判断手机号码是不是合法
        if (!MobileNumberUtil.isMobileNO(phone)){
            Toast.makeText(this, "对不起手机号码格式有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(confirmCode)){
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (pwd.length()<6){
            Toast.makeText(this, "密码长度不能小于6位", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //注册
    private void register(final String account,final String nickName,final String pwd) {
       /* ContactHttpClient.getInstance().register(account, nickName, pwd, new ContactHttpClient.ContactHttpCallback<Void>() {
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
        });*/
    }
    //自己服务器的注册接口
    private void doRegister(String account, String nickName, String password) {
        Map<String,String> params = new HashMap<>();
        params.put("mobile",account);
        params.put("password",password);
        params.put("captcha","000000");
        params.put("name",nickName);
        ApiAccount.doRegister(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    if (code.equals(ApiConstant.SUCCESS_CODE)){
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void login() {
        dialog.show();
        final String account="xzy031424";
        //final String token = tokenFromPassword("031424xzy");
        final String token = "031424xzy";
        NimUIKit.login(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                dialog.dismiss();
                DemoCache.setAccount(account);
                saveLoginInfo(account, token);
                // 初始化消息提醒配置
                initNotificationConfig();
                // 进入主界面
                //MainActivity.start(RegisterActivity.this, null);
                jumpActivity(RegisterActivity.this, MainActivity.class);
                finish();
            }
            @Override
            public void onFailed(int code) {
                dialog.dismiss();
                if (code == 302 || code == 404) {
                    Toast.makeText(RegisterActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable exception) {
                Toast.makeText(RegisterActivity.this, R.string.login_exception, Toast.LENGTH_LONG).show();
            }
        });
    }
    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);
    }
    //DEMO中使用 username 作为 NIM 的account ，md5(password) 作为 token
    //开发者需要根据自己的实际情况配置自身用户系统和 NIM 用户系统的关系
    private String tokenFromPassword(String password) {
        String appKey = readAppKey(this);
        boolean isDemo = "f09dda3419685a8d64d627c2fe97bafd".equals(appKey);
               // || "f09dda3419685a8d64d627c2fe97bafd".equals(appKey);
        return isDemo ? MD5.getStringMD5(password) : password;
    }
    private static String readAppKey(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.netease.nim.appKey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
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

}
