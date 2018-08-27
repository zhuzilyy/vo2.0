package com.zl.vo_.own.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/17.
 */

public class HelpFragment extends BaseFragment{
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View view_help=inflater.inflate(R.layout.fragment_help,null);
        return view_help;
    }

    @Override
    protected void initViews() {
        tv_title.setText("发现");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
