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

public class UserInfoActivity_ViewBinding<T extends UserInfoActivity> implements Unbinder {
  protected T target;

  private View view2131755356;

  private View view2131755376;

  private View view2131755379;

  private View view2131755381;

  private View view2131755241;

  private View view2131755382;

  @UiThread
  public UserInfoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_nickName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_nickName'", TextView.class);
    target.tv_voNum = Utils.findRequiredViewAsType(source, R.id.tv_voId, "field 'tv_voNum'", TextView.class);
    target.iv_avatar = Utils.findRequiredViewAsType(source, R.id.iv_avatar, "field 'iv_avatar'", CircleImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755356 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_nickName, "method 'click'");
    view2131755376 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_voId, "method 'click'");
    view2131755379 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_qrcode, "method 'click'");
    view2131755381 = view;
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
    view = Utils.findRequiredView(source, R.id.rl_more, "method 'click'");
    view2131755382 = view;
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
    target.tv_nickName = null;
    target.tv_voNum = null;
    target.iv_avatar = null;

    view2131755356.setOnClickListener(null);
    view2131755356 = null;
    view2131755376.setOnClickListener(null);
    view2131755376 = null;
    view2131755379.setOnClickListener(null);
    view2131755379 = null;
    view2131755381.setOnClickListener(null);
    view2131755381 = null;
    view2131755241.setOnClickListener(null);
    view2131755241 = null;
    view2131755382.setOnClickListener(null);
    view2131755382 = null;

    this.target = null;
  }
}
