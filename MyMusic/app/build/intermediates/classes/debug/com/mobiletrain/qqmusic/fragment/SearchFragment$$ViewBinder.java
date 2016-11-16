// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SearchFragment$$ViewBinder<T extends com.mobiletrain.qqmusic.fragment.SearchFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492960, "field 'tvBack' and method 'onTvBackClick'");
    target.tvBack = finder.castView(view, 2131492960, "field 'tvBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onTvBackClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492996, "field 'etSearch'");
    target.etSearch = finder.castView(view, 2131492996, "field 'etSearch'");
    view = finder.findRequiredView(source, 2131492987, "field 'tvSearch' and method 'onClick'");
    target.tvSearch = finder.castView(view, 2131492987, "field 'tvSearch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492997, "field 'lvRecent'");
    target.lvRecent = finder.castView(view, 2131492997, "field 'lvRecent'");
    view = finder.findRequiredView(source, 2131492998, "field 'lvResult' and method 'onItemClick'");
    target.lvResult = finder.castView(view, 2131492998, "field 'lvResult'");
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
    target.tvBack = null;
    target.etSearch = null;
    target.tvSearch = null;
    target.lvRecent = null;
    target.lvResult = null;
  }
}
