package com.zl.vo_.own.ui.account;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.netease.nim.uikit.support.permission.MPermission;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionDenied;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionGranted;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionNeverAskAgain;
import com.zl.vo_.R;

import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.ui.MainActivity;
import com.zl.vo_.own.util.CameraUtil;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 2014
 */


public class Login_Register_Acitivity extends BaseActivity implements View.OnClickListener, SurfaceHolder.Callback  {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String KICK_OUT = "KICK_OUT";
    private final int BASIC_PERMISSION_REQUEST_CODE = 110;

    private int screenWidth;
    private int screenheight;

    private Camera mCamera;
    private SurfaceView surfaceView;
    private SurfaceHolder mHolder;
    //默认前置或者后置相机 0:后置 1:前置
    private int mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
    private int video_width;
    private int video_height;
    private int recorderRotation;

    //--------------------------
    private String ResultAccount;
    public static Activity instance;
    //---新ui---------------------------------
    @BindView(R.id.ll_phone_login)
    public LinearLayout ll_phone_login;
    @BindView(R.id.ll_phone_register)
    public LinearLayout ll_phone_register;
    @BindView(R.id.ll_wx_login)
    public LinearLayout ll_wx_login;
    @Override
    protected void initViews() {
        screenWidth = this.getResources().getDisplayMetrics().widthPixels;
        screenheight = this.getResources().getDisplayMetrics().heightPixels;
        Log.i("wh", "width: " + screenWidth + "    heeight: " + screenheight);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mHolder = surfaceView.getHolder();
        mHolder.addCallback(this);
        minit();
    }
    private void minit() {
        instance=this;
        requestBasicPermission();
    }
    /**
     * 基本权限管理
     */
    private final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private void requestBasicPermission() {
        MPermission.with(Login_Register_Acitivity.this)
                .setRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(BASIC_PERMISSIONS)
                .request();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
       // Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    @OnMPermissionNeverAskAgain(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
      //  Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCamera(1);
            if (mHolder != null && mCamera != null) {
                //开启预览
                startPreview(mCamera, mHolder);
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }
    /*
     * 获取Camera实例
     *
     * @return
     */
    private Camera getCamera(int id) {
        Camera camera = null;
        try {
            camera = Camera.open(id);
        } catch (Exception e) {
        }
        return camera;
    }
    /*
     * 预览相机
     */
    private void startPreview(Camera camera, SurfaceHolder holder) {
        if(camera==null){
            return;
        }
        try {
            setupCamera(camera);
            camera.setPreviewDisplay(holder);
            //获取相机预览角度， 后面录制视频需要用
            recorderRotation = CameraUtil.getInstance().getRecorderRotation(mCameraId);
            CameraUtil.getInstance().setCameraDisplayOrientation(this, mCameraId, camera);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * 设置
     */
    private void setupCamera(Camera camera) {
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            List<String> focusModes = parameters.getSupportedFocusModes();
            if (focusModes != null && focusModes.size() > 0) {
                if (focusModes.contains(
                        Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                    //设置自动对焦
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                }
            }
            //这里第三个参数为最小尺寸 getPropPreviewSize方法会对从最小尺寸开始升序排列 取出所有支持尺寸的最小尺寸
            Camera.Size previewSize = CameraUtil.getInstance().getPropPreviewSize(parameters.getSupportedPreviewSizes(), video_width);
            parameters.setPreviewSize(previewSize.width, previewSize.height);

            Camera.Size pictrueSize = CameraUtil.getInstance().getPropPictureSize(parameters.getSupportedPictureSizes(), video_width);
            parameters.setPictureSize(pictrueSize.width, pictrueSize.height);

            camera.setParameters(parameters);

            /**
             * 设置surfaceView的尺寸 因为camera默认是横屏，所以取得支持尺寸也都是横屏的尺寸
             * 我们在startPreview方法里面把它矫正了过来，但是这里我们设置设置surfaceView的尺寸的时候要注意 previewSize.height<previewSize.width
             * previewSize.width才是surfaceView的高度
             * 一般相机都是屏幕的宽度 这里设置为屏幕宽度 高度自适应 你也可以设置自己想要的大小
             */
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(screenWidth, screenheight);//(screenWidth * video_width) / video_height
            //这里当然可以设置拍照位置 比如居中 我这里就置顶了
            surfaceView.setLayoutParams(params);

        }
    }

    /*
     * 释放相机资源
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void getResLayout() {
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_own_login_register);
    }
    @Override
    protected void initListener() {
    }
    @Override
    protected void setStatusBarColor() {
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        startPreview(mCamera, mHolder);
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(mCamera==null){
            return;
        }
        mCamera.stopPreview();
        startPreview(mCamera, surfaceHolder);
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        releaseCamera();
    }

    //----------------------
    public static void start(Context context) {
        start(context, false);
    }

    public static void start(Context context, boolean kickOut) {
        Intent intent = new Intent(context, Login_Register_Acitivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KICK_OUT, kickOut);
        context.startActivity(intent);
    }

    @OnClick({ R.id.ll_wx_login,R.id.ll_phone_register,R.id.ll_phone_login
    })
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_wx_login:
                //微信登录

                break;
            case R.id.ll_phone_login:
                //手机号登录
                startActivity(new Intent(Login_Register_Acitivity.this, LoginActivity.class));
                break;
            case R.id.ll_phone_register:
                //手机号注册
                startActivity(new Intent(Login_Register_Acitivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

}
