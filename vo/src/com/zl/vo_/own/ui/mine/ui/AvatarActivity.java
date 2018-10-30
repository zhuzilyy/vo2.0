package com.zl.vo_.own.ui.mine.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiMine;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.dialog.PhotoChioceDialog;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.util.BitmapToBase64;
import com.zl.vo_.own.util.PhotoUtils;
import com.zl.vo_.own.util.SPUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/8/27.
 */

public class AvatarActivity extends BaseActivity {
    //测试苹果电脑
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;
    //***************************************************************7.0拍照相册
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/avatar.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_avatarPhoto.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private Bitmap cropBitmap;
    @Override
    protected void initViews() {
        tv_title.setText("更改头像");
        tv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        String avatar = (String) SPUtils.get(this, "avatar", "");
        Glide.with(this).load(avatar).into(iv_avatar);
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
    @OnClick({R.id.iv_back,R.id.rl_avatar,R.id.tv_right})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_avatar:
                showPhotoDialog();
                break;
            case R.id.tv_right:
                changeAvatarBitmap(cropBitmap);
                break;
        }
    }
    //照片选择器
    private void showPhotoDialog() {
        PhotoChioceDialog photoChioceDialog = new PhotoChioceDialog(this);
        photoChioceDialog.show();
        photoChioceDialog.setClickCallback(new PhotoChioceDialog.ClickCallback() {
            @Override
            public void doCamera() {
                readPhotoAlbum();
            }
            @Override
            public void doCancel() {
            }
            @Override
            public void doAlbum () {
                takePhoto();
            }
        });
    }
    //读取相册
    private void readPhotoAlbum() {
        //指定action   [照相机]
        requestPermissions(AvatarActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
            @Override
            public void granted() {
                if (hasSdcard()) {
                    imageUri = Uri.fromFile(fileUri);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        //通过FileProvider创建一个content类型的Uri
                        imageUri = FileProvider.getUriForFile(AvatarActivity.this, "com.zl.vo_.fileprovider", fileUri);
                    PhotoUtils.takePicture(AvatarActivity.this, imageUri, CODE_CAMERA_REQUEST);
                } else {
                    Toast.makeText(AvatarActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                    Log.e("asd", "设备没有SD卡");
                }
            }

            @Override
            public void denied() {
                Toast.makeText(AvatarActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
            }
        });
    }
    //拍照
    private void takePhoto() {
        requestPermissions(AvatarActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
            @Override
            public void granted() {
                PhotoUtils.openPic(AvatarActivity.this, CODE_GALLERY_REQUEST);
            }
            @Override
            public void denied() {
                Toast.makeText(AvatarActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
            }
        });
    }
    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 120, output_Y = 120;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);
                Bitmap bitmap = null;
                try {
                    bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), cropImageUri);
                    String base64 = BitmapToBase64.bitmapToBase64(bitmap);
                    Log.i("TAG","11"+base64);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "com.zl.vo_.fileprovider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        Toast.makeText(AvatarActivity.this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    cropBitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (cropBitmap != null) {
                        iv_avatar.setImageBitmap(cropBitmap);
                    }
                    break;
            }
        }
    }
    //修改头像
    private void changeAvatarBitmap(Bitmap cropBitmap) {
       /* Bitmap bitmap = null;
        try {
            bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), cropImageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String base64Avatar = BitmapToBase64.bitmapToBase64(cropBitmap);
        Log.i("tag",base64Avatar);
        Map<String,String> params = new HashMap<>();
        params.put("avatar_basestr",base64Avatar);
        ApiMine.changeAvatar(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
            }
            @Override
            public void requestFailure(int code, String msg) {
                Log.i("tag",msg);
            }
        });
    }
}
