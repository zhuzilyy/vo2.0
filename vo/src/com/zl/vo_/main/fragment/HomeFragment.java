package com.zl.vo_.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.business.contact.selector.activity.ContactSelectActivity;
import com.netease.nim.uikit.business.team.helper.TeamHelper;
import com.netease.nim.uikit.common.fragment.TFragment;
import com.netease.nim.uikit.common.ui.drop.DropCover;
import com.netease.nim.uikit.common.ui.drop.DropManager;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.zl.vo_.R;
import com.zl.vo_.common.ui.viewpager.FadeInOutPageTransformer;
import com.zl.vo_.common.ui.viewpager.PagerSlidingTabStrip;
import com.zl.vo_.contact.activity.AddFriendActivity;
import com.zl.vo_.main.adapter.MainTabPagerAdapter;
import com.zl.vo_.main.helper.SystemMessageUnreadManager;
import com.zl.vo_.main.model.MainTab;
import com.zl.vo_.main.reminder.ReminderItem;
import com.zl.vo_.main.reminder.ReminderManager;
import com.zl.vo_.own.ui.SearchFriendsActivity;
import com.zl.vo_.own.views.DetailsTypePopupWindow;
import com.zl.vo_.own.views.NoScrollViewPager;
import com.zl.vo_.team.TeamCreateHelper;
import com.zl.vo_.team.activity.AdvancedTeamSearchActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 云信主界面（导航页）
 */
public class HomeFragment extends TFragment implements OnPageChangeListener, ReminderManager.UnreadNumChangedCallback {
    private TextView tv_title;
    private PagerSlidingTabStrip tabs;
    private NoScrollViewPager pager;
    private int scrollState;
    private MainTabPagerAdapter adapter;
    private View rootView;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    private static final int REQUEST_CODE_NORMAL = 1;
    private static final int REQUEST_CODE_ADVANCED = 2;
    public HomeFragment() {
        setContainerId(R.id.welcome_container);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolBar(R.id.toolbar, R.string.app_name, R.drawable.actionbar_dark_logo);
        setTitle(R.string.app_name);
        findViews();
        setupPager();
        setupTabs();
        registerMsgUnreadInfoObserver(true);
        registerSystemMessageObservers(true);
        requestSystemMessageUnreadCount();
        initUnreadCover();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // TO TABS
        tabs.onPageScrolled(position, positionOffset, positionOffsetPixels);
        // TO ADAPTER
        adapter.onPageScrolled(position);
    }
    @Override
    public void onPageSelected(int position) {
        tabs.onPageSelected(position);
        selectPage(position);
        enableMsgNotification(false);
        switch (position){
            case 0:
                tv_title.setText("消息");
                break;
            case 1:
                tv_title.setText("通讯录");
                break;
            case 3:
                tv_title.setText("发现");
                break;
            case 4:
                tv_title.setText("我的");
                break;
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {
        // TO TABS
        tabs.onPageScrollStateChanged(state);

        scrollState = state;

        selectPage(pager.getCurrentItem());
    }
    private void selectPage(int page) {
        // TO PAGE
        if (scrollState == ViewPager.SCROLL_STATE_IDLE) {

            adapter.onPageSelected(pager.getCurrentItem());
        }
    }

    public void switchTab(int tabIndex, String params) {
        pager.setCurrentItem(tabIndex);
    }

    /**
     * 查找页面控件
     */
    private void findViews() {
        tabs = findView(R.id.tabs);
        pager = findView(R.id.main_tab_pager);
        tv_title=findView(R.id.tv_title);
        tv_title.setText("消息");
    }

    @Override
    public void onResume() {
        super.onResume();
        enableMsgNotification(false);
        //quitOtherActivities();
        tv_title.setText("消息");
    }
    @Override
    public void onPause() {
        super.onPause();
        enableMsgNotification(true);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        registerMsgUnreadInfoObserver(false);
        registerSystemMessageObservers(false);
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean onClick(View v) {
        return true;
    }

    /**
     * 设置viewPager
     */
    private void setupPager() {
        // CACHE COUNT
        adapter = new MainTabPagerAdapter(getFragmentManager(), getActivity(), pager);
        pager.setOffscreenPageLimit(adapter.getCacheCount());
        // page swtich animation
        pager.setPageTransformer(true, new FadeInOutPageTransformer());
        // ADAPTER
        pager.setAdapter(adapter);
        // TAKE OVER CHANGE
        pager.setOnPageChangeListener(this);
    }

    /**
     * 设置tab条目
     */
    private void setupTabs() {

        tabs.setOnCustomTabListener(new PagerSlidingTabStrip.OnCustomTabListener() {
            @Override
            public int getTabLayoutResId(int position) {
                return R.layout.tab_layout_main;
            }

            @Override
            public boolean screenAdaptation() {
                return true;
            }
        });
        tabs.setViewPager(pager);
        tabs.setOnTabClickListener(adapter);
        tabs.setOnTabDoubleTapListener(adapter);
    }

    private void enableMsgNotification(boolean enable) {
        boolean msg = (pager.getCurrentItem() != MainTab.RECENT_CONTACTS.tabIndex);
        if (enable | msg) {
            /**
             * 设置最近联系人的消息为已读
             *
             * @param account,    聊天对象帐号，或者以下两个值：
             *                    {@link #MSG_CHATTING_ACCOUNT_ALL} 目前没有与任何人对话，但能看到消息提醒（比如在消息列表界面），不需要在状态栏做消息通知
             *                    {@link #MSG_CHATTING_ACCOUNT_NONE} 目前没有与任何人对话，需要状态栏消息通知
             */
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
        } else {
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_ALL, SessionTypeEnum.None);
        }
    }

    /**
     * 注册未读消息数量观察者
     */
    private void registerMsgUnreadInfoObserver(boolean register) {
        if (register) {
            ReminderManager.getInstance().registerUnreadNumChangedCallback(this);
        } else {
            ReminderManager.getInstance().unregisterUnreadNumChangedCallback(this);
        }
    }

    /**
     * 未读消息数量观察者实现
     */
    @Override
    public void onUnreadNumChanged(ReminderItem item) {
        MainTab tab = MainTab.fromReminderId(item.getId());
        if (tab != null) {
            tabs.updateTab(tab.tabIndex, item);
        }
    }

    /**
     * 注册/注销系统消息未读数变化
     *
     * @param register
     */
    private void registerSystemMessageObservers(boolean register) {
        NIMClient.getService(SystemMessageObserver.class).observeUnreadCountChange(sysMsgUnreadCountChangedObserver,
                register);
    }

    private Observer<Integer> sysMsgUnreadCountChangedObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer unreadCount) {
            SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(unreadCount);
            ReminderManager.getInstance().updateContactUnreadNum(unreadCount);
        }
    };

