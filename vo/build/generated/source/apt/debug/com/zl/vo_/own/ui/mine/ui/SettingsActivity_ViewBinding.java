// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingsActivity_ViewBinding<T extends SettingsActivity> implements Unbinder {
  protected T target;

  private View view2131755363;

  private View view2131755364;

  private View view2131755365;

  private View view2131755366;

  private View view2131755367;

  private View view2131755349;

  @UiThread
  public SettingsActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.re_newmsgNotify, "field 're_newmsgNotify' and method 'onClick'");
    target.re_newmsgNotify = Utils.castView(view, R.id.re_newmsgNotify, "field 're_newmsgNotify'", RelativeLayout.class);
    view2131755363 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_do_not_disturb_mode, "field 're_do_not_disturb_mode' and method 'onClick'");
    target.re_do_not_disturb_mode = Utils.castView(view, R.id.re_do_not_disturb_mode, "field 're_do_not_disturb_mode'", RelativeLayout.class);
    view2131755364 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_chat, "field 're_chat' and method 'onClick'");
    target.re_chat = Utils.castView(view, R.id.re_chat, "field 're_chat'", RelativeLayout.class);
    view2131755365 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_currency, "field 're_currency' and method 'onClick'");
    target.re_currency = Utils.castView(view, R.id.re_currency, "field 're_currency'", RelativeLayout.class);
    view2131755366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_quite, "field 're_quite' and method 'onClick'");
    target.re_quite = Utils.castView(view, R.id.re_quite, "field 're_quite'", RelativeLayout.class);
    view2131755367 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'back' and method 'onClick'");
    target.back = Utils.castView(view, R.id.iv_back, "field 'back'", ImageView.class);
    view2131755349 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.re_newmsgNotify = null;
    target.re_do_not_disturb_mode = null;
    target.re_chat = null;
    target.re_currency = null;
    target.re_quite = null;
    target.back = null;
    target.title = null;

    view2131755363.setOnClickListener(null);
    view2131755363 = null;
    view2131755364.setOnClickListener(null);
    view2131755364 = null;
    view2131755365.setOnClickListener(null);
    view2131755365 = null;
    view2131755366.setOnClickListener(null);
    view2131755366 = null;
    view2131755367.setOnClickListener(null);
    view2131755367 = null;
    view2131755349.setOnClickListener(null);
    view2131755349 = null;

    this.target = null;
  }
}
