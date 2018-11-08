// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.account;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashActivity_ViewBinding<T extends SplashActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SplashActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_splash = Utils.findRequiredViewAsType(source, R.id.iv_splash, "field 'iv_splash'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_splash = null;

    this.target = null;
  }
}
