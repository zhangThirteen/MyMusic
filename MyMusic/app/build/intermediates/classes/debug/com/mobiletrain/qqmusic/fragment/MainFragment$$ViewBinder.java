// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainFragment$$ViewBinder<T extends com.mobiletrain.qqmusic.fragment.MainFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492982, "field 'tvMenu' and method 'onTvMenuClick'");
    target.tvMenu = finder.castView(view, 2131492982, "field 'tvMenu'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onTvMenuClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492987, "field 'tvSearch' and method 'onTvSearchClick'");
    target.tvSearch = finder.castView(view, 2131492987, "field 'tvSearch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onTvSearchClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492983, "field 'rgTabs'");
    target.rgTabs = finder.castView(view, 2131492983, "field 'rgTabs'");
    view = finder.findRequiredView(source, 2131492988, "field 'vpFragments' and method 'onVpPageSelected'");
    target.vpFragments = finder.castView(view, 2131492988, "field 'vpFragments'");
    ((android.support.v4.view.ViewPager) view).setOnPageChangeListener(
      new android.support.v4.view.ViewPager.OnPageChangeListener() {
        @Override public void onPageSelected(
          int p0
        ) {
          target.onVpPageSelected(p0);
        }
        @Override public void onPageScrolled(
          int p0,
          float p1,
          int p2
        ) {
          
        }
        @Override public void onPageScrollStateChanged(
          int p0
        ) {
          
        }
      });
    view = finder.findRequiredView(source, 2131492984, "field 'rbMine'");
    target.rbMine = finder.castView(view, 2131492984, "field 'rbMine'");
    view = finder.findRequiredView(source, 2131492985, "field 'rbOnline'");
    target.rbOnline = finder.castView(view, 2131492985, "field 'rbOnline'");
    view = finder.findRequiredView(source, 2131492986, "field 'rbFind'");
    target.rbFind = finder.castView(view, 2131492986, "field 'rbFind'");
    view = finder.findRequiredView(source, 2131492989, "field 'vMask'");
    target.vMask = view;
  }

  @Override public void unbind(T target) {
    target.tvMenu = null;
    target.tvSearch = null;
    target.rgTabs = null;
    target.vpFragments = null;
    target.rbMine = null;
    target.rbOnline = null;
    target.rbFind = null;
    target.vMask = null;
  }
}
