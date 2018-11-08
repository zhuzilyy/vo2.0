// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VipActivity_ViewBinding<T extends VipActivity> implements Unbinder {
  protected T target;

  private View view2131755346;

  private View view2131755318;

  @UiThread
  public VipActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.lv_vip = Utils.findRequiredViewAsType(source, R.id.lv_vip, "field 'lv_vip'", ListView.class);
    target.wv_webview = Utils.findRequiredViewAsType(source, R.id.webView, "field 'wv_webview'", WebView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_submit, "method 'click'");
    view2131755318 = view;
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
    target.lv_vip = null;
    target.wv_webview = null;

    view2131755346.setOnClickListener(null);
    view2131755346 = null;
    view2131755318.setOnClickListener(null);
    view2131755318 = null;

    this.target = null;
  }
}
