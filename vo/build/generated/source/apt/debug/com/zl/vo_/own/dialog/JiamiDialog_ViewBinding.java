// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JiamiDialog_ViewBinding<T extends JiamiDialog> implements Unbinder {
  protected T target;

  @UiThread
  public JiamiDialog_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_jiami = Utils.findRequiredViewAsType(source, R.id.iv_jiami, "field 'iv_jiami'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_jiami = null;

    this.target = null;
  }
}
