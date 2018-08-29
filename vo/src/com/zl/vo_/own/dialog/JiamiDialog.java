package com.zl.vo_.own.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zl.vo_.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/27.
 */

public class JiamiDialog extends Dialog {
    @BindView(R.id.iv_jiami)
    ImageView iv_jiami;
    public JiamiDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_jiami);
        ButterKnife.bind(this);
        Glide.with(getContext()).asGif().load(R.mipmap.jiami).into(iv_jiami);
    }
}
