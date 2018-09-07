package com.zl.vo_.own.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/6.
 */

public class SearchFriendsActivity extends BaseActivity {
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tv_search_name)
    TextView tv_search_name;
    @BindView(R.id.ll_search_noUser)
    LinearLayout ll_search_noUser;
    @BindView(R.id.ll_search_result)
    LinearLayout ll_search_result;
    @Override
    protected void initViews() {

    }
    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_search_friend);
    }

    @Override
    protected void initListener() {
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
    }

    @Override
    protected void setStatusBarColor() {

    }
}
