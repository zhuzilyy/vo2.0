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

public class LifeNotePwdSettingDialog_ViewBinding<T extends LifeNotePwdSettingDialog> implements Unbinder {
  protected T target;

  private View view2131755480;

  private View view2131755901;

  private View view2131755902;

  private View view2131755904;

  private View view2131755905;

  @UiThread
  public LifeNotePwdSettingDialog_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_cancel, "method 'click'");
    view2131755480 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_setPwd, "method 'click'");
    view2131755901 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_fixPwd, "method 'click'");
    view2131755902 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_canclePwd, "method 'click'");
    view2131755904 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_findPwd, "method 'click'");
    view2131755905 = view;
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

    view2131755480.setOnClickListener(null);
    view2131755480 = null;
    view2131755901.setOnClickListener(null);
    view2131755901 = null;
    view2131755902.setOnClickListener(null);
    view2131755902 = null;
    view2131755904.setOnClickListener(null);
    view2131755904 = null;
    view2131755905.setOnClickListener(null);
    view2131755905 = null;

    this.target = null;
  }
}
