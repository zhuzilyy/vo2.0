// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageAdapter$FooterViewHolder_ViewBinding<T extends MessageAdapter.FooterViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MessageAdapter$FooterViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.tv_footer = Utils.findRequiredViewAsType(source, R.id.tv_footer, "field 'tv_footer'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_footer = null;

    this.target = null;
  }
}
