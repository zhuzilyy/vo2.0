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

  private View view2131755370;

  private View view2131755371;

  private View view2131755372;

  private View view2131755373;

  private View view2131755374;

  private View view2131755356;

  @UiThread
  public SettingsActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.re_newmsgNotify, "field 're_newmsgNotify' and method 'onClick'");
    target.re_newmsgNotify = Utils.castView(view, R.id.re_newmsgNotify, "field 're_newmsgNotify'", RelativeLayout.class);
    view2131755370 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_do_not_disturb_mode, "field 're_do_not_disturb_mode' and method 'onClick'");
    target.re_do_not_disturb_mode = Utils.castView(view, R.id.re_do_not_disturb_mode, "field 're_do_not_disturb_mode'", RelativeLayout.class);
    view2131755371 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_chat, "field 're_chat' and method 'onClick'");
    target.re_chat = Utils.castView(view, R.id.re_chat, "field 're_chat'", RelativeLayout.class);
    view2131755372 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_currency, "field 're_currency' and method 'onClick'");
    target.re_currency = Utils.castView(view, R.id.re_currency, "field 're_currency'", RelativeLayout.class);
    view2131755373 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_quite, "field 're_quite' and method 'onClick'");
    target.re_quite = Utils.castView(view, R.id.re_quite, "field 're_quite'", RelativeLayout.class);
    view2131755374 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'back' and method 'onClick'");
    target.back = Utils.castView(view, R.id.iv_back, "field 'back'", ImageView.class);
    view2131755356 = view;
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

    view2131755370.setOnClickListener(null);
    view2131755370 = null;
    view2131755371.setOnClickListener(null);
    view2131755371 = null;
    view2131755372.setOnClickListener(null);
    view2131755372 = null;
    view2131755373.setOnClickListener(null);
    view2131755373 = null;
    view2131755374.setOnClickListener(null);
    view2131755374 = null;
    view2131755356.setOnClickListener(null);
    view2131755356 = null;

    this.target = null;
  }
}
