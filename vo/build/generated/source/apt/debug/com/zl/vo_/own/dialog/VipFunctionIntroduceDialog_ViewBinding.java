// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VipFunctionIntroduceDialog_ViewBinding<T extends VipFunctionIntroduceDialog> implements Unbinder {
  protected T target;

  private View view2131755792;

  private View view2131755480;

  @UiThread
  public VipFunctionIntroduceDialog_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_confirm, "method 'click'");
    view2131755792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_cancel, "method 'click'");
    view2131755480 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131755792.setOnClickListener(null);
    view2131755792 = null;
    view2131755480.setOnClickListener(null);
    view2131755480 = null;

    this.target = null;
  }
}
