package com.zl.vo_.own.ui.mine.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.api.ApiMine;
import com.zl.vo_.own.base.BaseActivity;
import com.zl.vo_.own.dialog.PhotoChioceDialog;
import com.zl.vo_.own.listener.OnRequestDataListener;
import com.zl.vo_.own.ui.account.bean.UserInfoBean;
import com.zl.vo_.own.ui.account.bean.UserInfoData;
import com.zl.vo_.own.ui.mine.bean.JsonBean;
import com.zl.vo_.own.util.GetJsonDataUtil;
import com.zl.vo_.own.util.SPUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/27.
 */

public class ChangeMoreActivity extends BaseActivity {
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.tv_sign)
    TextView tv_sign;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    private ArrayList<JsonBean> options1Items;
    private ArrayList<ArrayList<String>> options2Items;
    private ArrayList<ArrayList<ArrayList<String>>> options3Items;
    private Thread thread;
    private boolean isLoaded = false;
    //省份选择器
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    break;
            }
        }
    };

    @Override
    protected void initViews() {
        tv_title.setText("更多");
        options1Items = new ArrayList<>();
        options2Items = new ArrayList<>();
        options3Items = new ArrayList<>();
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        //设置签名
        String signature = (String) SPUtils.get(ChangeMoreActivity.this, "signature", "");
        tv_sign.setText(signature);
        //设置地区
        String province = (String) SPUtils.get(ChangeMoreActivity.this, "province", "");
        String city = (String) SPUtils.get(ChangeMoreActivity.this, "city", "");
        String country = (String) SPUtils.get(ChangeMoreActivity.this, "country", "");
        tv_address.setText(province+"-"+city+"-"+country);
        //设置性别
        String sex = (String) SPUtils.get(ChangeMoreActivity.this, "sex", "");
        if(sex.equals("1")){
            tv_sex.setText("男");
        }else if (sex.equals("0")){
            tv_sex.setText("女");
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_change_more);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }

    @OnClick({R.id.iv_back, R.id.rl_sex, R.id.re_address, R.id.rl_sign})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_sex:
                showSexDialog();
                break;
            case R.id.re_address:
                showAddressDialog();
                break;
            case R.id.rl_sign:
                jumpActivity(this, PersonSignActivity.class, 101);
                break;
        }
    }

    //弹出地址选择框
    private void showAddressDialog() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String address = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                //设置地址
               setAddress(options1Items.get(options1).getPickerViewText(),options2Items.get(options1).get(options2),options3Items.get(options1).get(options2).get(options3));


            }
        }).setTitleText("城市选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK).setContentTextSize(20).build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /**
     * 设置地址
     */
    public void setAddress(final String provice, final String city2, final String country2){
        Map<String,String> params = new HashMap<>();
        params.put("province",provice);
        params.put("city",city2);
        params.put("country",country2);
        ApiMine.setAddress(ChangeMoreActivity.this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                String code = userInfoBean.getCode();
                String message = userInfoBean.getMessage();
                Toast.makeText(ChangeMoreActivity.this, message, Toast.LENGTH_SHORT).show();
                UserInfoData userInfoData = userInfoBean.getData();
                if (code.equals(ApiConstant.SUCCESS_CODE)){
                    String token = userInfoData.getToken();
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
                    SPUtils.put(ChangeMoreActivity.this,"token",token);
                    SPUtils.put(ChangeMoreActivity.this,"avatar",avatar);
                    SPUtils.put(ChangeMoreActivity.this,"vo_code",vo_code);
                    SPUtils.put(ChangeMoreActivity.this,"nickName",nickName);
                    SPUtils.put(ChangeMoreActivity.this,"sex",sex);
                    SPUtils.put(ChangeMoreActivity.this,"country",country);
                    SPUtils.put(ChangeMoreActivity.this,"city",city);
                    SPUtils.put(ChangeMoreActivity.this,"province",province);
                    SPUtils.put(ChangeMoreActivity.this,"id",id);
                    SPUtils.put(ChangeMoreActivity.this,"vo_code_can",vo_code_can);
                    SPUtils.put(ChangeMoreActivity.this,"signature",signature);

                    tv_address.setText(provice+"-"+city2+"-"+country2);

                }

            }

            @Override
            public void requestFailure(int code, String msg) {
                Log.i("tag",msg);
                Toast.makeText(ChangeMoreActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //性别选择对话框
    private void showSexDialog() {
        PhotoChioceDialog photoChioceDialog = new PhotoChioceDialog(this);
        photoChioceDialog.setSelectot1("男");
        photoChioceDialog.setSelectot2("女");
        photoChioceDialog.show();
        photoChioceDialog.setClickCallback(new PhotoChioceDialog.ClickCallback() {
            @Override
            public void doAlbum() {
              setSex("男");

            }

            @Override
            public void doCancel() {
            }

            @Override
            public void doCamera() {
                setSex("女");

            }
        });
    }

    /**
     * 设置性别
     */
    public void setSex(String sex){
        if(sex.equals("男")){
            changeSex("1");
        }else if (sex.equals("女")){
            changeSex("0");
        }
    }

    /**
     * 设置性别2
     * @param sex
     */
    public void changeSex(String sex){
        Map<String,String> params = new HashMap<>();
        params.put("sex",sex);
        ApiMine.setSex(ChangeMoreActivity.this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(String data) {
                Log.i("tag",data);
                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(data, UserInfoBean.class);
                String code = userInfoBean.getCode();
                String message = userInfoBean.getMessage();
                Toast.makeText(ChangeMoreActivity.this, message, Toast.LENGTH_SHORT).show();
                UserInfoData userInfoData = userInfoBean.getData();
                if (code.equals(ApiConstant.SUCCESS_CODE)){
                    String token = userInfoData.getToken();
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
                    SPUtils.put(ChangeMoreActivity.this,"token",token);
                    SPUtils.put(ChangeMoreActivity.this,"avatar",avatar);
                    SPUtils.put(ChangeMoreActivity.this,"vo_code",vo_code);
                    SPUtils.put(ChangeMoreActivity.this,"nickName",nickName);
                    SPUtils.put(ChangeMoreActivity.this,"sex",sex);
                    SPUtils.put(ChangeMoreActivity.this,"country",country);
                    SPUtils.put(ChangeMoreActivity.this,"city",city);
                    SPUtils.put(ChangeMoreActivity.this,"province",province);
                    SPUtils.put(ChangeMoreActivity.this,"id",id);
                    SPUtils.put(ChangeMoreActivity.this,"vo_code_can",vo_code_can);
                    SPUtils.put(ChangeMoreActivity.this,"signature",signature);

                    if(sex.equals("1")){
                        tv_sex.setText("男");
                    }else if (sex.equals("0")){
                        tv_sex.setText("女");
                    }

                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                Log.i("tag",msg);
                Toast.makeText(ChangeMoreActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 201) {
            String signature = data.getStringExtra("signature");
            //设置签名
            tv_sign.setText(signature);

        }


    }
}
