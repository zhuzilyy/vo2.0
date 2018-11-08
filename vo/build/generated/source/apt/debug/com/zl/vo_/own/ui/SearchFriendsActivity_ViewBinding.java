// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchFriendsActivity_ViewBinding<T extends SearchFriendsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SearchFriendsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.et_search = Utils.findRequiredViewAsType(source, R.id.et_search, "field 'et_search'", EditText.class);
    target.tv_search_name = Utils.findRequiredViewAsType(source, R.id.tv_search_name, "field 'tv_search_name'", TextView.class);
    target.ll_search_noUser = Utils.findRequiredViewAsType(source, R.id.ll_search_noUser, "field 'll_search_noUser'", LinearLayout.class);
    target.ll_search_result = Utils.findRequiredViewAsType(source, R.id.ll_search_result, "field 'll_search_result'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.et_search = null;
    target.tv_search_name = null;
    target.ll_search_noUser = null;
    target.ll_search_result = null;

    this.target = null;
  }
}
