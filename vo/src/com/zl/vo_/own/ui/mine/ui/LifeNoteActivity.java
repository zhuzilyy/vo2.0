package com.zl.vo_.own.ui.mine.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.dialog.PhotoChioceDialog;
import com.zl.vo_.own.ui.mine.adapter.LifeNoteAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/29.
 */

public class LifeNoteActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.lv_lifeNote)
    ListView lv_lifeNote;
    private LifeNoteAdapter lifeNoteAdapter;
    private View view_header;
    private RelativeLayout rl_writeLifeNote;
    @Override
    protected void initViews() {
        tv_title.setText("人生笔记");
    }
    @Override
    protected void initData() {
        view_header=LayoutInflater.from(this).inflate(R.layout.header_life_note,null);
        lifeNoteAdapter = new LifeNoteAdapter(this);
        lv_lifeNote.setAdapter(lifeNoteAdapter);
        lv_lifeNote.addHeaderView(view_header);


    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_life_note);
    }
    @Override
    protected void initListener() {
        ImageView iv_changeBg=view_header.findViewById(R.id.iv_changeBg);
        rl_writeLifeNote=view_header.findViewById(R.id.writeLifeNote_circle);
        iv_changeBg.setOnClickListener(this);
        rl_writeLifeNote.setOnClickListener(this);
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
}
