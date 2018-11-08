// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LifeNoteActivity_ViewBinding<T extends LifeNoteActivity> implements Unbinder {
  protected T target;

  private View view2131755346;

  @UiThread
  public LifeNoteActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.rv_lifeNote = Utils.findRequiredViewAsType(source, R.id.rv_lifeNote, "field 'rv_lifeNote'", RecyclerView.class);
    target.swipe_refresh_layout = Utils.findRequiredViewAsType(source, R.id.swipe_refresh_layout, "field 'swipe_refresh_layout'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'onClick'");
    view2131755346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_title = null;
    target.rv_lifeNote = null;
    target.swipe_refresh_layout = null;

    view2131755346.setOnClickListener(null);
    view2131755346 = null;

    this.target = null;
  }
}
