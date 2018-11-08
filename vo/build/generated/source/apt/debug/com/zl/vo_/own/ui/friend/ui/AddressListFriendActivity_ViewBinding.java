// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.friend.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddressListFriendActivity_ViewBinding<T extends AddressListFriendActivity> implements Unbinder {
  protected T target;

  private View view2131755346;

  private View view2131755234;

  @UiThread
  public AddressListFriendActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.container = Utils.findRequiredViewAsType(source, R.id.container, "field 'container'", RelativeLayout.class);
    target.tv_right = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tv_right'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.wx_share_ll, "method 'click'");
    view2131755234 = view;
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
    target.container = null;
    target.tv_right = null;

    view2131755346.setOnClickListener(null);
    view2131755346 = null;
    view2131755234.setOnClickListener(null);
    view2131755234 = null;

    this.target = null;
  }
}
