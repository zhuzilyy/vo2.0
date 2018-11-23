// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.fragment;

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

public class MineFragment_ViewBinding<T extends MineFragment> implements Unbinder {
  protected T target;

  private View view2131755926;

  private View view2131755557;

  private View view2131755563;

  private View view2131755566;

  private View view2131755569;

  private View view2131755572;

  private View view2131755556;

  private View view2131755561;

  @UiThread
  public MineFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_add, "field 'iv_add' and method 'click'");
    target.iv_add = Utils.castView(view, R.id.iv_add, "field 'iv_add'", ImageView.class);
    view2131755926 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_lifeNote, "method 'click'");
    view2131755557 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_setPrivacyFriends, "method 'click'");
    view2131755563 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_lifeNotePwdSetting, "method 'click'");
    view2131755566 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_infoTransmission, "method 'click'");
    view2131755569 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_deletePravcyFriends, "method 'click'");
    view2131755572 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_userInfo, "method 'click'");
    view2131755556 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_openVip, "method 'click'");
    view2131755561 = view;
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
    target.iv_add = null;

    view2131755926.setOnClickListener(null);
    view2131755926 = null;
    view2131755557.setOnClickListener(null);
    view2131755557 = null;
    view2131755563.setOnClickListener(null);
    view2131755563 = null;
    view2131755566.setOnClickListener(null);
    view2131755566 = null;
    view2131755569.setOnClickListener(null);
    view2131755569 = null;
    view2131755572.setOnClickListener(null);
    view2131755572 = null;
    view2131755556.setOnClickListener(null);
    view2131755556 = null;
    view2131755561.setOnClickListener(null);
    view2131755561 = null;

    this.target = null;
  }
}
