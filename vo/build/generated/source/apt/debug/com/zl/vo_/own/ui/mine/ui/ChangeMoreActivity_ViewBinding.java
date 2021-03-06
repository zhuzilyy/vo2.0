// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

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

public class ChangeMoreActivity_ViewBinding<T extends ChangeMoreActivity> implements Unbinder {
  protected T target;

  private View view2131755356;

  private View view2131755252;

  private View view2131755256;

  private View view2131755258;

  @UiThread
  public ChangeMoreActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_right = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tv_right'", TextView.class);
    target.tv_sign = Utils.findRequiredViewAsType(source, R.id.tv_sign, "field 'tv_sign'", TextView.class);
    target.tv_address = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tv_address'", TextView.class);
    target.tv_sex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'tv_sex'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755356 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_sex, "method 'click'");
    view2131755252 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_address, "method 'click'");
    view2131755256 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_sign, "method 'click'");
    view2131755258 = view;
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
    target.tv_right = null;
    target.tv_sign = null;
    target.tv_address = null;
    target.tv_sex = null;

    view2131755356.setOnClickListener(null);
    view2131755356 = null;
    view2131755252.setOnClickListener(null);
    view2131755252 = null;
    view2131755256.setOnClickListener(null);
    view2131755256 = null;
    view2131755258.setOnClickListener(null);
    view2131755258 = null;

    this.target = null;
  }
}
