// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.account;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import com.zl.vo_.own.views.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  private View view2131755349;

  private View view2131755283;

  private View view2131755284;

  @UiThread
  public LoginActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.login_name = Utils.findRequiredViewAsType(source, R.id.login_name, "field 'login_name'", ClearEditText.class);
    target.login_pwd = Utils.findRequiredViewAsType(source, R.id.login_pwd, "field 'login_pwd'", ClearEditText.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755349 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_submit, "method 'click'");
    view2131755283 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_forgetpwd_tv, "method 'click'");
    view2131755284 = view;
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
    target.login_name = null;
    target.login_pwd = null;

    view2131755349.setOnClickListener(null);
    view2131755349 = null;
    view2131755283.setOnClickListener(null);
    view2131755283 = null;
    view2131755284.setOnClickListener(null);
    view2131755284 = null;

    this.target = null;
  }
}
