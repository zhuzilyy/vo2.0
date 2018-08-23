package com.zl.vo_.own.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.fragment.FriendsFragment;
import com.zl.vo_.own.fragment.HelpFragment;
import com.zl.vo_.own.fragment.MessageFragment;
import com.zl.vo_.own.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/17.
 */
public class MainActivity extends BaseActivity{
    @BindView(R.id.iv_msg)
    ImageView iv_msg;
    @BindView(R.id.iv_friends)
    ImageView iv_friends;
    @BindView(R.id.iv_find)
    ImageView iv_find;
    @BindView(R.id.iv_mine)
    ImageView iv_mine;
    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.tv_friends)
    TextView tv_friends;
    @BindView(R.id.tv_find)
    TextView tv_find;
    @BindView(R.id.tv_mine)
    TextView tv_mine;
    private MessageFragment messageFragment;
    private FriendsFragment friendsFragment;
    private HelpFragment helpFragment;
    private MineFragment mineFragment;
    private Fragment currentFragment;
    private List<ImageView> imageViewList;
    private List<TextView> textViewList;
    @Override
    protected void initViews() {
        messageFragment=new MessageFragment();
        friendsFragment=new FriendsFragment();
        helpFragment=new HelpFragment();
        mineFragment=new MineFragment();
        switchBottomBar();
        initFragment();
    }
    private void switchBottomBar() {
        imageViewList=new ArrayList<>();
        textViewList=new ArrayList<>();
        imageViewList.add(iv_msg);
        imageViewList.add(iv_friends);
        imageViewList.add(iv_find);
        imageViewList.add(iv_mine);
        textViewList.add(tv_message);
        textViewList.add(tv_friends);
        textViewList.add(tv_find);
        textViewList.add(tv_mine);
        iv_msg.setSelected(true);
    }

    private void initFragment() {
        if (messageFragment==null){
            messageFragment=new MessageFragment();
        }
        if (!messageFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().add(R.id.main_switch,messageFragment).commit();
            currentFragment=messageFragment;
        }
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
    @OnClick({R.id.rl_msg,R.id.ll_frineds,R.id.rl_find,R.id.ll_mine})
    public void click(View view){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        switch (view.getId()){
            case R.id.rl_msg:
                changeSelectedState(0);
                if (messageFragment == null){
                    messageFragment=new MessageFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(),messageFragment);
                break;
            case R.id.ll_frineds:
                changeSelectedState(1);
                if (friendsFragment == null){
                    friendsFragment=new FriendsFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(),friendsFragment);
                break;
            case R.id.rl_find:
                changeSelectedState(2);
                if (helpFragment == null){
                    helpFragment=new HelpFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(),helpFragment);
                break;
            case R.id.ll_mine:
                changeSelectedState(3);
                if (mineFragment == null){
                    mineFragment=new MineFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(),mineFragment);
                break;
        }
        transaction.commit();
    }

    /***
     * 显示隐藏Fragment
     *
     * @param ft
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction ft, Fragment fragment) {
        if (currentFragment == fragment) {
            return;
        }
        if (!fragment.isAdded()) {
            ft.hide(currentFragment).add(R.id.main_switch, fragment).commitAllowingStateLoss();

        } else {
            ft.hide(currentFragment).show(fragment).commitAllowingStateLoss();

        }
        currentFragment = fragment;
    }
    public void changeSelectedState(int poistion){
        for (int i = 0; i <imageViewList.size() ; i++) {
            if (i==poistion){
                imageViewList.get(i).setSelected(true);
                textViewList.get(i).setTextColor(Color.parseColor("#5544aa"));
            }else{
                imageViewList.get(i).setSelected(false);
                textViewList.get(i).setTextColor(Color.parseColor("#333333"));
            }
        }
    }

}
