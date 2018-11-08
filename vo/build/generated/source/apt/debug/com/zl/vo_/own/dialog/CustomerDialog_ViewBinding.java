// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomerDialog_ViewBinding<T extends CustomerDialog> implements Unbinder {
  protected T target;

  private View view2131755483;

  private View view2131755480;

  @UiThread
  public CustomerDialog_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_dialogTitle = Utils.findRequiredViewAsType(source, R.id.tv_dialogTitle, "field 'tv_dialogTitle'", TextView.class);
    target.tv_dialogContent = Utils.findRequiredViewAsType(source, R.id.tv_dialogContent, "field 'tv_dialogContent'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_dialog_confrim, "field 'tv_dialogConfrim' and method 'click'");
    target.tv_dialogConfrim = Utils.castView(view, R.id.tv_dialog_confrim, "field 'tv_dialogConfrim'", TextView.class);
    view2131755483 = view;
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
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_dialogTitle = null;
    target.tv_dialogContent = null;
    target.tv_dialogConfrim = null;

    view2131755483.setOnClickListener(null);
    view2131755483 = null;
    view2131755480.setOnClickListener(null);
    view2131755480 = null;

    this.target = null;
  }
}
