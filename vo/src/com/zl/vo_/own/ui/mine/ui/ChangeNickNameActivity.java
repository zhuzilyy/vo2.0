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
import com.zl.vo_.own.api.ReqResCodeForVo;
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

public class ChangeNickNameActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.et_nickName)
    EditText et_nickName;
    private String nick;
    private String nick_param;
    @Override
    protected void initViews() {
        tv_title.setText("更改昵称");
        tv_right.setVisibility(View.VISIBLE);
        nick_param = getIntent().getStringExtra("param");
        if(!TextUtils.isEmpty(nick_param)){
            et_nickName.setText(nick_param);
        }


    }

    @Override
    protected void initData() {

       nick = (String) SPUtils.get(ChangeNickNameActivity.this,"nickName","");
       if(TextUtils.isEmpty(nick)){
           et_nickName.setText(nick);
       }


    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_change_nickname);
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
                String nickStr = et_nickName.getText().toString().trim();
                if(!TextUtils.isEmpty(nickStr)){
                    changeNick(nickStr);
                }else {
                    Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 改变昵称
     * @param nickStr
     */
    public void changeNick(final String nickStr){
        Map<String,String> params = new HashMap<>();
        params.put("name",nickStr);
        ApiMine.setNick(ChangeNickNameActivity.this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                String code = userInfoBean.getCode();
                if (code.equals(ApiConstant.SUCCESS_CODE)){
                    UserInfoData userInfoData = userInfoBean.getData();
                    String token = userInfoData.getToken();
                    //String uuid = userInfoData.getUuid();
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
                    SPUtils.put(ChangeNickNameActivity.this,"token",token);
                    SPUtils.put(ChangeNickNameActivity.this,"avatar",avatar);
                    SPUtils.put(ChangeNickNameActivity.this,"vo_code",vo_code);
                    SPUtils.put(ChangeNickNameActivity.this,"nickName",nickName);
                    SPUtils.put(ChangeNickNameActivity.this,"sex",sex);
                    SPUtils.put(ChangeNickNameActivity.this,"country",country);
                    SPUtils.put(ChangeNickNameActivity.this,"city",city);
                    SPUtils.put(ChangeNickNameActivity.this,"province",province);
                    SPUtils.put(ChangeNickNameActivity.this,"id",id);
                    SPUtils.put(ChangeNickNameActivity.this,"vo_code_can",vo_code_can);
                    SPUtils.put(ChangeNickNameActivity.this,"signature",signature);

                    //发送到我的界面
                    Intent intent1 = new Intent();
                    intent1.setAction("nick_success");
                    sendBroadcast(intent1);

                    //ForResult
                    Intent intent = new Intent();
                    intent.putExtra("nick",nickStr);
                    setResult(ReqResCodeForVo.CHANGE_VONAME_RESULT_CODE,intent);

                    finish();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                Toast.makeText(ChangeNickNameActivity.this, "设置失败", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
