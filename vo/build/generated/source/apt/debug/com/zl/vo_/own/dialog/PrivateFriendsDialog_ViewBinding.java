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

public class PrivateFriendsDialog_ViewBinding<T extends PrivateFriendsDialog> implements Unbinder {
  protected T target;

  private View view2131755921;

  private View view2131755922;

  private View view2131755490;

  @UiThread
  public PrivateFriendsDialog_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_setPrivateFriends, "method 'click'");
    view2131755921 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_addOrReducePrivateFriends, "method 'click'");
    view2131755922 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_cancel, "method 'click'");
    view2131755490 = view;
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

    view2131755921.setOnClickListener(null);
    view2131755921 = null;
    view2131755922.setOnClickListener(null);
    view2131755922 = null;
    view2131755490.setOnClickListener(null);
    view2131755490 = null;

    this.target = null;
  }
}
