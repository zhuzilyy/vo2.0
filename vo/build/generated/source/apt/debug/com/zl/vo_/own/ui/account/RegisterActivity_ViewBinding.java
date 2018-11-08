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

public class RegisterActivity_ViewBinding<T extends RegisterActivity> implements Unbinder {
  protected T target;

  private View view2131755346;

  private View view2131755307;

  @UiThread
  public RegisterActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'title'", TextView.class);
    target.register_nick = Utils.findRequiredViewAsType(source, R.id.register_nick, "field 'register_nick'", ClearEditText.class);
    target.register_phone = Utils.findRequiredViewAsType(source, R.id.register_phone, "field 'register_phone'", ClearEditText.class);
    target.register_code = Utils.findRequiredViewAsType(source, R.id.register_code, "field 'register_code'", ClearEditText.class);
    target.register_pwd = Utils.findRequiredViewAsType(source, R.id.register_pwd, "field 'register_pwd'", ClearEditText.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.register_submit, "method 'click'");
    view2131755307 = view;
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

    target.title = null;
    target.register_nick = null;
    target.register_phone = null;
    target.register_code = null;
    target.register_pwd = null;

    view2131755346.setOnClickListener(null);
    view2131755346 = null;
    view2131755307.setOnClickListener(null);
    view2131755307 = null;

    this.target = null;
  }
}
