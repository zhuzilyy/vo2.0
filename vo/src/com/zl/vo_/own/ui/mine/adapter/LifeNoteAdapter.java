package com.zl.vo_.own.ui.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.ielse.imagewatcher.ImageWatcher;
import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.zl.vo_.R;
import com.zl.vo_.own.ui.mine.bean.LifeNoteBean;
import com.zl.vo_.own.util.WindowUtils;
import com.zl.vo_.own.views.CustomLoadingUIProvider;
import com.zl.vo_.own.views.MessagePicturesLayout;
import com.zl.vo_.own.views.SimpleLoader;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6.
 */

public class LifeNoteAdapter extends BaseAdapter implements MessagePicturesLayout.Callback {
    private Context context;
    private MessageAdapter adapter;
    private ImageWatcherHelper iwHelper;
    boolean isTranslucentStatus;
    public LifeNoteAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 1;
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
        view=LayoutInflater.from(context).inflate(R.layout.item_life_note_recyclerview,null);
        RecyclerView rv_lifeNote=view.findViewById(R.id.rv_lifeNote);
        rv_lifeNote.setLayoutManager(new LinearLayoutManager(context));
        rv_lifeNote.setAdapter(adapter = new MessageAdapter(context).setPictureClickCallback(this));
        adapter.set(LifeNoteBean.get());
        addView();
        return view;
    }

    private void addView() {
        iwHelper = ImageWatcherHelper.with((Activity) context,new SimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setTranslucentStatus(!isTranslucentStatus ? WindowUtils.calcStatusBarHeight(context) : 0) // 如果不是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
                .setErrorImageRes(R.mipmap.error_picture) // 配置error图标 如果不介意使用lib自带的图标，并不一定要调用这个API
                .setOnPictureLongPressListener(new ImageWatcher.OnPictureLongPressListener() {
                    @Override
                    public void onPictureLongPress(ImageView v, Uri uri, int pos) {
                        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
                        //Toast.makeText(v.getContext().getApplicationContext(), "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnStateChangedListener(new ImageWatcher.OnStateChangedListener() {
                    @Override
                    public void onStateChangeUpdate(ImageWatcher imageWatcher, ImageView clicked, int position, Uri uri, float animatedValue, int actionTag) {
                        Log.e("IW", "onStateChangeUpdate [" + position + "][" + uri + "][" + animatedValue + "][" + actionTag + "]");
                    }
                    @Override
                    public void onStateChanged(ImageWatcher imageWatcher, int position, Uri uri, int actionTag) {
                        if (actionTag == ImageWatcher.STATE_ENTER_DISPLAYING) {
                            Toast.makeText(context, "点击了图片 [" + position + "]" + uri + "", Toast.LENGTH_SHORT).show();
                        } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
                            //Toast.makeText(getApplicationContext(), "退出了查看大图", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 自定义LoadingUI
    }

    @Override
    public void onThumbPictureClick(ImageView i, SparseArray<ImageView> imageGroupList, List<Uri> urlList) {
        iwHelper.show(i, imageGroupList, urlList);
    }
}
