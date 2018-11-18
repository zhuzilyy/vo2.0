package com.zl.vo_.own.ui.mine.ui;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.api.ApiMine;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.util.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/8/27.
 */

public class QrCodeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv__head)
    CircleImageView iv__head;
    @BindView(R.id.iv_sex)
    ImageView iv_sex;
    @BindView(R.id.code_image)
    ImageView code_image;
    @BindView(R.id.ewm_nick)
    TextView et_nick;
    @BindView(R.id.ewm_address)
    TextView et_address;

    @Override
    protected void initViews() {
        tv_title.setText("我的二维码");
        //获取默认值
        String avatar= (String) SPUtils.get(this,"avatar","");
        String sex= (String) SPUtils.get(this,"sex","");
        String nick = (String )SPUtils.get(this,"nickName","");
        String country = (String)SPUtils.get(this,"country","");
        String city = (String)SPUtils.get(this,"city","");
        //设置个人信心
        //头像
        Glide.with(this).load(avatar).into(iv__head);
        //昵称
        if(!TextUtils.isEmpty(nick)){
            et_nick.setText(nick);
        }
        //地址
        if(!TextUtils.isEmpty(country)){
            if(!TextUtils.isEmpty(city)){
                et_address.setText(city+"-"+country);
            }
        }
        if (sex.equals("1")){
            iv_sex.setImageResource(R.mipmap.nan);
        }else if (sex.equals("2")){
            iv_sex.setImageResource(R.mipmap.nv);
        }
    }
    @Override
    protected void initData() {
        ApiMine.getQrCode(this, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    JSONObject jsonData = jsonObject.getJSONObject("data");
                    if (code.equals(ApiConstant.SUCCESS_CODE)){
                        String qrcode_url = jsonData.getString("qrcode_url");
                        if (!TextUtils.isEmpty(qrcode_url)){
                            Glide.with(QrCodeActivity.this).load(qrcode_url).into(code_image);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {
                Toast.makeText(QrCodeActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_qr_code);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
