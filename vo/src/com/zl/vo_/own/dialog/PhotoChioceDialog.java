package com.zl.vo_.own.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.zl.vo_.R;


/**
 *  图片选择弹出框
 *
 * @author fee https://github.com/FeeAlan/BootomDialog
 * @created 2016/07/18
 */
public class PhotoChioceDialog extends BaseDialog {
    private ClickCallback clickCallback;
    private Button btn_album,btn_camera;
    public PhotoChioceDialog(Context context){
        super(context);
        dialog.setContentView(R.layout.dialog_pic_chioce);
        btn_album=dialog.findViewById(R.id.btn_album);
        btn_camera=dialog.findViewById(R.id.btn_camera);
        btn_album.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(this);
        setDialogLocation(mContext, dialog);
    }
    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }
    public void setSelectot1(String selector){
        if (!TextUtils.isEmpty(selector)){
            btn_album.setText(selector);
        }
    }
    public void setSelectot2(String selector){
        if (!TextUtils.isEmpty(selector)){
            btn_camera.setText(selector);
        }
    }

    public interface ClickCallback {
        /**
         * 进入相册
         */
        void doAlbum();

        /**
         * 取消
         */
        void doCancel();

        /**
         * 进入相机
         */
        void doCamera();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_album:
                if (clickCallback!=null)
                    clickCallback.doAlbum();
                break;
            case R.id.btn_camera:
                if (clickCallback!=null)
                    clickCallback.doCamera();
                break;
            case R.id.btn_cancel:
                if (clickCallback!=null)
                    clickCallback.doCancel();
                break;
        }
    }


}
