// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.netease.nim.uikit.common.ui.widget.SwitchButton;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatSettingActivity_ViewBinding<T extends ChatSettingActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ChatSettingActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'title'", TextView.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'back'", ImageView.class);
    target.switch_Usehandset = Utils.findRequiredViewAsType(source, R.id.switch_Usehandset, "field 'switch_Usehandset'", SwitchButton.class);
    target.clearAllmsg_re = Utils.findRequiredViewAsType(source, R.id.clearAllmsg_re, "field 'clearAllmsg_re'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.title = null;
    target.back = null;
    target.switch_Usehandset = null;
    target.clearAllmsg_re = null;

    this.target = null;
  }
}
