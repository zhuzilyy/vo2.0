// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HelpFragment_ViewBinding<T extends HelpFragment> implements Unbinder {
  protected T target;

  private View view2131755548;

  private View view2131755550;

  private View view2131755551;

  @UiThread
  public HelpFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.ll_help = Utils.findRequiredViewAsType(source, R.id.ll_help, "field 'll_help'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_share, "method 'click'");
    view2131755548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_helpAndFeedback, "method 'click'");
    view2131755550 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_aboutVo, "method 'click'");
    view2131755551 = view;
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
    target.ll_help = null;

    view2131755548.setOnClickListener(null);
    view2131755548 = null;
    view2131755550.setOnClickListener(null);
    view2131755550 = null;
    view2131755551.setOnClickListener(null);
    view2131755551 = null;

    this.target = null;
  }
}