    /**
     * 查询系统消息未读数
     */
    private void requestSystemMessageUnreadCount() {
        int unread = NIMClient.getService(SystemMessageService.class).querySystemMessageUnreadCountBlock();
        SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(unread);
        ReminderManager.getInstance().updateContactUnreadNum(unread);
    }

    /**
     * 初始化未读红点动画
     */
    private void initUnreadCover() {
        DropManager.getInstance().init(getContext(), (DropCover) findView(R.id.unread_cover),
                new DropCover.IDropCompletedListener() {
                    @Override
                    public void onCompleted(Object id, boolean explosive) {
                        if (id == null || !explosive) {
                            return;
                        }

                        if (id instanceof RecentContact) {
                            RecentContact r = (RecentContact) id;
                            NIMClient.getService(MsgService.class).clearUnreadCount(r.getContactId(), r.getSessionType());
                            LogUtil.i("HomeFragment", "clearUnreadCount, sessionId=" + r.getContactId());
                        } else if (id instanceof String) {
                            if (((String) id).contentEquals("0")) {
                                NIMClient.getService(MsgService.class).clearAllUnreadCount();
                                LogUtil.i("HomeFragment", "clearAllUnreadCount");
                            } else if (((String) id).contentEquals("1")) {
                                NIMClient.getService(SystemMessageService.class).resetSystemMessageUnreadCount();
                                LogUtil.i("HomeFragment", "clearAllSystemUnreadCount");
                            }
                        }
                    }
                });
    }
    //添加的部分 点击事件
    @OnClick({R.id.iv_add,R.id.iv_search})
    public void click(View view){
        switch(view.getId()){
            case R.id.iv_add:
                showAddPw(iv_add);
                break;
            case R.id.iv_search:
                Intent intent=new Intent(getActivity(), SearchFriendsActivity.class);
                startActivity(intent);
                break;
        }
    }

    //显示右上角的添加按钮
    private void showAddPw(View view) {
        DetailsTypePopupWindow typePopupWindow = new DetailsTypePopupWindow(getActivity(), view, "", "");
        typePopupWindow.setmItemsOnClick(new DetailsTypePopupWindow.ItemsOnClick() {
            @Override
            public void itemsOnClick(int position) {
                switch (position) {
                    case 0:
                        AdvancedTeamSearchActivity.start(getActivity());
                        break;
                    //添加新的好友
                    case 1:
                        AddFriendActivity.start(getActivity());
                        break;
                    //扫一扫
                    case 2:
                        ContactSelectActivity.Option option = TeamHelper.getCreateContactSelectOption(null, 50);
                        NimUIKit.startContactSelector(getActivity(), option, REQUEST_CODE_NORMAL);
                        break;
                    //帮助及反馈
                    case 3:
                      /*  Intent intent=new Intent(getActivity(),WebViweActivity.class);
                        intent.putExtra("url", ApiConstant.FUNCTION_INTRODUCE);
                        intent.putExtra("title","功能介绍");
                        startActivity(intent);*/
                        ContactSelectActivity.Option advancedOption = TeamHelper.getCreateContactSelectOption(null, 50);
                        NimUIKit.startContactSelector(getActivity(), advancedOption, REQUEST_CODE_ADVANCED);
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_NORMAL) {
                final ArrayList<String> selected = data.getStringArrayListExtra(ContactSelectActivity.RESULT_DATA);
                if (selected != null && !selected.isEmpty()) {
                    TeamCreateHelper.createNormalTeam(getActivity(), selected, false, null);
                } else {
                    Toast.makeText(getActivity(), "请选择至少一个联系人！", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == REQUEST_CODE_ADVANCED) {
                final ArrayList<String> selected = data.getStringArrayListExtra(ContactSelectActivity.RESULT_DATA);
                TeamCreateHelper.createAdvancedTeam(getActivity(), selected);
            }
        }
    }
}