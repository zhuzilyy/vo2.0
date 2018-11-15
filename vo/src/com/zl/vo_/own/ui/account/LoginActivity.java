package com.zl.vo_.own.ui.account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.bean.UserInfoBean;
import com.zl.vo_.own.ui.account.bean.UserInfoData;
import com.zl.vo_.own.util.SPUtils;
import com.zl.vo_.own.util.StringToMD5;
import com.zl.vo_.own.views.ClearEditText;

import org.json.JSONException;
import org.json.JSONObject;

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
                String account = login_name.getText().toString();
                String pwd = login_pwd.getText().toString();
                if(checkParamsIsLegal(account,pwd)){
                    login(account,pwd);
                }
               // testLogin();
                break;
            case R.id.login_forgetpwd_tv:
                jumpActivity(this,ForgetPwdActivity.class);
                break;

        }

    }
    //检查参数是不是合法
    private boolean checkParamsIsLegal(String account, String pwd) {
        if (TextUtils.isEmpty(account)){
            Toast.makeText(this, "用户名不能位空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "密码不能位空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //登录
    private void login(String account,String pwd) {
        Map<String,String> params = new HashMap<>();
        params.put("mobile",account);
        params.put("password",pwd);
        ApiAccount.doLogin(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                String token = "";
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code=jsonObject.getString("code");
                    String message=jsonObject.getString("message");
                    if (code.equals(ApiConstant.SUCCESS_CODE)){
                        JSONObject jsonData = jsonObject.getJSONObject("data");
                        token= jsonData.getString("token");
                        SPUtils.put(LoginActivity.this,"cloudToken",token);
                        getUserInfo();
                    }else if(code.equals("404002")){
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {
                Log.i("tag",msg);
            }
        });

    }
    //获取个人信息
    private void getUserInfo() {
            ApiAccount.getUserInfo(this, new OnRequestDataListener() {
                @Override
                public void requestSuccess( String data) {
                    Log.i("tag","11"+data);
                    Gson gson = new Gson();
                    UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                    String code = userInfoBean.getCode();
                    if (code.equals(ApiConstant.SUCCESS_CODE)){
                        UserInfoData userInfoData = userInfoBean.getData();
                        String token = userInfoData.getToken();
                        String vo_code_set = userInfoData.getVo_code_set();
                        String avatar = userInfoData.getAvatar();
                        String vo_code = userInfoData.getVo_code();
                        String nickName = userInfoData.getName();
                        String sex = userInfoData.getSex();
                        String country = userInfoData.getCountry();
                        String city = userInfoData.getCity();
                        String province = userInfoData.getProvince();
                        String id = userInfoData.getId();
                        String vo_code_can = userInfoData.getVo_code_can();
                        String signature = userInfoData.getSignature();
                        SPUtils.put(LoginActivity.this,"token",token);
                        SPUtils.put(LoginActivity.this,"avatar",avatar);
                        SPUtils.put(LoginActivity.this,"vo_code",vo_code);
                        SPUtils.put(LoginActivity.this,"nickName",nickName);
                        SPUtils.put(LoginActivity.this,"sex",sex);
                        SPUtils.put(LoginActivity.this,"country",country);
                        SPUtils.put(LoginActivity.this,"city",city);
                        SPUtils.put(LoginActivity.this,"province",province);
                        SPUtils.put(LoginActivity.this,"id",id);
                        SPUtils.put(LoginActivity.this,"vo_code_can",vo_code_can);
                        SPUtils.put(LoginActivity.this,"signature",signature);
                        SPUtils.put(LoginActivity.this,"vo_code_set",vo_code_set);
                        //云信登录
                        cloudLogin(token,vo_code);
                    }
                }
                @Override
                public void requestFailure(int code, String msg) {

                }
            });
    }
    /*
    云信登录
     */
    private void cloudLogin(final String token,final String uuid) {
      /*  DialogMaker.showProgressDialog(this, null, getString(R.string.logining), true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (loginRequest != null) {
                    loginRequest.abort();
                    onLoginDone();
                }
            }
        }).setCanceledOnTouchOutside(false);*/
        loginRequest = NimUIKit.login(new LoginInfo(uuid, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {
                LogUtil.i(TAG, "login success");
                onLoginDone();
                DemoCache.setAccount(uuid);
                saveLoginInfo(uuid, token);
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
        return isDemo ? MD5.getStringMD5(password) : password;
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
