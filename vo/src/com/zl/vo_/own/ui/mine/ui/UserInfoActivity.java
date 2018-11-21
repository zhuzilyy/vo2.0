package com.zl.vo_.own.ui.mine.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zl.vo_.R;
import com.zl.vo_.own.api.ReqResCodeForVo;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.util.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/8/27.
 */

public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_name)
    TextView tv_nickName;
    @BindView(R.id.tv_voId)
    TextView tv_voNum;
    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;
    private MyReceiver myReceiver;
    @Override
    protected void initViews() {
        tv_title.setText("个人信息");
        myReceiver = new MyReceiver();
        //修改vo号的广播
        IntentFilter changeVoCodeFilter = new IntentFilter();
        changeVoCodeFilter.addAction("com.action.changeVoCode");
        registerReceiver(myReceiver,changeVoCodeFilter);
    }
    @Override
    protected void initData() {
        setDefaultValue();
    }
    //设置初始化的数据
    private void setDefaultValue() {
        String avatar = (String) SPUtils.get(this, "avatar", "");
        String vo_code = (String) SPUtils.get(this, "vo_code", "");
        String vo_code_can = (String) SPUtils.get(this,"vo_code_can","");
        String vo_code_set = (String) SPUtils.get(this,"vo_code_set","");

        String nickName = (String) SPUtils.get(this, "nickName", "");
        Glide.with(this).load(avatar).into(iv_avatar);
        tv_nickName.setText(nickName);

        if("0".equals(vo_code_can)){
            tv_voNum.setText(vo_code_set);
        }else {
            tv_voNum.setText(vo_code);
        }

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_useinfo);
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.rl_nickName,R.id.rl_voId,R.id.rl_qrcode,R.id.rl_avatar,R.id.rl_more})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                Intent intent = new Intent();
                intent.putExtra("nick_setOk",tv_nickName.getText().toString().trim());
                intent.putExtra("void_setOk",tv_voNum.getText().toString().trim());
                setResult(ReqResCodeForVo.USERINFO_VONAME_ID_RESULT_CODE,intent);

              //  Intent intent_void = new Intent();
              //  intent_void.putExtra("void_setOk",tv_voNum.getText().toString().trim());
              //  setResult(ReqResCodeForVo.USERINFO_VOID_RESULT_CODE,intent_void);
               finish();
                break;
            case R.id.rl_avatar:
                jumpActivity(UserInfoActivity.this,AvatarActivity.class);
                break;
            //修改昵称
            case R.id.rl_nickName:
                String nick_name = tv_nickName.getText().toString().trim();
                jumpActivity(UserInfoActivity.this,ChangeNickNameActivity.class,nick_name,102);
                break;
            //修改vo号
            case R.id.rl_voId:
                String voCode = tv_voNum.getText().toString().trim();
                jumpActivity(UserInfoActivity.this,ChangeVoIDActivity.class,voCode,103);
                break;
            //二维码
            case R.id.rl_qrcode:
                jumpActivity(UserInfoActivity.this,QrCodeActivity.class);
                break;
            //更多
            case R.id.rl_more:
                jumpActivity(UserInfoActivity.this,ChangeMoreActivity.class);
                break;
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver!=null){
            unregisterReceiver(myReceiver);
        }
    }
    class  MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.changeVoCode")){
                String voCode=intent.getStringExtra("voCode");
                tv_voNum.setText(voCode);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(ReqResCodeForVo.CHANGE_VONAME_RESULT_CODE == resultCode){
            tv_nickName.setText((String)SPUtils.get(UserInfoActivity.this,"nickName",""));
        }
        if(ReqResCodeForVo.CHANGE_VOID_RESULT_CODE == resultCode){
           String vocode =  data.getStringExtra("coVode");
           if(!TextUtils.isEmpty(vocode)){
               tv_voNum.setText(vocode);
           }

        }
    }
}
