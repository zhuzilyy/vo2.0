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

  private View view2131755346;

  private View view2131755366;

  private View view2131755369;

  private View view2131755371;

  private View view2131755238;

  private View view2131755372;

  @UiThread
  public UserInfoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_nickName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_nickName'", TextView.class);
    target.tv_voNum = Utils.findRequiredViewAsType(source, R.id.tv_voId, "field 'tv_voNum'", TextView.class);
    target.iv_avatar = Utils.findRequiredViewAsType(source, R.id.iv_avatar, "field 'iv_avatar'", CircleImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_nickName, "method 'click'");
    view2131755366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_voId, "method 'click'");
    view2131755369 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_qrcode, "method 'click'");
    view2131755371 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_avatar, "method 'click'");
    view2131755238 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_more, "method 'click'");
    view2131755372 = view;
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

    view2131755346.setOnClickListener(null);
    view2131755346 = null;
    view2131755366.setOnClickListener(null);
    view2131755366 = null;
    view2131755369.setOnClickListener(null);
    view2131755369 = null;
    view2131755371.setOnClickListener(null);
    view2131755371 = null;
    view2131755238.setOnClickListener(null);
    view2131755238 = null;
    view2131755372.setOnClickListener(null);
    view2131755372 = null;

    this.target = null;
  }
}
