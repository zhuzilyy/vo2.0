// Generated code from Butter Knife. Do not modify!
package com.zl.vo_.own.ui.find.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zl.vo_.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanCaptureActivity_ViewBinding<T extends ScanCaptureActivity> implements Unbinder {
  protected T target;

  private View view2131755356;

  @UiThread
  public ScanCaptureActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.scanPreview = Utils.findRequiredViewAsType(source, R.id.capture_preview, "field 'scanPreview'", FrameLayout.class);
    target.scanResult = Utils.findRequiredViewAsType(source, R.id.capture_scan_result, "field 'scanResult'", TextView.class);
    target.scanContainer = Utils.findRequiredViewAsType(source, R.id.capture_container, "field 'scanContainer'", RelativeLayout.class);
    target.scanCropView = Utils.findRequiredViewAsType(source, R.id.capture_crop_view, "field 'scanCropView'", RelativeLayout.class);
    target.scanLine = Utils.findRequiredViewAsType(source, R.id.capture_scan_line, "field 'scanLine'", ImageView.class);
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
    target.scanPreview = null;
    target.scanResult = null;
    target.scanContainer = null;
    target.scanCropView = null;
    target.scanLine = null;

    view2131755356.setOnClickListener(null);
    view2131755356 = null;

    this.target = null;
  }
}
