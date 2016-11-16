// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class onlineConcreteFragment$$ViewBinder<T extends com.mobiletrain.qqmusic.fragment.onlineConcreteFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492993, "field 'tvBackOnline'");
    target.tvBackOnline = finder.castView(view, 2131492993, "field 'tvBackOnline'");
    view = finder.findRequiredView(source, 2131492994, "field 'tvTopBar'");
    target.tvTopBar = finder.castView(view, 2131492994, "field 'tvTopBar'");
    view = finder.findRequiredView(source, 2131492995, "field 'lvTopConcrete'");
    target.lvTopConcrete = finder.castView(view, 2131492995, "field 'lvTopConcrete'");
  }

  @Override public void unbind(T target) {
    target.tvBackOnline = null;
    target.tvTopBar = null;
    target.lvTopConcrete = null;
  }
}
