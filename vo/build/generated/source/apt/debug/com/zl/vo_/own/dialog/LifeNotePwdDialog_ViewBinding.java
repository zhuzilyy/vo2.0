// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LifeNotePwdDialog_ViewBinding<T extends LifeNotePwdDialog> implements Unbinder {
  protected T target;

  private View view2131755796;

  private View view2131755483;

  @UiThread
  public LifeNotePwdDialog_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.et_pwd = Utils.findRequiredViewAsType(source, R.id.et_pwd, "field 'et_pwd'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_confirm, "method 'click'");
    view2131755796 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_cancel, "method 'click'");
    view2131755483 = view;
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
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.et_pwd = null;

    view2131755796.setOnClickListener(null);
    view2131755796 = null;
    view2131755483.setOnClickListener(null);
    view2131755483 = null;

    this.target = null;
  }
}
