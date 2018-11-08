// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.account;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebViweActivity_ViewBinding<T extends WebViweActivity> implements Unbinder {
  protected T target;

  private View view2131755346;

  @UiThread
  public WebViweActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.wv_webview = Utils.findRequiredViewAsType(source, R.id.wv_webview, "field 'wv_webview'", WebView.class);
    target.pb_webview = Utils.findRequiredViewAsType(source, R.id.pb_webview, "field 'pb_webview'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755346 = view;
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
    target.wv_webview = null;
    target.pb_webview = null;

    view2131755346.setOnClickListener(null);
    view2131755346 = null;

    this.target = null;
  }
}
