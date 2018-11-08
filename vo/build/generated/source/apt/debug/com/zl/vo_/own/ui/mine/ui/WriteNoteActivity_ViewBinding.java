// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WriteNoteActivity_ViewBinding<T extends WriteNoteActivity> implements Unbinder {
  protected T target;

  @UiThread
  public WriteNoteActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_right = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tv_right'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_title = null;
    target.tv_right = null;
    target.recyclerView = null;

    this.target = null;
  }
}
