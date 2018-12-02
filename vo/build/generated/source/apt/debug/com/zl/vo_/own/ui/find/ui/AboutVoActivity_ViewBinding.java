// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.find.ui;

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

public class AboutVoActivity_ViewBinding<T extends AboutVoActivity> implements Unbinder {
  protected T target;

  private View view2131755356;

  private View view2131755223;

  private View view2131755225;

  @UiThread
  public AboutVoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755356 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_voIntroduce, "method 'click'");
    view2131755223 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_update, "method 'click'");
    view2131755225 = view;
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

    target.tv_title = null;

    view2131755356.setOnClickListener(null);
    view2131755356 = null;
    view2131755223.setOnClickListener(null);
    view2131755223 = null;
    view2131755225.setOnClickListener(null);
    view2131755225 = null;

    this.target = null;
  }
}
