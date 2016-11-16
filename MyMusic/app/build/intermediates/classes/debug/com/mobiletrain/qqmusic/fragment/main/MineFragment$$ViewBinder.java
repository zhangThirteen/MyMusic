// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic.fragment.main;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MineFragment$$ViewBinder<T extends com.mobiletrain.qqmusic.fragment.main.MineFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492991, "field 'lvLocalMusics' and method 'onItemClick'");
    target.lvLocalMusics = finder.castView(view, 2131492991, "field 'lvLocalMusics'");
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onItemClick(p0, p1, p2, p3);
        }
      });
  }

  @Override public void unbind(T target) {
    target.lvLocalMusics = null;
  }
}
