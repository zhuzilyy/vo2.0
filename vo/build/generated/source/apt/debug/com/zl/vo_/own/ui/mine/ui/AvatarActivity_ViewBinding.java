// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AvatarActivity_ViewBinding<T extends AvatarActivity> implements Unbinder {
  protected T target;

  private View view2131755648;

  private View view2131755349;

  private View view2131755241;

  @UiThread
  public AvatarActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_right, "field 'tv_right' and method 'click'");
    target.tv_right = Utils.castView(view, R.id.tv_right, "field 'tv_right'", TextView.class);
    view2131755648 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    target.iv_avatar = Utils.findRequiredViewAsType(source, R.id.iv_avatar, "field 'iv_avatar'", CircleImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755349 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_avatar, "method 'click'");
    view2131755241 = view;
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
    target.tv_right = null;
    target.iv_avatar = null;

    view2131755648.setOnClickListener(null);
    view2131755648 = null;
    view2131755349.setOnClickListener(null);
    view2131755349 = null;
    view2131755241.setOnClickListener(null);
    view2131755241 = null;

    this.target = null;
  }
}
