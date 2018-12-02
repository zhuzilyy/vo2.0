package com.zl.vo_.own.ui.account.ui;


import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import com.google.gson.Gson;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nim.uikit.support.permission.MPermission;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionDenied;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionGranted;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionNeverAskAgain;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.zl.vo_.DemoCache;
import com.zl.vo_.NimApplication;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.Preferences;
import com.zl.vo_.config.preference.UserPreferences;
import com.zl.vo_.own.api.ApiAccount;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.bean.UserInfoBean;
import com.zl.vo_.own.ui.account.bean.UserInfoData;
import com.zl.vo_.own.util.CameraUtil;
import com.zl.vo_.own.util.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private MyReceiver myReceiver;
    private AbortableFuture<LoginInfo> loginRequest;
    @Override
    protected void initViews() {
        screenWidth = this.getResources().getDisplayMetrics().widthPixels;
        screenheight = this.getResources().getDisplayMetrics().heightPixels;
        Log.i("wh", "width: " + screenWidth + "    heeight: " + screenheight);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mHolder = surfaceView.getHolder();
        mHolder.addCallback(this);
        minit();

        //注册广播
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.action.wechat");
        registerReceiver(myReceiver,intentFilter);
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
                wechatLogin();
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
    //微信登录
    private void wechatLogin() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        NimApplication.mWxApi.sendReq(req);
    }

    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.wechat")){
                String openId = intent.getStringExtra("openid");
                String nickname =intent.getStringExtra("nickname");
                int sex =intent.getIntExtra("sex",0);
                String headimgurl =intent.getStringExtra("headimgurl");
                wxLogin(openId,nickname,sex,headimgurl);
            }
        }
    }
    //微信登录的方法
    private void wxLogin(final String openId, final String nickname, final int sex, final String headimgurl) {
        Map<String,String> params = new HashMap<>();
        params.put("openid",openId);
        params.put("headimgurl",headimgurl);
        params.put("sex",sex+"");
        params.put("nickname",nickname);
        ApiAccount.wechatLogin(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    if (code.equals("404004")){
                        Intent intent = new Intent(Login_Register_Acitivity.this,BindPhoneActivity.class);
                        intent.putExtra("openid",openId);
                        intent.putExtra("nickname",nickname);
                        intent.putExtra("sex",sex+"");
                        intent.putExtra("headimgurl",headimgurl);
                        startActivity(intent);
                    }else if (code.equals(ApiConstant.SUCCESS_CODE)){
                        String token = jsonObject.getJSONObject("data").getString("token");
                        SPUtils.put(Login_Register_Acitivity.this,"cloudToken",token);
                        getUserInfo();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void requestFailure(int code, String msg) {

            }
        });
    }

    //获取个人信息
    private void getUserInfo() {
        ApiAccount.getUserInfo(this, new OnRequestDataListener() {
            @Override
            public void requestSuccess( String data) {
                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                String code = userInfoBean.getCode();
                if (ApiConstant.SUCCESS_CODE.equals(code)){
                    UserInfoData userInfoData = userInfoBean.getData();
                    String token = userInfoData.getToken();
                    String vo_code_set = userInfoData.getVo_code_set();
                    String avatar = userInfoData.getAvatar();
                    String vo_code = userInfoData.getVo_code();
                    String nickName = userInfoData.getName();
                    String sex = userInfoData.getSex();
                    String country = userInfoData.getCountry();
                    String city = userInfoData.getCity();
                    String province = userInfoData.getProvince();
                    String id = userInfoData.getId();
                    String vo_code_can = userInfoData.getVo_code_can();
                    String signature = userInfoData.getSignature();

                    int by_mobile = Integer.parseInt(userInfoData.getBy_mobile());
                    int by_qrcode = Integer.parseInt(userInfoData.getBy_qrcode());
                    int by_vocode = Integer.parseInt(userInfoData.getBy_vo_code());
                    int by_card = Integer.parseInt(userInfoData.getBy_card());
                    int by_group_caht = Integer.parseInt(userInfoData.getBy_group_chat());


                    SPUtils.put(Login_Register_Acitivity.this,"token",token);
                    SPUtils.put(Login_Register_Acitivity.this,"avatar",avatar);
                    SPUtils.put(Login_Register_Acitivity.this,"vo_code",vo_code);
                    SPUtils.put(Login_Register_Acitivity.this,"nickName",nickName);
                    SPUtils.put(Login_Register_Acitivity.this,"sex",sex);
                    SPUtils.put(Login_Register_Acitivity.this,"country",country);
                    SPUtils.put(Login_Register_Acitivity.this,"city",city);
                    SPUtils.put(Login_Register_Acitivity.this,"province",province);
                    SPUtils.put(Login_Register_Acitivity.this,"id",id);
                    SPUtils.put(Login_Register_Acitivity.this,"vo_code_can",vo_code_can);
                    SPUtils.put(Login_Register_Acitivity.this,"signature",signature);
                    SPUtils.put(Login_Register_Acitivity.this,"vo_code_set",vo_code_set);
                    SPUtils.put(Login_Register_Acitivity.this,"by_mobile",by_mobile);
                    SPUtils.put(Login_Register_Acitivity.this,"by_vo_code",by_vocode);
                    SPUtils.put(Login_Register_Acitivity.this,"by_qrcode",by_qrcode);
                    SPUtils.put(Login_Register_Acitivity.this,"by_card",by_card);
                    SPUtils.put(Login_Register_Acitivity.this,"by_group_chat",by_group_caht);
                    //云信登录
                    cloudLogin(token,vo_code);
                }
            }
            @Override
            public void requestFailure(int code, String msg) {

            }
        });
    }
    private void cloudLogin(final String token,final String uuid) {
      /*  DialogMaker.showProgressDialog(this, null, getString(R.string.logining), true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (loginRequest != null) {
                    loginRequest.abort();
                    onLoginDone();
                }
            }
        }).setCanceledOnTouchOutside(false);*/
        loginRequest = NimUIKit.login(new LoginInfo(uuid, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {
                LogUtil.i(TAG, "login success");
                onLoginDone();
                DemoCache.setAccount(uuid);
                saveLoginInfo(uuid, token);
                // 初始化消息提醒配置
                initNotificationConfig();
                // 进入主界面
                startActivity(new Intent(Login_Register_Acitivity.this, com.zl.vo_.main.activity.MainActivity.class));
                finish();
            }
            @Override
            public void onFailed(int code) {
                onLoginDone();
                if (code == 302 || code == 404) {
                    Toast.makeText(Login_Register_Acitivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login_Register_Acitivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable throwable) {
                Toast.makeText(Login_Register_Acitivity.this, R.string.login_exception, Toast.LENGTH_LONG).show();
                onLoginDone();
            }
        });
    }
    /*
    登录成功后，保存账号和token
     */
    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);
    }
    /*
    初始化消息提醒配置
     */
    private void initNotificationConfig() {
        // 初始化消息提醒
        NIMClient.toggleNotification(UserPreferences.getNotificationToggle());

        // 加载状态栏配置
        StatusBarNotificationConfig statusBarNotificationConfig = UserPreferences.getStatusConfig();
        if (statusBarNotificationConfig == null) {
            statusBarNotificationConfig = DemoCache.getNotificationConfig();
            UserPreferences.setStatusConfig(statusBarNotificationConfig);
        }
        // 更新配置
        NIMClient.updateStatusBarNotificationConfig(statusBarNotificationConfig);
    }
    private void onLoginDone() {
        loginRequest = null;
        DialogMaker.dismissProgressDialog();
    }
}
