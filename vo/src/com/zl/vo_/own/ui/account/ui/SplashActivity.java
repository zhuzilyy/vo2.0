package com.zl.vo_.own.ui.account.ui;

import android.Manifest;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zl.vo_.R;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.util.SPUtils;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/5/9.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView iv_splash;
    private String TAG="SplashActivity";

    @Override
    protected void initViews() {
        startAnim();
    }

    private void startAnim() {
        // 动画集合
        AnimationSet set = new AnimationSet(false);

       /* // 旋转动画
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setDuration(1000);// 动画时间
        rotate.setFillAfter(true);// 保持动画状态

        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scale.setDuration(1000);// 动画时间
        scale.setFillAfter(true);// 保持动画状态*/

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);// 动画时间
        alpha.setFillAfter(true);// 保持动画状态
       /* set.addAnimation(rotate);
        set.addAnimation(scale);*/
        set.addAnimation(alpha);

        // 设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                requestPermissions();
               // jumpNextPage();
            }
        });
        iv_splash.startAnimation(set);
    }
    /**
     * 跳转下一个页面
     */
    private void jumpNextPage() {
        //jumpActivity(SplashActivity.this,WelcomeActiity.class);
        // 判断之前有没有显示过新手引导
        boolean isFirst = (boolean) SPUtils.get(this, "isFirst", true);
        if (isFirst){
            jumpActivity(SplashActivity.this,Login_Register_Acitivity.class);
        }else{
            jumpActivity(SplashActivity.this,Login_Register_Acitivity.class);
        }
        finish();
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_own_splash);
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }

    /****
     * 动态权限获取
     */
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(SplashActivity.this);
        rxPermission.requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d(TAG, permission.name + " is granted.");
                            jumpNextPage();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                            jumpNextPage();
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d(TAG, permission.name + " is denied.");
                            jumpNextPage();
                        }
                    }
                });
    }


}
