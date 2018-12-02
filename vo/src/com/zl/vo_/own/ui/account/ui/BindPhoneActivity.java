package com.zl.vo_.own.ui.account.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huawei.updatesdk.service.appmgr.bean.UpgradeRequest;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.zl.vo_.DemoCache;
import com.zl.vo_.NimApplication;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.Preferences;
import com.zl.vo_.config.preference.UserPreferences;
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.bean.UserInfoBean;
import com.zl.vo_.own.ui.account.bean.UserInfoData;
import com.zl.vo_.own.ui.account.view.MyCountDownTimer;
import com.zl.vo_.own.util.MobileNumberUtil;
import com.zl.vo_.own.util.SPUtils;
import com.zl.vo_.own.views.ClearEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/2.
 */

public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_bindPhone)
    LinearLayout ll_bindPhone;

    @BindView(R.id.reg_phone)
    ClearEditText reg_phone;
    @BindView(R.id.reg_smsCode)
    ClearEditText reg_smsCode;
    @BindView(R.id.reg_pwd)
    ClearEditText reg_pwd;
    @BindView(R.id.reg_confirm_pwd)
    ClearEditText reg_confirm_pwd;
    private String openId,nickname,sex,headimgurl;
    private AbortableFuture<LoginInfo> loginRequest;
    private MyCountDownTimer timer;
    @Override
    protected void initViews() {
        tv_title.setText("绑定手机");
        Intent intent = getIntent();
        if (intent!=null){
            openId = intent.getStringExtra("openid");
            nickname = intent.getStringExtra("nickname");
            sex = intent.getStringExtra("sex");
            headimgurl = intent.getStringExtra("headimgurl");
        }
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_bind_phone);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.reg_submit,R.id.reg_tv_smsCode})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.reg_submit:
                String phoneNum = reg_phone.getText().toString();
                String confirmCode = reg_smsCode.getText().toString();
                String pwd = reg_pwd.getText().toString();
                String confirmPwd = reg_confirm_pwd.getText().toString();
                int visibility = ll_bindPhone.getVisibility();
                if (visibility == View.VISIBLE){
                    if (checkParamsIsLegal(phoneNum,confirmCode,pwd,confirmPwd)){
                        wxRegister(phoneNum,confirmCode,pwd,confirmPwd);
                    }
                }else{
                    if (checkBindParamsIsLegal(phoneNum,confirmCode)){
                        wxBind(phoneNum,confirmCode);
                    }
                }
                break;
            case R.id.reg_tv_smsCode:
                String phone = reg_phone.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断手机号码是不是合法
                if (!MobileNumberUtil.isMobileNO(phone)){
                    Toast.makeText(this, "对不起手机号码格式有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                timer = new MyCountDownTimer(60000, 1000, (Button) view);
                timer.start();
                getConfirmCode(phone);
                break;
        }
    }
    //微信注册
    private void wxRegister(String phone, String confirmCode, String pwd, String confirmPwd) {
        Map<String,String> params = new HashMap<>();
        params.put("mobile",phone);
        params.put("captcha",confirmCode);
        params.put("password",pwd);
        params.put("openid",openId);
        params.put("headimgurl",headimgurl);
        params.put("sex",sex);
        params.put("nickname",nickname);
        ApiAccount.wxRegister(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    if (code.equals(ApiConstant.SUCCESS_CODE)){
                        String token = jsonObject.getJSONObject("data").getString("token");
                        SPUtils.put(BindPhoneActivity.this,"cloudToken",token);
                        getUserInfo();
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
    //微信绑定
    private void wxBind(String phoneNum, String confirmCode) {
        Map<String,String> params = new HashMap<>();
        params.put("mobile",phoneNum);
        params.put("captcha",confirmCode);
        params.put("openid",openId);
        params.put("headimgurl",headimgurl);
        params.put("sex",sex);
        params.put("nickname",nickname);
        ApiAccount.wxBind(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    if (code.equals(ApiConstant.SUCCESS_CODE)){
                        String token = jsonObject.getJSONObject("data").getString("token");
                        SPUtils.put(BindPhoneActivity.this,"cloudToken",token);
                        getUserInfo();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {

            }
        });
    }
    //获取个人信息
    private void getUserInfo() {
        ApiAccount.getUserInfo(this, new OnRequestDataListener() {
            @Override
            public void requestSuccess( String data) {
                Log.i("tag",data);
                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                String code = userInfoBean.getCode();
                if (ApiConstant.SUCCESS_CODE.equals(code)){
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

                    int by_mobile = Integer.parseInt(userInfoData.getBy_mobile());
                    int by_qrcode = Integer.parseInt(userInfoData.getBy_qrcode());
                    int by_vocode = Integer.parseInt(userInfoData.getBy_vo_code());
                    int by_card = Integer.parseInt(userInfoData.getBy_card());
                    int by_group_caht = Integer.parseInt(userInfoData.getBy_group_chat());


                    SPUtils.put(BindPhoneActivity.this,"token",token);
                    SPUtils.put(BindPhoneActivity.this,"avatar",avatar);
                    SPUtils.put(BindPhoneActivity.this,"vo_code",vo_code);
                    SPUtils.put(BindPhoneActivity.this,"nickName",nickName);
                    SPUtils.put(BindPhoneActivity.this,"sex",sex);
                    SPUtils.put(BindPhoneActivity.this,"country",country);
                    SPUtils.put(BindPhoneActivity.this,"city",city);
                    SPUtils.put(BindPhoneActivity.this,"province",province);
                    SPUtils.put(BindPhoneActivity.this,"id",id);
                    SPUtils.put(BindPhoneActivity.this,"vo_code_can",vo_code_can);
                    SPUtils.put(BindPhoneActivity.this,"signature",signature);
                    SPUtils.put(BindPhoneActivity.this,"vo_code_set",vo_code_set);
                    SPUtils.put(BindPhoneActivity.this,"by_mobile",by_mobile);
                    SPUtils.put(BindPhoneActivity.this,"by_vo_code",by_vocode);
                    SPUtils.put(BindPhoneActivity.this,"by_qrcode",by_qrcode);
                    SPUtils.put(BindPhoneActivity.this,"by_card",by_card);
                    SPUtils.put(BindPhoneActivity.this,"by_group_chat",by_group_caht);
                    //云信登录
                    cloudLogin(token,vo_code);
                }
            }
            @Override
            public void requestFailure(int code, String msg) {
                Log.i("tag",msg);
            }
        });
    }
    private void cloudLogin(final String token,final String uuid) {
        loginRequest = NimUIKit.login(new LoginInfo(uuid, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {
                onLoginDone();
                DemoCache.setAccount(uuid);
                saveLoginInfo(uuid, token);
                // 初始化消息提醒配置
                initNotificationConfig();
                // 进入主界面
                startActivity(new Intent(BindPhoneActivity.this, com.zl.vo_.main.activity.MainActivity.class));
                finish();
            }
            @Override
            public void onFailed(int code) {
                onLoginDone();
                if (code == 302 || code == 404) {
                    Toast.makeText(BindPhoneActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BindPhoneActivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable throwable) {
                Toast.makeText(BindPhoneActivity.this, R.string.login_exception, Toast.LENGTH_LONG).show();
                onLoginDone();
            }
        });
    }
    private void onLoginDone() {
        loginRequest = null;
        DialogMaker.dismissProgressDialog();
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
    //检查注册的参数是不是空
    private boolean checkParamsIsLegal(String phone, String confirmCode, String pwd,String confirmPwd) {
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
        if (TextUtils.isEmpty(confirmPwd)){
            Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!pwd.equals(confirmPwd)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(confirmPwd)){
            Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //检查注册的参数是不是空
    private boolean checkBindParamsIsLegal(String phone, String confirmCode) {
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
        return true;
    }
    private void getConfirmCode(String phone) {
        Map<String,String> params = new HashMap<>();
        params.put("mobile",phone);
        params.put("tag","4");
        ApiAccount.getConfirmCode(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    int is_register = jsonObject.getJSONObject("data").getInt("is_register");
                    Toast.makeText(BindPhoneActivity.this, message, Toast.LENGTH_SHORT).show();
                    if (is_register == 0){
                        ll_bindPhone.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {

            }
        });
    }
}
