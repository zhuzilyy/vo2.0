// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.main.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding<T extends HomeFragment> implements Unbinder {
  protected T target;

  private View view2131755922;

  private View view2131755923;

  @UiThread
  public HomeFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_add, "field 'iv_add' and method 'click'");
    target.iv_add = Utils.castView(view, R.id.iv_add, "field 'iv_add'", ImageView.class);
    view2131755922 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_search, "method 'click'");
    view2131755923 = view;
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

    target.iv_add = null;

    view2131755922.setOnClickListener(null);
    view2131755922 = null;
    view2131755923.setOnClickListener(null);
    view2131755923 = null;

    this.target = null;
  }
}
