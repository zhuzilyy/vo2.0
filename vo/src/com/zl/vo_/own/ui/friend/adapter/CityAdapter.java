package com.zl.vo_.own.ui.friend.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zl.vo_.R;
import com.zl.vo_.own.beans.CityBean;
import com.zl.vo_.own.util.PhoneUtils;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    protected Context mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;
    private Activity activity;

    public CityAdapter(Context mContext, List<CityBean> mDatas, Activity activity) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.activity = activity;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public CityAdapter setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final CityAdapter.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "pos:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        if(TextUtils.isEmpty(cityBean.getAvatar())){
           holder.avatar.setImageResource(R.drawable.friend);
        }else {
            Glide.with(mContext).load(cityBean.getAvatar()).into(holder.avatar);
        }
        //注册该软件
        if((1 == cityBean.getIs_app()) && 0 ==cityBean.getIs_friend()){
            holder.btn.setBackgroundResource(R.drawable.shape_invitation);
            holder.btn.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.btn.setText("添加");
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "添加", Toast.LENGTH_SHORT).show();
                    String vocode =  cityBean.getVo_code();


                }
            });
        }else if(0 == cityBean.getIs_app()){
            holder.btn.setText("邀请");
            holder.btn.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.btn.setBackgroundResource(R.drawable.shape_invitation);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxPermissions rxPermissions = new RxPermissions(activity);
                    rxPermissions
                            //request中申请多个权限
                            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.CALL_PHONE,
                                    Manifest.permission.SEND_SMS)
                            .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(@NonNull Boolean aBoolean) throws Exception {
                                    //根据Boolean来判断申请成功后和申请失败后的逻辑判断
                                    if (aBoolean) {
                                        Toast.makeText(mContext, "申请成功", Toast.LENGTH_SHORT).show();
                                        PhoneUtils.sendSms("下载vo吧");
                                    } else {
                                        Toast.makeText(mContext, "取消申请", Toast.LENGTH_SHORT).show();
                                        PhoneUtils.sendSms("下载vo吧");
                                    }
                                }
                            });



                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView avatar;
        View content;
        Button btn;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            content = itemView.findViewById(R.id.content);
            btn = itemView.findViewById(R.id.btn_addOrInvatation);

        }
    }
}
