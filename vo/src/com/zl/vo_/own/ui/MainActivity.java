package com.zl.vo_.own.ui;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.fragment.FriendsFragment;
import com.zl.vo_.own.fragment.HelpFragment;
import com.zl.vo_.own.fragment.MessageFragment;
import com.zl.vo_.own.fragment.MineFragment;
/**
 * Created by Administrator on 2018/7/17.
 */
public class MainActivity extends BaseActivity{
    BottomNavigationBar bar;
    private MessageFragment messageFragment;
    private FriendsFragment friendsFragment;
    private HelpFragment helpFragment;
    private MineFragment mineFragment;
    private Fragment currentFragment;
    @Override
    protected void initViews() {
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
       setContentView(R.layout.activity_own_main);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
}
