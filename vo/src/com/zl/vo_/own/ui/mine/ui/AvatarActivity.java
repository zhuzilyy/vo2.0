package com.zl.vo_.own.ui.mine.ui;

import android.view.View;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.dialog.PhotoChioceDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/27.
 */

public class AvatarActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @Override
    protected void initViews() {
        tv_title.setText("更改头像");
        tv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
       setContentView(R.layout.activity_avatar);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.rl_avatar})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_avatar:
                showPhotoDialog();
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
}
