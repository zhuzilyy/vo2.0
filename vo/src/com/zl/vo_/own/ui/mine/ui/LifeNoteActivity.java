package com.zl.vo_.own.ui.mine.ui;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ielse.imagewatcher.ImageWatcher;
import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.dialog.PhotoChioceDialog;
import com.zl.vo_.own.ui.mine.adapter.LifeNoteAdapter;
import com.zl.vo_.own.ui.mine.adapter.MessageAdapter;
import com.zl.vo_.own.ui.mine.bean.LifeNoteBean;
import com.zl.vo_.own.util.RecyclerViewUtil;
import com.zl.vo_.own.util.WeiboDialogUtils;
import com.zl.vo_.own.util.WindowUtils;
import com.zl.vo_.own.views.CustomLoadingUIProvider;
import com.zl.vo_.own.views.MessagePicturesLayout;
import com.zl.vo_.own.views.SimpleLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/29.
 */

public class LifeNoteActivity extends BaseActivity implements View.OnClickListener, MessagePicturesLayout.Callback {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rv_lifeNote)
    RecyclerView rv_lifeNote;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;
    private MessageAdapter adapter;
    private View view_header,view_footer;
    private TextView tv_footer;
    private RelativeLayout rl_writeLifeNote;
    private ImageWatcherHelper iwHelper;
    boolean isTranslucentStatus;
    private RecyclerViewUtil recyclerViewUtil;
    private LinearLayoutManager manager;
    @Override
    protected void initViews() {
        tv_title.setText("人生笔记");
    }
    @Override
    protected void initData() {
        view_header=LayoutInflater.from(this).inflate(R.layout.header_life_note,null);
        view_footer=LayoutInflater.from(this).inflate(R.layout.footer_life_note,null);
        manager = new LinearLayoutManager(this);
        rv_lifeNote.setLayoutManager(manager);
        //vRecycler.addItemDecoration(new SpaceItemDecoration(this).setSpace(14).setSpaceColor(0xFFECECEC));
        rv_lifeNote.setAdapter(adapter = new MessageAdapter(this).setPictureClickCallback(this));
        adapter.set(LifeNoteBean.get());
        adapter.setHeaderView(view_header);
        //adapter.setFooterView(view_footer);
        addView();
    }
    private void addView() {
        iwHelper = ImageWatcherHelper.with(LifeNoteActivity.this,new SimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setTranslucentStatus(!isTranslucentStatus ? WindowUtils.calcStatusBarHeight(this) : 0) // 如果不是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
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
                            Toast.makeText(getApplicationContext(), "点击了图片 [" + position + "]" + uri + "", Toast.LENGTH_SHORT).show();
                        } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
                            //Toast.makeText(getApplicationContext(), "退出了查看大图", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 自定义LoadingUI
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_life_note);
    }
    @Override
    protected void initListener() {
        ImageView iv_changeBg=view_header.findViewById(R.id.iv_changeBg);
        rl_writeLifeNote=view_header.findViewById(R.id.writeLifeNote_circle);
        tv_footer=view_footer.findViewById(R.id.tv_footer);
        iv_changeBg.setOnClickListener(this);
        rl_writeLifeNote.setOnClickListener(this);
        recyclerViewUtil=new RecyclerViewUtil(this,rv_lifeNote);
        recyclerViewUtil.setOnLoadMoreListener(new RecyclerViewUtil.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Dialog dialog = WeiboDialogUtils.createLoadingDialog(LifeNoteActivity.this, "加载中");
                dialog.show();
                //adapter.set(LifeNoteBean.get());
                //Toast.makeText(LifeNoteActivity.this, "到底了。。。。。。。。。。", Toast.LENGTH_SHORT).show();
            }
        });
    /*  rv_lifeNote.addOnScrollListener(new RecyclerView.OnScrollListener() {
          boolean isSlidingToLast = false;
          @Override
          public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
              super.onScrollStateChanged(recyclerView, newState);
              if (newState== RecyclerView.SCROLL_STATE_IDLE && isSlidingToLast){
                  int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                  int totalItemCount = manager.getItemCount();
                  if (lastVisibleItemPosition==totalItemCount-1){
                      Dialog dialog = WeiboDialogUtils.createLoadingDialog(LifeNoteActivity.this, "加载中");
                      dialog.show();
                  }
              }
          }
          @Override
          public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
              super.onScrolled(recyclerView, dx, dy);
              if (dy>0){
                  isSlidingToLast = true;
              }else{
                  isSlidingToLast = false;
              }
          }
      });
*/
    }
    @Override
    protected void setStatusBarColor() {

    }
    //点击事件
    @OnClick({R.id.iv_back})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_changeBg:
                showPhotoDialog();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.writeLifeNote_circle:
                jumpActivity(this,WriteNoteActivity.class);
                break;
        }
    }
    //照片选择器
    private void showPhotoDialog() {
        PhotoChioceDialog photoChioceDialog = new PhotoChioceDialog(this);
        photoChioceDialog.show();
        photoChioceDialog.setClickCallback(new PhotoChioceDialog.ClickCallback() {
            @Override
            public void doAlbum() {
                //readPhotoAlbum();
            }
            @Override
            public void doCancel() {
            }
            @Override
            public void doCamera() {
                //takePhoto();
            }
        });
    }


    @Override
    public void onThumbPictureClick(ImageView i, SparseArray<ImageView> imageGroupList, List<Uri> urlList) {
        iwHelper.show(i, imageGroupList, urlList);
    }
    @Override
    public void onBackPressed() {
        if (!iwHelper.handleBackPressed()) {
            super.onBackPressed();
        }
    }
    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }
}
