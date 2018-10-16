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
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.api.ApiMine;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.bean.UserInfoBean;
import com.zl.vo_.own.ui.account.bean.UserInfoData;
import com.zl.vo_.own.util.SPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/31.
 */

public class PersonSignActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.sign_ed)
    EditText sign_ed;
    @Override
    protected void initViews() {
        tv_title.setText("个性签名");
        tv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

       String signature = (String) SPUtils.get(PersonSignActivity.this,"signature","");
       if(!TextUtils.isEmpty(signature)){
           sign_ed.setText(signature);
       }

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_person_sign);
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
                //点击保存签名
                String signature = sign_ed.getText().toString();
                saveSignature(signature);
                break;
        }
    }

    /*
     * 保存签名
     * @param signature
     */
    public void saveSignature(String signature){
        Map<String,String> params = new HashMap<String,String>();
        params.put("signature",signature);
        ApiMine.setSignature(PersonSignActivity.this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                String code = userInfoBean.getCode();
                String message = userInfoBean.getMessage();
                Toast.makeText(PersonSignActivity.this, message, Toast.LENGTH_SHORT).show();
                UserInfoData userInfoData = userInfoBean.getData();
                if (code.equals(ApiConstant.SUCCESS_CODE)){
                    String token = userInfoData.getToken();
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
                    SPUtils.put(PersonSignActivity.this,"token",token);
                    SPUtils.put(PersonSignActivity.this,"avatar",avatar);
                    SPUtils.put(PersonSignActivity.this,"vo_code",vo_code);
                    SPUtils.put(PersonSignActivity.this,"nickName",nickName);
                    SPUtils.put(PersonSignActivity.this,"sex",sex);
                    SPUtils.put(PersonSignActivity.this,"country",country);
                    SPUtils.put(PersonSignActivity.this,"city",city);
                    SPUtils.put(PersonSignActivity.this,"province",province);
                    SPUtils.put(PersonSignActivity.this,"id",id);
                    SPUtils.put(PersonSignActivity.this,"vo_code_can",vo_code_can);
                    SPUtils.put(PersonSignActivity.this,"signature",signature);
                    Intent intent = new Intent();
                    intent.putExtra("signature",signature);
                    setResult(201,intent);
                    finish();
                }

            }

            @Override
            public void requestFailure(int code, String msg) {
                Log.i("tag",msg);
                Toast.makeText(PersonSignActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
