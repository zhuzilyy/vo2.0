package com.zl.vo_.main.model;

import com.zl.vo_.R;
import com.zl.vo_.main.fragment.ChatRoomListFragment;
import com.zl.vo_.main.fragment.ContactListFragment;
import com.zl.vo_.main.fragment.FindFragment;
import com.zl.vo_.main.fragment.MainTabFragment;
import com.zl.vo_.main.fragment.MineFragment;
import com.zl.vo_.main.fragment.SessionListFragment;
import com.zl.vo_.main.reminder.ReminderId;

public enum MainTab {
    RECENT_CONTACTS(0, ReminderId.SESSION, SessionListFragment.class, R.string.main_tab_session, R.layout.session_list),
    CONTACT(1, ReminderId.CONTACT, ContactListFragment.class, R.string.main_tab_contact, R.layout.contacts_list),
    CHAT_ROOM(2, ReminderId.INVALID, ChatRoomListFragment.class, R.string.main_tab_empty, R.layout.chat_room_tab),
    FIND(3, ReminderId.INVALID, FindFragment.class, R.string.main_tab_find, R.layout.fragment_help),
    MINE(4, ReminderId.INVALID, MineFragment.class, R.string.main_tab_mine, R.layout.fragment_mine);

    public final int tabIndex;

    public final int reminderId;

    public final Class<? extends MainTabFragment> clazz;

    public final int resId;

    public final int fragmentId;

    public final int layoutId;

    MainTab(int index, int reminderId, Class<? extends MainTabFragment> clazz, int resId, int layoutId) {
        this.tabIndex = index;
        this.reminderId = reminderId;
        this.clazz = clazz;
        this.resId = resId;
        this.fragmentId = index;
        this.layoutId = layoutId;
    }

    public static final MainTab fromReminderId(int reminderId) {
        for (MainTab value : MainTab.values()) {
            if (value.reminderId == reminderId) {
                return value;
            }
        }

        return null;
    }

    public static final MainTab fromTabIndex(int tabIndex) {
        for (MainTab value : MainTab.values()) {
            if (value.tabIndex == tabIndex) {
                return value;
            }
        }

        return null;
    }
}
