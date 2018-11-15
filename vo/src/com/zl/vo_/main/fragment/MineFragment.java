package com.zl.vo_.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleTextView;
import com.cpiz.android.bubbleview.RelativePos;
import com.zl.vo_.R;
import com.zl.vo_.config.preference.Preferences;
import com.zl.vo_.login.LogoutHelper;
import com.zl.vo_.own.dialog.CustomerDialog;
import com.zl.vo_.own.dialog.JiamiDialog;
import com.zl.vo_.own.dialog.LifeNotePwdDialog;
import com.zl.vo_.own.dialog.LifeNotePwdSettingDialog;
import com.zl.vo_.own.dialog.PrivateFriendsDialog;
import com.zl.vo_.own.dialog.VipDialog;
import com.zl.vo_.own.dialog.VipFunctionIntroduceDialog;
import com.zl.vo_.own.ui.account.LoginActivity;
import com.zl.vo_.own.ui.mine.ui.CancleLifeNotePwdActivity;
import com.zl.vo_.own.ui.mine.ui.ChangePrivateFriendsActivity;
import com.zl.vo_.own.ui.mine.ui.FindLifeNotePwdActivity;
import com.zl.vo_.own.ui.mine.ui.FixLifeNotePwdActivity;
import com.zl.vo_.own.ui.mine.ui.LifeNoteActivity;
import com.zl.vo_.own.ui.mine.ui.SetLifeNotePwdActivity;
import com.zl.vo_.own.ui.mine.ui.SetPrivateFriendsActivity;
import com.zl.vo_.own.ui.mine.ui.SettingsActivity;
import com.zl.vo_.own.ui.mine.ui.UserInfoActivity;
import com.zl.vo_.own.ui.mine.ui.VipActivity;
import com.zl.vo_.own.util.SPUtils;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.cpiz.android.bubbleview.Utils.dp2px;

public class MineFragment extends MainTabFragment implements View.OnClickListener {
    private RelativeLayout rl_lifeNote,rl_setPrivacyFriends,rl_lifeNotePwdSetting,rl_infoTransmission,
            rl_deletePravcyFriends,rl_userInfo,rl_openVip,rl_setting;
    private VipDialog vipDialog;
    private ImageView iv_guidePravcyFriends,iv_guideLifeNotePwd,iv_guideInfoTrans,iv_guideDeletePravcyFriends;
    private CircleImageView iv_avatar;
    private TextView tv_nickName,tv_voNum;
    //12345
    @Override
    protected void onInit() {
        initViews();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCurrent();
    }


    private void initViews() {
        iv_guidePravcyFriends=findView(R.id.iv_guidePravcyFriends);
        iv_guideLifeNotePwd=findView(R.id.iv_guideLifeNotePwd);
        iv_guideInfoTrans=findView(R.id.iv_guideInfoTrans);
        iv_guideDeletePravcyFriends=findView(R.id.iv_guideDeletePravcyFriends);
        rl_lifeNote=findView(R.id.rl_lifeNote);
        rl_setPrivacyFriends=findView(R.id.rl_setPrivacyFriends);
        rl_lifeNotePwdSetting=findView(R.id.rl_lifeNotePwdSetting);
        rl_infoTransmission=findView(R.id.rl_infoTransmission);
        rl_deletePravcyFriends=findView(R.id.rl_deletePravcyFriends);
        rl_userInfo=findView(R.id.rl_userInfo);
        rl_openVip=findView(R.id.rl_openVip);
        rl_setting=findView(R.id.rl_setting);
        iv_avatar=findView(R.id.iv_avatar);
        tv_nickName=findView(R.id.tv_name);
        tv_voNum=findView(R.id.tv_voId);

        rl_lifeNote.setOnClickListener(this);
        rl_setPrivacyFriends.setOnClickListener(this);
        rl_lifeNotePwdSetting.setOnClickListener(this);
        rl_infoTransmission.setOnClickListener(this);
        rl_deletePravcyFriends.setOnClickListener(this);
        rl_userInfo.setOnClickListener(this);
        rl_openVip.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
        iv_guidePravcyFriends.setOnClickListener(this);
        iv_guideLifeNotePwd.setOnClickListener(this);
        iv_guideInfoTrans.setOnClickListener(this);
        iv_guideDeletePravcyFriends.setOnClickListener(this);
        setDefaultValue();
    }


    //设置初始化的数据
    private void setDefaultValue() {
        String avatar = (String) SPUtils.get(getActivity(), "avatar", "");
        String vo_code = (String) SPUtils.get(getActivity(), "vo_code", "");
        String nickName = (String) SPUtils.get(getActivity(), "nickName", "");
        String vo_code_can = (String) SPUtils.get(getActivity(), "vo_code_can", "");
        String vo_code_set = (String) SPUtils.get(getActivity(), "vo_code_set", "");
        Glide.with(getActivity()).load(avatar).into(iv_avatar);
        tv_nickName.setText("昵称:"+nickName);
        if (vo_code_can.equals("1")){
            tv_voNum.setText("vo号:"+vo_code);
        }else{
            tv_voNum.setText("vo号:"+vo_code_set);
        }
    }
    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()){
            //人生笔记
            case R.id.rl_lifeNote:
                intent=new Intent(getActivity(), LifeNoteActivity.class);
                startActivity(intent);
                break;
            //通用设置(进入设置界面)
            case R.id.rl_setting:
               // onLogout();
                intent=new Intent(getActivity(), SettingsActivity.class);
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
             //开通vip
            case R.id.rl_openVip:
                intent=new Intent(getActivity(), VipActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_guidePravcyFriends:
                showPop("一键开启专属人生笔记，您的私密空间为您永久保存", iv_guidePravcyFriends);
                break;
            case R.id.iv_guideLifeNotePwd:
                showPop("一键隐藏好友，一摇隐藏好友，一键显示隐私好友", iv_guideLifeNotePwd);
                break;
            case R.id.iv_guideInfoTrans:
                showPop("一键信息传输加密，2048位密码保护您的信息不被第三方窃取", iv_guideInfoTrans);
                break;
            case R.id.iv_guideDeletePravcyFriends:
                showPop(" 一键解除加密隐私好友，不留任何信息", iv_guideDeletePravcyFriends);
                break;
        }
    }
    //退出登录
    private void onLogout() {
        Preferences.saveUserToken("");
        // 清理缓存&注销监听
        LogoutHelper.logout();
        // 启动登录
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
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
                      /*
                      Intent intent = new Intent(getActivity(), CreateVoVIPAccountActivityVo.class);
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
    //显示提示框
    private void showPop(String tvStr, ImageView iv) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.simple_text_bubble, null);
        BubbleTextView mBubbleTextView = rootView.findViewById(R.id.popup_bubble);
        BubblePopupWindow mBubblePopupWindow = new BubblePopupWindow(rootView, mBubbleTextView);
        mBubblePopupWindow.setPadding(dp2px(50));
        mBubbleTextView.setText(tvStr);
        RelativePos mRelativePos = new RelativePos(RelativePos.CENTER_HORIZONTAL, RelativePos.CENTER_VERTICAL);
        mRelativePos.setHorizontalRelate(RelativePos.TO_LEFT_OF);
        showPopupBubble(mBubblePopupWindow, mRelativePos, 0, 0, iv);
    }
    private void showPopupBubble(BubblePopupWindow mBubblePopupWindow, RelativePos mRelativePos, int mMarginH, int mMarginV, ImageView iv) {
        if (getActivity().hasWindowFocus()) {
            mBubblePopupWindow.showArrowTo(iv, mRelativePos, mMarginH, mMarginV);
        }
    }
}
