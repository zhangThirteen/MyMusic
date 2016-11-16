// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LocalMusicAdapter$ViewHolder$$ViewBinder<T extends com.mobiletrain.qqmusic.adapter.LocalMusicAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492961, "field 'tvMore'");
    target.tvMore = finder.castView(view, 2131492961, "field 'tvMore'");
    view = finder.findRequiredView(source, 2131493001, "field 'tvPlus'");
    target.tvPlus = finder.castView(view, 2131493001, "field 'tvPlus'");
    view = finder.findRequiredView(source, 2131493002, "field 'tvName'");
    target.tvName = finder.castView(view, 2131493002, "field 'tvName'");
    view = finder.findRequiredView(source, 2131492975, "field 'tvDownloaded'");
    target.tvDownloaded = finder.castView(view, 2131492975, "field 'tvDownloaded'");
    view = finder.findRequiredView(source, 2131493003, "field 'tvInfo'");
    target.tvInfo = finder.castView(view, 2131493003, "field 'tvInfo'");
  }

  @Override public void unbind(T target) {
    target.tvMore = null;
    target.tvPlus = null;
    target.tvName = null;
    target.tvDownloaded = null;
    target.tvInfo = null;
  }
}
