// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.mine.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QrCodeActivity_ViewBinding<T extends QrCodeActivity> implements Unbinder {
  protected T target;

  private View view2131755356;

  @UiThread
  public QrCodeActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.iv__head = Utils.findRequiredViewAsType(source, R.id.iv__head, "field 'iv__head'", CircleImageView.class);
    target.iv_sex = Utils.findRequiredViewAsType(source, R.id.iv_sex, "field 'iv_sex'", ImageView.class);
    target.code_image = Utils.findRequiredViewAsType(source, R.id.code_image, "field 'code_image'", ImageView.class);
    target.et_nick = Utils.findRequiredViewAsType(source, R.id.ewm_nick, "field 'et_nick'", TextView.class);
    target.et_address = Utils.findRequiredViewAsType(source, R.id.ewm_address, "field 'et_address'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'click'");
    view2131755356 = view;
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
    target.iv__head = null;
    target.iv_sex = null;
    target.code_image = null;
    target.et_nick = null;
    target.et_address = null;

    view2131755356.setOnClickListener(null);
    view2131755356 = null;

    this.target = null;
  }
}
