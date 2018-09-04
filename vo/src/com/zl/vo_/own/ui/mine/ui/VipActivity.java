package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.ui.mine.adapter.VipAdapter;
import com.zl.vo_.own.util.WebviewUtil;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * Created by Administrator on 2018/8/29.
 */

public class VipActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.lv_vip)
    ListView lv_vip;
    private VipAdapter vipAdapter;
    @BindView(R.id.webView)
    WebView wv_webview;
    private WebSettings webSettings;
    @Override
    protected void initViews() {
        tv_title.setText("VO会员");
        vipAdapter=new VipAdapter(this);
        lv_vip.setAdapter(vipAdapter);
    }
    @Override
    protected void initData() {
        webSettings=wv_webview.getSettings();
        WebviewUtil.setWebview(wv_webview, webSettings);
        // 加载webview
        wv_webview.loadUrl(ApiConstant.VO_VIP_AGREEMENT);
    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_vip);
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.btn_submit})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                jumpActivity(VipActivity.this,PayMehtodActivity.class);
                break;
        }
    }
}
