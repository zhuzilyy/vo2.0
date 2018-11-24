// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  private View view2131755291;

  private View view2131755295;

  private View view2131755298;

  private View view2131755302;

  @UiThread
  public MainActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.iv_msg = Utils.findRequiredViewAsType(source, R.id.iv_msg, "field 'iv_msg'", ImageView.class);
    target.iv_friends = Utils.findRequiredViewAsType(source, R.id.iv_friends, "field 'iv_friends'", ImageView.class);
    target.iv_find = Utils.findRequiredViewAsType(source, R.id.iv_find, "field 'iv_find'", ImageView.class);
    target.iv_mine = Utils.findRequiredViewAsType(source, R.id.iv_mine, "field 'iv_mine'", ImageView.class);
    target.tv_message = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'tv_message'", TextView.class);
    target.tv_friends = Utils.findRequiredViewAsType(source, R.id.tv_friends, "field 'tv_friends'", TextView.class);
    target.tv_find = Utils.findRequiredViewAsType(source, R.id.tv_find, "field 'tv_find'", TextView.class);
    target.tv_mine = Utils.findRequiredViewAsType(source, R.id.tv_mine, "field 'tv_mine'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_msg, "method 'click'");
    view2131755291 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_frineds, "method 'click'");
    view2131755295 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_find, "method 'click'");
    view2131755298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_mine, "method 'click'");
    view2131755302 = view;
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

    target.iv_msg = null;
    target.iv_friends = null;
    target.iv_find = null;
    target.iv_mine = null;
    target.tv_message = null;
    target.tv_friends = null;
    target.tv_find = null;
    target.tv_mine = null;

    view2131755291.setOnClickListener(null);
    view2131755291 = null;
    view2131755295.setOnClickListener(null);
    view2131755295 = null;
    view2131755298.setOnClickListener(null);
    view2131755298 = null;
    view2131755302.setOnClickListener(null);
    view2131755302 = null;

    this.target = null;
  }
}
