package com.zl.vo_.team.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.vo_.R;
import com.netease.nim.uikit.common.activity.ToolBarOptions;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.common.ui.widget.ClearableEditTextWithIcon;
import com.netease.nim.uikit.api.wrapper.NimToolBarOptions;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.model.Team;
import com.zl.vo_.own.util.WeiboDialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索加入群组界面
 * Created by hzxuwen on 2015/3/20.
 */
public class AdvancedTeamSearchActivity extends UI {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;

    private ClearableEditTextWithIcon searchEditText;

    public static final void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AdvancedTeamSearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nim_advanced_team_search_activity);
        ButterKnife.bind(this);
        setTitle(R.string.search_join_team);

        ToolBarOptions options = new NimToolBarOptions();
        options.titleId = R.string.search_join_team;
        setToolBar(R.id.toolbar, options);

        findViews();
        initActionbar();
        initData();
    }

    private void initData() {
        tv_title.setText("搜索加入群组");
        tv_right.setText("搜索");
        tv_right.setVisibility(View.VISIBLE);
    }
    @OnClick({R.id.iv_back,R.id.tv_right})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                if (TextUtils.isEmpty(searchEditText.getText().toString())) {
                    Toast.makeText(AdvancedTeamSearchActivity.this, R.string.not_allow_empty, Toast.LENGTH_SHORT).show();
                } else {
                    queryTeamById();
                }
                break;
        }
    }

    private void findViews() {
        searchEditText = findViewById(R.id.team_search_edittext);
        searchEditText.setDeleteImage(R.drawable.nim_grey_delete_icon);
    }

    private void initActionbar() {
        TextView toolbarView = findView(R.id.action_bar_right_clickable_textview);
        toolbarView.setText(R.string.search);
        toolbarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(searchEditText.getText().toString())) {
                    Toast.makeText(AdvancedTeamSearchActivity.this, R.string.not_allow_empty, Toast.LENGTH_SHORT).show();
                } else {
                    queryTeamById();
                }
            }
        });
    }

    private void queryTeamById() {
        final Dialog dialog = WeiboDialogUtils.createLoadingDialog(this, "正在搜索");
        dialog.show();
        NIMClient.getService(TeamService.class).searchTeam(searchEditText.getText().toString()).setCallback(new RequestCallback<Team>() {
            @Override
            public void onSuccess(Team team) {
                dialog.dismiss();
                updateTeamInfo(team);
            }

            @Override
            public void onFailed(int code) {
                dialog.dismiss();
                if (code == 803) {
                    Toast.makeText(AdvancedTeamSearchActivity.this, R.string.team_number_not_exist, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AdvancedTeamSearchActivity.this, "search team failed: " + code, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onException(Throwable exception) {
                dialog.dismiss();
                Toast.makeText(AdvancedTeamSearchActivity.this, "search team exception：" + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 搜索群组成功的回调
     *
     * @param team 群
     */
    private void updateTeamInfo(Team team) {
        if (team.getId().equals(searchEditText.getText().toString())) {
            AdvancedTeamJoinActivity.start(AdvancedTeamSearchActivity.this, team.getId());
        }

    }
}
