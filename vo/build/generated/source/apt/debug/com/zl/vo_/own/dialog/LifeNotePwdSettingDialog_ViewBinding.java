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

  private View view2131755490;

  private View view2131755916;

  private View view2131755917;

  private View view2131755919;

  private View view2131755920;

  @UiThread
  public LifeNotePwdSettingDialog_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_cancel, "method 'click'");
    view2131755490 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_setPwd, "method 'click'");
    view2131755916 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_fixPwd, "method 'click'");
    view2131755917 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_canclePwd, "method 'click'");
    view2131755919 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_findPwd, "method 'click'");
    view2131755920 = view;
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

    view2131755490.setOnClickListener(null);
    view2131755490 = null;
    view2131755916.setOnClickListener(null);
    view2131755916 = null;
    view2131755917.setOnClickListener(null);
    view2131755917 = null;
    view2131755919.setOnClickListener(null);
    view2131755919 = null;
    view2131755920.setOnClickListener(null);
    view2131755920 = null;

    this.target = null;
  }
}
