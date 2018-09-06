package com.zl.vo_.own.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zl.vo_.NimApplication;
import com.zl.vo_.R;
import com.zl.vo_.own.ui.mine.bean.LifeNoteBean;
import com.zl.vo_.own.views.MessagePicturesLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter {
    private final int TYPE_HEADER = 0;//header
    private final int TYPE_NORMAL = 1;//正常布局
    private final int TYPE_FOOTER = 2;//footer

    private final List<LifeNoteBean> mDataList = new ArrayList<>();
    private MessagePicturesLayout.Callback mCallback;
    private View mHeaderView;
    private View mFooterView;
    public MessageAdapter(Context context) {
    }

    public MessageAdapter setPictureClickCallback(MessagePicturesLayout.Callback callback) {
        mCallback = callback;
        return this;
    }

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mHeaderView != null)
            return TYPE_HEADER;
        if (position == getItemCount() - 1 && mFooterView != null)
            return TYPE_FOOTER;
        return TYPE_NORMAL;

    }

    public void set(List<LifeNoteBean> dataList) {
        mDataList.clear();
        if (dataList != null) {
            mDataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        ImageView iAvatar;
        TextView tNickname, tTime, tContent;
        MessagePicturesLayout lPictures;
        LifeNoteBean mData;

        NormalViewHolder(View itemView) {
            super(itemView);
            iAvatar = (ImageView) itemView.findViewById(R.id.i_avatar);
            tNickname = (TextView) itemView.findViewById(R.id.t_nickname);
            tTime = (TextView) itemView.findViewById(R.id.t_time);
            tContent = (TextView) itemView.findViewById(R.id.t_content);
            lPictures = (MessagePicturesLayout) itemView.findViewById(R.id.l_pictures);
            lPictures.setCallback(mCallback);
        }

        void refresh(int pos) {
            mData = mDataList.get(pos);
            Glide.with(itemView.getContext()).load(mData.getAvatar()).into(iAvatar);
            tNickname.setText(mData.getNickname());
            tTime.setText(mData.getCreateTime());
            tContent.setText(mData.getContent());
            lPictures.set(mData.getPictureThumbList(), mData.getPictureList());
        }
    }
    //底部的viewHolder
    class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_footer)
        TextView tv_footer;
        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tv_footer.setText("加载完成");
        }
    }
    //头部的viewHolder
    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_life_note, parent, false));
        } else if(viewType == TYPE_HEADER){
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_life_note, parent, false));
        }else{
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_life_note, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_NORMAL){
            if (holder instanceof NormalViewHolder)
                ((NormalViewHolder) holder).refresh(position-1);
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mDataList.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return mDataList.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return mDataList.size() + 1;
        } else {
            return mDataList.size() + 2;
        }
    }

}
