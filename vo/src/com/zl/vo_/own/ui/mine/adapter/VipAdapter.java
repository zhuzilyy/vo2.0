package com.zl.vo_.own.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zl.vo_.R;

/**
 * Created by Administrator on 2018/9/3.
 */

public class VipAdapter extends BaseAdapter {
    private Context context;
    public VipAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.item_vip,null);
        return view;
    }
}
