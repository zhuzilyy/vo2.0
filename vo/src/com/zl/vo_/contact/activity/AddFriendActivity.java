package com.zl.vo_.contact.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.vo_.DemoCache;
import com.zl.vo_.R;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.SimpleCallback;
import com.netease.nim.uikit.api.wrapper.NimToolBarOptions;
import com.netease.nim.uikit.common.activity.ToolBarOptions;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.ui.dialog.EasyAlertDialogHelper;
import com.netease.nim.uikit.common.ui.widget.ClearableEditTextWithIcon;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.zl.vo_.own.util.WeiboDialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加好友页面
 * Created by huangjun on 2015/8/11.
 */
public class AddFriendActivity extends UI {

    private ClearableEditTextWithIcon searchEdit;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;

    public static final void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddFriendActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend_activity);
        ButterKnife.bind(this);
        ToolBarOptions options = new NimToolBarOptions();
        options.titleId = R.string.add_buddy;
        setToolBar(R.id.toolbar, options);

        findViews();
        initActionbar();
        initData();
    }

    private void initData() {
        tv_title.setText("添加好友");
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("搜索");
    }
    @OnClick({R.id.iv_back,R.id.tv_right})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                if (TextUtils.isEmpty(searchEdit.getText().toString())) {
                    Toast.makeText(AddFriendActivity.this, R.string.not_allow_empty, Toast.LENGTH_SHORT).show();
                } else if (searchEdit.getText().toString().equals(DemoCache.getAccount())) {
                    Toast.makeText(AddFriendActivity.this, R.string.add_friend_self_tip, Toast.LENGTH_SHORT).show();
                } else {
                    query();
                }
                break;
        }
    }
    private void findViews() {
        searchEdit = findView(R.id.search_friend_edit);
        searchEdit.setDeleteImage(R.drawable.nim_grey_delete_icon);
    }

    private void initActionbar() {
        TextView toolbarView = findView(R.id.action_bar_right_clickable_textview);
        toolbarView.setText(R.string.search);
        toolbarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(searchEdit.getText().toString())) {
                    Toast.makeText(AddFriendActivity.this, R.string.not_allow_empty, Toast.LENGTH_SHORT).show();
                } else if (searchEdit.getText().toString().equals(DemoCache.getAccount())) {
                    Toast.makeText(AddFriendActivity.this, R.string.add_friend_self_tip, Toast.LENGTH_SHORT).show();
                } else {
                    query();
                }
            }
        });
    }

    private void query() {
        final Dialog dialog = WeiboDialogUtils.createLoadingDialog(this, "正在搜索");
        dialog.show();
        //DialogMaker.showProgressDialog(this, null, false);
        final String account = searchEdit.getText().toString().toLowerCase();
        NimUIKit.getUserInfoProvider().getUserInfoAsync(account, new SimpleCallback<NimUserInfo>() {
            @Override
            public void onResult(boolean success, NimUserInfo result, int code) {
                //DialogMaker.dismissProgressDialog();
                dialog.dismiss();
                if (success) {
                    if (result == null) {
                        EasyAlertDialogHelper.showOneButtonDiolag(AddFriendActivity.this, R.string.user_not_exsit,
                                R.string.user_tips, R.string.ok, false, null);
                    } else {
                        UserProfileActivity.start(AddFriendActivity.this, account);
                    }
                } else if (code == 408) {
                    Toast.makeText(AddFriendActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
                } else if (code == ResponseCode.RES_EXCEPTION) {
                    Toast.makeText(AddFriendActivity.this, "on exception", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddFriendActivity.this, "on failed:" + code, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
