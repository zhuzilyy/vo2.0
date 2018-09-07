package com.zl.vo_.own.ui.friend.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.util.WhiteBgBitmapUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/6.
 */

public class AddressListFriendActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.tv_right)
    TextView tv_right;
    private PopupWindow pw_share;
    private View view_share;

    @Override
    protected void initViews() {
        tv_title.setText("通讯录好友");
        tv_right.setVisibility(View.VISIBLE);
        view_share= LayoutInflater.from(AddressListFriendActivity.this).inflate(R.layout.pw_share,null);
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_address_list_friend);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.wx_share_ll})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.wx_share_ll:
                //分享微信链接
                wx_share();
                break;
        }

    }

    private void wx_share() {
        pw_share = new PopupWindow(AddressListFriendActivity.this);
        pw_share.setContentView(view_share);
        pw_share.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pw_share.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pw_share.setTouchable(true);
        pw_share.setFocusable(true);
        pw_share.setBackgroundDrawable(new BitmapDrawable());
        pw_share.setAnimationStyle(R.style.AnimBottom);
        pw_share.showAtLocation(container, Gravity.BOTTOM, 0, 0);
        // 设置pw弹出时候的背景颜色的变化
        backgroundAlpha(0.5f);
        pw_share.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        view_share.findViewById(R.id.ll_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //微信好友
                shareFriends("0");
                pw_share.dismiss();
            }
        });

        view_share.findViewById(R.id.ll_friendcircle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //朋友圈
                shareFriends("1");
                pw_share.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }


    //分享到微信好友
    private void shareFriends(String type) {
        IWXAPI mWxApi;
        mWxApi = WXAPIFactory.createWXAPI(AddressListFriendActivity.this, ApiConstant.APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(ApiConstant.APP_ID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl =ApiConstant.DOWN_LOAD_URL;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title ="人生知己，我在VO等你！";
        msg.description ="手机摇一摇即可隐藏好友,聊天办公,高端人群都在用" ;
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.logo);
        Bitmap bitmap = WhiteBgBitmapUtil.changeColor(bmp);
        msg.setThumbImage(bitmap);
        //bitmap.recycle();
        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
        req.message = msg;
        /**
         * 判断是否是朋友圈
         */
        if("1".equals(type)){
            req.scene =  SendMessageToWX.Req.WXSceneTimeline;
        }else if("0".equals(type)){
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        // 调用api接口发送数据到微信
        mWxApi.sendReq(req);
    }
    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
