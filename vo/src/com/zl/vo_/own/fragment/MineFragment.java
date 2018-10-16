package com.zl.vo_.own.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.vo_.R;
import com.zl.vo_.own.api.ApiConstant;
import com.zl.vo_.own.base.BaseFragment;
import com.zl.vo_.own.dialog.CustomerDialog;
import com.zl.vo_.own.dialog.JiamiDialog;
import com.zl.vo_.own.dialog.LifeNotePwdDialog;
import com.zl.vo_.own.dialog.LifeNotePwdSettingDialog;
import com.zl.vo_.own.dialog.PrivateFriendsDialog;
import com.zl.vo_.own.dialog.VipDialog;
import com.zl.vo_.own.dialog.VipFunctionIntroduceDialog;
import com.zl.vo_.own.ui.account.WebViweActivity;
import com.zl.vo_.own.ui.mine.ui.CancleLifeNotePwdActivity;
import com.zl.vo_.own.ui.mine.ui.ChangePrivateFriendsActivity;
import com.zl.vo_.own.ui.mine.ui.FindLifeNotePwdActivity;
import com.zl.vo_.own.ui.mine.ui.FixLifeNotePwdActivity;
import com.zl.vo_.own.ui.mine.ui.LifeNoteActivity;
import com.zl.vo_.own.ui.mine.ui.SetLifeNotePwdActivity;
import com.zl.vo_.own.ui.mine.ui.SetPrivateFriendsActivity;
import com.zl.vo_.own.ui.mine.ui.UserInfoActivity;
import com.zl.vo_.own.ui.mine.ui.VipActivity;
import com.zl.vo_.own.util.SPUtils;
import com.zl.vo_.own.views.DetailsTypePopupWindow;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/17.
 */

