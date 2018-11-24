package com.zl.vo_.own.ui.mine.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.netease.nim.uikit.common.ui.widget.SwitchButton;
import com.netease.nimlib.sdk.friend.constant.VerifyType;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.UserPreferences;
import com.zl.vo_.contact.activity.UserProfileActivity;
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.LoginActivity;
import com.zl.vo_.own.ui.account.bean.UserInfoBean;
import com.zl.vo_.own.ui.account.bean.UserInfoData;
import com.zl.vo_.own.util.SPUtils;
import com.zl.vo_.own.views.EaseSwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class CurrencySettingActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.switch_Verification)
    SwitchButton switch_Verification;
    @BindView(R.id.switch_Recommended_address_friends)
    SwitchButton switch_Recommended_address_friends;
    private boolean b_verification;


    //添加我的方式
    @BindView(R.id.privacy_addmeway_re)
    RelativeLayout privacy_addmeway_re;
    //通讯录黑名单
    @BindView(R.id.privacy_blackList_re)
    RelativeLayout privacy_blackList_re;

    @Override
    protected void initViews() {
        title.setText("通用");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        //加我为好友时需要验证
        b_verification = UserPreferences.getKeyVerificationFriend();
        switch_Verification.setCheck(b_verification);








    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.layout_currencysetting_activity);
    }

    @Override
    protected void initListener() {

        switch_Verification.setOnChangedListener(new SwitchButton.OnChangedListener() {
            @Override
            public void OnChanged(View v, boolean checkState) {
                friendAsme_Verification();
            }
        });

        //添加我的方式
        privacy_addmeway_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CurrencySettingActivity.this, AddMeAsFriendActivity.class));
            }
        });

        //通讯录黑名单
        privacy_blackList_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CurrencySettingActivity.this, "9009", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void setStatusBarColor() {

    }

    //加我为好友时需要验证
    public void friendAsme_Verification() {


        Map<String, String> params = new HashMap<>();
        ApiAccount.FriendAsMe_Verification(CurrencySettingActivity.this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag", "11" + data);
                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                String code = userInfoBean.getCode();
                if (code.equals(ApiConstant.SUCCESS_CODE)) {
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
                    SPUtils.put(CurrencySettingActivity.this, "token", token);
                    SPUtils.put(CurrencySettingActivity.this, "avatar", avatar);
                    SPUtils.put(CurrencySettingActivity.this, "vo_code", vo_code);
                    SPUtils.put(CurrencySettingActivity.this, "nickName", nickName);
                    SPUtils.put(CurrencySettingActivity.this, "sex", sex);
                    SPUtils.put(CurrencySettingActivity.this, "country", country);
                    SPUtils.put(CurrencySettingActivity.this, "city", city);
                    SPUtils.put(CurrencySettingActivity.this, "province", province);
                    SPUtils.put(CurrencySettingActivity.this, "id", id);
                    SPUtils.put(CurrencySettingActivity.this, "vo_code_can", vo_code_can);
                    SPUtils.put(CurrencySettingActivity.this, "signature", signature);
                    SPUtils.put(CurrencySettingActivity.this, "vo_code_set", vo_code_set);


                    UserPreferences.setKeyVerificationFriend(!b_verification);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {

                switch_Verification.setCheck(b_verification);

            }
        });
    }

}
