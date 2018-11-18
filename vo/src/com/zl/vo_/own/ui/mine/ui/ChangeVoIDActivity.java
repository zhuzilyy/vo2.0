package com.zl.vo_.own.ui.mine.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.api.ApiMine;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.LoginActivity;
import com.zl.vo_.own.ui.account.bean.UserInfoBean;
import com.zl.vo_.own.ui.account.bean.UserInfoData;
import com.zl.vo_.own.util.SPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/27.
 */

public class ChangeVoIDActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.et_voId)
    EditText et_voId;
    private String voCode;
    @Override
    protected void initViews() {
        tv_title.setText("更改VO号");
        tv_right.setVisibility(View.VISIBLE);
        //获取从上一个界面传递的vo号
        voCode = getIntent().getStringExtra("param");
        if(!TextUtils.isEmpty(voCode)){
            et_voId.setText(voCode);
        }
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_change_void);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.tv_right})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                String vo_code_can= (String) SPUtils.get(this,"vo_code_can","");
                String voId = et_voId.getText().toString();
                if (TextUtils.isEmpty(voId)){
                    Toast.makeText(this, "vo号不能位空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (vo_code_can.equals("0")){
                    Toast.makeText(this, "vo号只能修改一次", Toast.LENGTH_SHORT).show();
                    return;
                }
                changeVoId(voId);
                break;
        }
    }
    private void changeVoId(String voId) {
        Map<String,String> params = new HashMap<>();
        params.put("vo_code",voId);
        ApiMine.changeVoId(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                String code = userInfoBean.getCode();
                String message = userInfoBean.getMessage();
                Toast.makeText(ChangeVoIDActivity.this, message, Toast.LENGTH_SHORT).show();
                UserInfoData userInfoData = userInfoBean.getData();
                if (code.equals(ApiConstant.SUCCESS_CODE)){
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
                    SPUtils.put(ChangeVoIDActivity.this,"token",token);
                    SPUtils.put(ChangeVoIDActivity.this,"avatar",avatar);
                    SPUtils.put(ChangeVoIDActivity.this,"vo_code",vo_code);
                    SPUtils.put(ChangeVoIDActivity.this,"nickName",nickName);
                    SPUtils.put(ChangeVoIDActivity.this,"sex",sex);
                    SPUtils.put(ChangeVoIDActivity.this,"country",country);
                    SPUtils.put(ChangeVoIDActivity.this,"city",city);
                    SPUtils.put(ChangeVoIDActivity.this,"province",province);
                    SPUtils.put(ChangeVoIDActivity.this,"id",id);
                    SPUtils.put(ChangeVoIDActivity.this,"vo_code_can",vo_code_can);
                    SPUtils.put(ChangeVoIDActivity.this,"signature",signature);
                    SPUtils.put(ChangeVoIDActivity.this,"vo_code_set",vo_code_set);
                    Intent intent = new Intent();
                    intent.putExtra("voCode",vo_code);
                    intent.setAction("com.action.changeVoCode");
                    sendBroadcast(intent);
                    finish();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {
                Log.i("tag",msg);
                Toast.makeText(ChangeVoIDActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
