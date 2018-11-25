package com.zl.vo_.own.ui.friend.ui;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zl.vo_.DemoCache;
import com.zl.vo_.R;
import com.zl.vo_.contact.activity.AddFriendActivity;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.api.ApiFriends;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.beans.CityBean;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.friend.adapter.CityAdapter;
import com.zl.vo_.own.ui.friend.bean.BookUserInfoBean;
import com.zl.vo_.own.util.WeiboDialogUtils;
import com.zl.vo_.own.util.WhiteBgBitmapUtil;
import com.zl.vo_.own.views.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //****************字母索引*************
    private static final String INDEX_STRING_TOP = "↑";
    private RecyclerView mRv;
    private CityAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas = new ArrayList<>();

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;

    //****************字母索引*************



    private PopupWindow pw_share;
    private View view_share;
    private Dialog dialog;

    @Override
    protected void initViews() {
        tv_title.setText("通讯录好友");
        tv_right.setVisibility(View.VISIBLE);
        view_share= LayoutInflater.from(AddressListFriendActivity.this).inflate(R.layout.pw_share,null);


        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mAdapter = new CityAdapter(this, mDatas,AddressListFriendActivity.this);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(AddressListFriendActivity.this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        dialog= WeiboDialogUtils.createLoadingDialog(this, "正在加载");
    }
    @Override
    protected void initData() {
        dialog.show();
        Map<String,String> params = new HashMap<>();
        String str = DemoCache.getPhone_contacts();
        params.put("book_str", str);
        ApiFriends.getAddressFriends(AddressListFriendActivity.this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                dialog.dismiss();
                Log.i("tag","11"+data);
                Gson gson = new Gson();

                BookUserInfoBean bookUserInfoBean = gson.fromJson(data, BookUserInfoBean.class);
                String code = bookUserInfoBean.getCode();
                if(ApiConstant.SUCCESS_CODE.equals(code)){

                    List<CityBean> cityBeans  = bookUserInfoBean.getData();
                    if(cityBeans!= null){
                        initDatas(cityBeans);
                    }


                }


            }

            @Override
            public void requestFailure(int code, String msg) {
                Log.i("yuiop",msg);
                dialog.dismiss();

            }
        });



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
    @OnClick({R.id.iv_back,R.id.wx_share_ll,R.id.tv_search_friend,R.id.tv_right})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.wx_share_ll:
                //分享微信链接
                wx_share();
                break;
            case R.id.tv_search_friend:
                jumpActivity(AddressListFriendActivity.this,AddFriendActivity.class);
                break;
            case R.id.tv_right:
                jumpActivity(AddressListFriendActivity.this,AddFriendActivity.class);
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





    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final List<CityBean> data) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();

                for (int i = 0; i < data.size(); i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data.get(i).getName());//设置城市名称
                    mDatas.add(cityBean);
                }
                mAdapter.setDatas(mDatas);
                mAdapter.notifyDataSetChanged();

                mIndexBar.setmSourceDatas(mDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mDatas);
            }
        }, 500);
    }


}
