// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.account;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Login_Register_Acitivity_ViewBinding<T extends Login_Register_Acitivity> implements Unbinder {
  protected T target;

  private View view2131755284;

  private View view2131755286;

  private View view2131755285;

  @UiThread
  public Login_Register_Acitivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_phone_login, "field 'll_phone_login' and method 'onClick'");
    target.ll_phone_login = Utils.castView(view, R.id.ll_phone_login, "field 'll_phone_login'", LinearLayout.class);
    view2131755284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_phone_register, "field 'll_phone_register' and method 'onClick'");
    target.ll_phone_register = Utils.castView(view, R.id.ll_phone_register, "field 'll_phone_register'", LinearLayout.class);
    view2131755286 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_wx_login, "field 'll_wx_login' and method 'onClick'");
    target.ll_wx_login = Utils.castView(view, R.id.ll_wx_login, "field 'll_wx_login'", LinearLayout.class);
    view2131755285 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_phone_login = null;
    target.ll_phone_register = null;
    target.ll_wx_login = null;

    view2131755284.setOnClickListener(null);
    view2131755284 = null;
    view2131755286.setOnClickListener(null);
    view2131755286 = null;
    view2131755285.setOnClickListener(null);
    view2131755285 = null;

    this.target = null;
  }
}