public class MineFragment extends BaseFragment{
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    private VipDialog vipDialog;
    private TextView tv_nickName;
    private MyReceiver myReceiver = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View view_mine=inflater.inflate(R.layout.fragment_mine,null);
        tv_nickName = view_mine.findViewById(R.id.tv_name);
        return view_mine;
    }

    @Override
    protected void initViews() {
        myReceiver =  new MyReceiver();
        IntentFilter filter01 = new IntentFilter("nick_success");
        getActivity().registerReceiver(myReceiver,filter01);
        //-------------------------
        tv_title.setText("我的");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.rl_lifeNote,R.id.rl_setPrivacyFriends,R.id.rl_lifeNotePwdSetting,R.id.rl_infoTransmission,R.id.rl_deletePravcyFriends,
    R.id.rl_userInfo,R.id.iv_add,R.id.rl_openVip})
    public void click(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.rl_lifeNote:
                //showLifeNoteDialog();
                intent=new Intent(getActivity(), LifeNoteActivity.class);
                startActivity(intent);
                break;
            //隐私好友设置
            case R.id.rl_setPrivacyFriends:
                //showOpenVipDialog();
                showPrivateFriendsDialog();
                break;
            //人生笔记密码设置
            case R.id.rl_lifeNotePwdSetting:
                showLifeNotePwdDialog();
                break;
            //信息传输加密设置
            case R.id.rl_infoTransmission:
                showInfoTransmissionDialog();
                break;
            //隐私好友一键删除
            case R.id.rl_deletePravcyFriends:
                showDeleteFriendDialog();
                break;
            //用户信息
            case R.id.rl_userInfo:
                intent=new Intent(getActivity(),UserInfoActivity.class);
                startActivity(intent);
                break;
            //添加
            case R.id.iv_add:
                showAddPw(iv_add);
                break;
            case R.id.rl_openVip:
                intent=new Intent(getActivity(), VipActivity.class);
                startActivity(intent);
                break;
        }
    }
    //显示右上角的添加按钮
    private void showAddPw(View view) {
        DetailsTypePopupWindow typePopupWindow = new DetailsTypePopupWindow(getActivity(), view, "", "");
        typePopupWindow.setmItemsOnClick(new DetailsTypePopupWindow.ItemsOnClick() {
            @Override
            public void itemsOnClick(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "00000000000", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getActivity(), GroupsActivity.class));
                        break;
                    //添加新的好友
                    case 1:
                        Toast.makeText(getActivity(), "111111111111", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getActivity(), addFriendActivity_ContactsVo.class));
                        break;
                    //扫一扫
                    case 2:
                        Toast.makeText(getActivity(), "222222222222222", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getActivity(), ScanCaptureActivity.class));
                        break;
                    //帮助及反馈
                    case 3:
                        Intent intent=new Intent(getActivity(),WebViweActivity.class);
                        intent.putExtra("url", ApiConstant.FUNCTION_INTRODUCE);
                        intent.putExtra("title","功能介绍");
                        startActivity(intent);
                        break;
                }
            }
        });
    }
    //设置加密好友
    private void showPrivateFriendsDialog() {
        final PrivateFriendsDialog dialog=new PrivateFriendsDialog(getActivity());
        dialog.setNoOnclickListener(new PrivateFriendsDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();

            }
        });
        dialog.setAddOrReduceFriendsOnclickListener(new PrivateFriendsDialog.onAddOrReduceFriendsOnclickListener() {
            @Override
            public void onClick() {
                dialog.dismiss();
                Intent intent=new Intent(getActivity(), ChangePrivateFriendsActivity.class);
                startActivity(intent);
            }
        });
        dialog.setFriendsOnclickListener(new PrivateFriendsDialog.onFriendsOnclickListener() {
            @Override
            public void onClick() {
                dialog.dismiss();
                Intent intent=new Intent(getActivity(), SetPrivateFriendsActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
    //删除加密好友
    private void showDeleteFriendDialog() {
        final CustomerDialog dialog=new CustomerDialog(getActivity());
        dialog.setDialogTitle("删除加密好友");
        dialog.setDialogConfirmText("确定");
        dialog.setDialogMessage("您确定删除所有加密好友吗(删除后不可恢复)");
        dialog.setYesOnclickListener(new CustomerDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                dialog.dismiss();
                confirmAgain();
            }
        });
        dialog.setNoOnclickListener(new CustomerDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    //确认删除
    private void confirmAgain() {
        final CustomerDialog dialog=new CustomerDialog(getActivity());
        dialog.setDialogTitle("删除加密好友");
        dialog.setDialogConfirmText("确定");
        dialog.setDialogMessage("加密好友及聊天记录将一并删除,您需要再次添加好友才能聊天");
        dialog.setYesOnclickListener(new CustomerDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                dialog.dismiss();
            }
        });
        dialog.setNoOnclickListener(new CustomerDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    //信息传输加密
    private void showInfoTransmissionDialog() {
        JiamiDialog jiamiDialog=new JiamiDialog(getActivity());
        jiamiDialog.show();
    }
    //人生笔记密码的增删改查
    private void showLifeNotePwdDialog() {
        final LifeNotePwdSettingDialog dialog=new LifeNotePwdSettingDialog(getActivity());
        dialog.setNoOnclickListener(new LifeNotePwdSettingDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.setPwdOnclickListener(new LifeNotePwdSettingDialog.onSetPwdOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
                Intent intent=new Intent(getActivity(), SetLifeNotePwdActivity.class);
                startActivity(intent);
            }
        });
        dialog.setFixPwdOnclickListener(new LifeNotePwdSettingDialog.onFixPwdOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
                Intent intent=new Intent(getActivity(),FixLifeNotePwdActivity.class);
                startActivity(intent);
            }
        });
        dialog.setCanclePwdOnclickListener(new LifeNotePwdSettingDialog.onCanclePwdOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
                Intent intent=new Intent(getActivity(), CancleLifeNotePwdActivity.class);
                startActivity(intent);
            }
        });
        dialog.setFindPwdOnclickListener(new LifeNotePwdSettingDialog.onFindPwdOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
                Intent intent=new Intent(getActivity(), FindLifeNotePwdActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    //弹出开通vip的对话框
    private void showOpenVipDialog() {
        vipDialog = new VipDialog.Builder(getActivity())
                .setOpenVipListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      /*  Intent intent = new Intent(getActivity(), CreateVoVIPAccountActivityVo.class);
                        startActivity(intent);
                        dialog_vip.dismiss();*/
                    }
                }).setVipFunctionListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showVipFunctionDialog();
                        vipDialog.dismiss();
                    }
                }).create();
        vipDialog.show();
    }
    //弹出会员功能介绍的对话框
    private void showVipFunctionDialog() {
        final VipFunctionIntroduceDialog vipFunctionIntroduceDialog=new VipFunctionIntroduceDialog(getActivity());
        vipFunctionIntroduceDialog.setNoOnclickListener(new VipFunctionIntroduceDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                vipFunctionIntroduceDialog.dismiss();
            }
        });
        vipFunctionIntroduceDialog.setYesOnclickListener(new VipFunctionIntroduceDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                Toast.makeText(getActivity(), "11111111111111111", Toast.LENGTH_SHORT).show();
            }
        });
        vipFunctionIntroduceDialog.show();
    }
    //弹出输入人生笔记密码的dialog
    private void showLifeNoteDialog() {
        final LifeNotePwdDialog dialog=new LifeNotePwdDialog(getActivity());
        dialog.setYesOnclickListener(new LifeNotePwdDialog.onYesOnclickListener() {
            @Override
            public void onYesClick(String content) {
                if (TextUtils.isEmpty(content)){
                    Toast.makeText(getActivity(), "111111111", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNoOnclickListener(new LifeNotePwdDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if("nick_success".equals(action)){
                tv_nickName.setText((String)SPUtils.get(getActivity(),"nickName",""));
            }
        }
    }

}
