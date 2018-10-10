package com.zl.vo_.main.viewholder;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.rtskit.common.imageview.CircleImageView;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.zl.vo_.DemoCache;
import com.zl.vo_.R;
import com.zl.vo_.common.imageView.ImageLoader;
import com.zl.vo_.session.extension.VoCardAttachment;

public class MsgViewHolderVoCard extends MsgViewHolderBase {
    private VoCardAttachment attachment;
    //  头像
    private CircleImageView vo_avatar;
    // 名称
    private TextView vo_name;
    // id
    private TextView vo_id;

    public MsgViewHolderVoCard(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected int getContentResId() {
        return R.layout.vocard_msg_layout;
    }

    @Override
    protected void inflateContentView() {
        vo_avatar = (CircleImageView) view.findViewById(R.id.vocard_avatar);
        vo_name = (TextView) view.findViewById(R.id.vocard_nick);
        vo_id = (TextView) view.findViewById(R.id.vocard_id);

    }

    @Override
    protected void onItemClick() {
        super.onItemClick();
        Toast.makeText(context, "名片消息 yuan", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void bindContentView() {
        attachment = (VoCardAttachment) message.getAttachment();

        if (message.getDirect() == MsgDirectionEnum.Out) {
            int color = DemoCache.getContext().getResources().getColor(R.color.white);
            vo_name.setTextColor(color);
            vo_id.setTextColor(color);
        } else if (message.getDirect() == MsgDirectionEnum.In) {
            int titleColor = DemoCache.getContext().getResources().getColor(R.color.color_blue_0888ff);
            int describeColor = DemoCache.getContext().getResources().getColor(R.color.color_blue_3a9efb);
            vo_name.setTextColor(titleColor);
            vo_id.setTextColor(describeColor);
        }
        vo_name.setText(attachment.getVo_nick());

        // 判断是否传了图片，如果没有传图片，则不显示图片区域
        if (TextUtils.isEmpty(attachment.getVo_avatar())) {
            vo_avatar.setVisibility(View.GONE);
        } else {
            vo_avatar.setVisibility(View.VISIBLE);
            // 图片加载器，异步加载网络图片，此处有误先别着急，往下看文档或者先注释掉此处
            ImageLoader.onLoadImage(attachment.getVo_avatar(), new ImageLoader.LoadImageListener() {

                @Override
                public void onLoadImage(Bitmap bitmap, String bitmapPath) {
                    if (bitmap != null) {
                        vo_avatar.setImageBitmap(bitmap);
                    }
                }
            });
        }

        // 判断是否传了描述，如果没有描述，则不显示描述区域
        if (TextUtils.isEmpty(attachment.getVo_id())) {
            vo_id.setVisibility(View.GONE);
        } else {
            vo_id.setText(attachment.getVo_id());
            vo_id.setVisibility(View.VISIBLE);
        }



    }


}
