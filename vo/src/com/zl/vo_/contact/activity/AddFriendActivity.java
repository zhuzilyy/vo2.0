package com.zl.vo_.contact.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.test.mock.MockApplication;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.dialog.CustomDialog;
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
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.api.ApiFriends;
import com.zl.vo_.own.dialog.CustomerDialog;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.util.InternetUtil;
import com.zl.vo_.own.util.SPUtils;
import com.zl.vo_.own.util.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tv_search_name)
    TextView tv_search_name;
    @BindView(R.id.ll_search_noUser)
    LinearLayout ll_search_noUser;
    @BindView(R.id.ll_search_result)
    LinearLayout ll_search_result;

    private ImageView back_arrow;
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
        back_arrow=findView(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_search.getText().toString();
                if(!TextUtils.isEmpty(s)){
                    ll_search_result.setVisibility(View.VISIBLE);
                    tv_search_name.setText(s);
                    ll_search_noUser.setVisibility(View.GONE);
                }else {
                    ll_search_result.setVisibility(View.GONE);
                }
            }
        });
        ll_search_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (InternetUtil.hasInternet()){
                   //设置不能搜索自己
                   if (tv_search_name.getText().toString().equals(DemoCache.getAccount())){
                       Toast.makeText(AddFriendActivity.this, R.string.add_friend_self_tip, Toast.LENGTH_SHORT).show();
                       return;
                   }
                   String friend = tv_search_name.getText().toString().trim();
                   searchFriend(friend);
               }else{
                   Toast.makeText(AddFriendActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
               }
            }
        });

    }
    //搜索好友
    private void searchFriend(final String name) {
        Map<String,String> params = new HashMap<>();
        params.put("keyword",name);
        String cloudToken =(String)SPUtils.get(AddFriendActivity.this,"cloudToken","");
        ApiFriends.searchFriend(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    if (code.equals(ApiConstant.SUCCESS_CODE)){
                        JSONObject jsonData = jsonObject.getJSONObject("data");
                        String vo_code = jsonData.getString("vo_code");
                        UserProfileActivity.start(AddFriendActivity.this, vo_code);
                    }else if (code.equals("404001")){
                            showNouserDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {

            }
        });

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
        final String account = tv_search_name.getText().toString().toLowerCase();
        NimUIKit.getUserInfoProvider().getUserInfoAsync(account, new SimpleCallback<NimUserInfo>() {
            @Override
            public void onResult(boolean success, NimUserInfo result, int code) {
                //DialogMaker.dismissProgressDialog();
                dialog.dismiss();
                if (success) {
                    if (result == null) {
                       /* EasyAlertDialogHelper.showOneButtonDiolag(AddFriendActivity.this, R.string.user_not_exsit,
                                R.string.user_tips, R.string.ok, false, null);*/
                       showNouserDialog();
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
    //设置没有用户
    private void showNouserDialog() {
        final CustomerDialog dialog=new CustomerDialog(AddFriendActivity.this);
        dialog.setDialogTitle("用户不存在");
        dialog.setDialogConfirmText("确定");
        dialog.setDialogMessage("请检查输入的账号是否正确");
        dialog.setYesOnclickListener(new CustomerDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                dialog.dismiss();
            }
        });
        dialog.setNoOnclickListener(new CustomerDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
