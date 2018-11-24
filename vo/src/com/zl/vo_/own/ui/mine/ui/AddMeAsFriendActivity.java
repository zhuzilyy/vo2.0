package com.zl.vo_.own.ui.mine.ui;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.nim.uikit.common.ui.widget.SwitchButton;
import com.zl.vo_.R;
import com.zl.vo_.main.adapter.SettingsAdapter;
import com.zl.vo_.own.api.ApiSettings;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.friend.ui.AddressListFriendActivity;
import com.zl.vo_.own.util.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by tianou on 2018/11/22.
 */

public class AddMeAsFriendActivity extends BaseActivity implements SwitchButton.OnChangedListener {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView back;

    //通过手机添加
    @BindView(R.id.switch_phone)
    SwitchButton switch_phone;
    //通过vo
    @BindView(R.id.switch_void)
    SwitchButton switch_void;

    //群聊
    @BindView(R.id.switch_group)
    SwitchButton switch_group;
    //二维码
    @BindView(R.id.switch_qcode)
    SwitchButton switch_qcode;

    //名片
    @BindView(R.id.switch_idcard)
    SwitchButton switch_idcard;

    @Override
    protected void initViews() {

        title.setText("添加我的方式");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

        int by_mobile2 = (Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_mobile", 1);
        int by_vo_code2 = (Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_vo_code", 1);
        int by_qrcode2 = (Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_qrcode", 1);
        int by_card2 = (Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_card", 1);
        int by_group_chat2 = (Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_group_chat", 1);

        switch_phone.setOnChangedListener(this);
        switch_void.setOnChangedListener(this);
        switch_group.setOnChangedListener(this);
        switch_qcode.setOnChangedListener(this);
        switch_idcard.setOnChangedListener(this);



        //通过手机号
        if (by_mobile2 == 1) {
            switch_phone.setCheck(true);
        } else if (by_mobile2 == 0) {
            switch_phone.setCheck(false);
        }
        //通过void
        if (by_vo_code2 == 1) {
            switch_void.setCheck(true);
        } else if (by_vo_code2 == 0) {
            switch_void.setCheck(false);
        }

        //可以通过呢群聊
        if (by_group_chat2 == 1) {
            switch_group.setCheck(true);
        } else if (by_group_chat2 == 0) {
            switch_group.setCheck(false);
        }

        //可以同二维码
        if (by_qrcode2 == 1) {
            switch_qcode.setCheck(true);
        } else if (by_qrcode2 == 0) {
            switch_qcode.setCheck(false);
        }

        //可以通过名片
        if (by_card2 == 1) {
            switch_idcard.setCheck(true);
        } else if (by_card2 == 0) {
            switch_idcard.setCheck(false);
        }
    }

    @Override
    protected void getResLayout() {

        setContentView(R.layout.activity_addme_asfriend);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }

    @Override
    public void OnChanged(View v, boolean checkState) {
        switch (v.getId()){
            case R.id.switch_phone:

                changgeToggle("by_mobile");

                break;
            case R.id.switch_void:
                changgeToggle("by_vo_code");
                break;
            case R.id.switch_group:
                changgeToggle("by_group_chat");
                break;
            case R.id.switch_qcode:
                changgeToggle("by_qrcode");
                break;
            case R.id.switch_idcard:
                changgeToggle("by_card");
                break;
        }

    }


    public void changgeToggle(final String content){

        Map<String,String> params = new HashMap<>();
        params.put("field",content);
        ApiSettings.setWays(AddMeAsFriendActivity.this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    if("200000".equals(code)){

                        if("by_mobile".equals(content)){
                            SPUtils.updateUserData(AddMeAsFriendActivity.this,content,((Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_mobile", 1))== 1?0:1);
                        }else if("by_vo_code".equals(content)){
                            SPUtils.updateUserData(AddMeAsFriendActivity.this,content,((Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_vo_code", 1))== 1?0:1);
                        }else if("by_group_chat".equals(content)){
                            SPUtils.updateUserData(AddMeAsFriendActivity.this,content,((Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_group_chat", 1))== 1?0:1);
                        }else if("by_qrcode".equals(content)){
                            SPUtils.updateUserData(AddMeAsFriendActivity.this,content,((Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_qrcode", 1))== 1?0:1);
                        }else if("by_card".equals(content)){
                            SPUtils.updateUserData(AddMeAsFriendActivity.this,content,((Integer) SPUtils.get(AddMeAsFriendActivity.this, "by_card", 1))== 1?0:1);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void requestFailure(int code, String msg) {
                Log.i("fsofjosfji",msg);
            }
        });

    }


}
