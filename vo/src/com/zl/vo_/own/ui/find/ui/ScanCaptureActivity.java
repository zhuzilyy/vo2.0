package com.zl.vo_.own.ui.find.ui;

import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;

import net.sourceforge.zbar.ImageScanner;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/4.
 */

public class ScanCaptureActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    private Camera mCamera;
    //private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private CameraManager mCameraManager;

    private TextView scanResult;
    private FrameLayout scanPreview;
    private Button scanRestart;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private ImageView scanLine;

    private Rect mCropRect = null;
    private boolean barcodeScanned = false;
    private boolean previewing = true;
    private ImageScanner mImageScanner = null;
    @Override
    protected void initViews() {
        tv_title.setText("扫一扫");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_scan_capture);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
