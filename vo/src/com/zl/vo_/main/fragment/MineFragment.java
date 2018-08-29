package com.zl.vo_.main.fragment;

import android.os.Bundle;

public class MineFragment extends MainTabFragment {
    @Override
    protected void onInit() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       onCurrent();
    }
}
