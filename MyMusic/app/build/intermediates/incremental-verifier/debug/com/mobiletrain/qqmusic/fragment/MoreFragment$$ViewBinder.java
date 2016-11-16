// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MoreFragment$$ViewBinder<T extends com.mobiletrain.qqmusic.fragment.MoreFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492960, "field 'tvBack' and method 'onTvBlackClick'");
    target.tvBack = finder.castView(view, 2131492960, "field 'tvBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onTvBlackClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
  }
}
