// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import com.zl.vo_.own.views.EaseSwitchButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewMsgSettingsActivity_ViewBinding<T extends NewMsgSettingsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public NewMsgSettingsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'title'", TextView.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'back'", ImageView.class);
    target.switch_notification = Utils.findRequiredViewAsType(source, R.id.switch_notification, "field 'switch_notification'", EaseSwitchButton.class);
    target.switch_notify_details = Utils.findRequiredViewAsType(source, R.id.notifySwitch_details, "field 'switch_notify_details'", EaseSwitchButton.class);
    target.switch_notify_voice = Utils.findRequiredViewAsType(source, R.id.notifySwitch_voice, "field 'switch_notify_voice'", EaseSwitchButton.class);
    target.switch_notify_vibrate = Utils.findRequiredViewAsType(source, R.id.notifySwitch_vibrate, "field 'switch_notify_vibrate'", EaseSwitchButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.title = null;
    target.back = null;
    target.switch_notification = null;
    target.switch_notify_details = null;
    target.switch_notify_voice = null;
    target.switch_notify_vibrate = null;

    this.target = null;
  }
}
