// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TopListAdapter$ViewHolder$$ViewBinder<T extends com.mobiletrain.qqmusic.adapter.TopListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492954, "field 'sdvCover'");
    target.sdvCover = finder.castView(view, 2131492954, "field 'sdvCover'");
    view = finder.findRequiredView(source, 2131493002, "field 'tvName'");
    target.tvName = finder.castView(view, 2131493002, "field 'tvName'");
    view = finder.findRequiredView(source, 2131493004, "field 'headphone'");
    target.headphone = finder.castView(view, 2131493004, "field 'headphone'");
    view = finder.findRequiredView(source, 2131493005, "field 'singleI'");
    target.singleI = view;
    view = finder.findRequiredView(source, 2131493006, "field 'singleII'");
    target.singleII = view;
    view = finder.findRequiredView(source, 2131493007, "field 'singleIII'");
    target.singleIII = view;
  }

  @Override public void unbind(T target) {
    target.sdvCover = null;
    target.tvName = null;
    target.headphone = null;
    target.singleI = null;
    target.singleII = null;
    target.singleIII = null;
  }
}
