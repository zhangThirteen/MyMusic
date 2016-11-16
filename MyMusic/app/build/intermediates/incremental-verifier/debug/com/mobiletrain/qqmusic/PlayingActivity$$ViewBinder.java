// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PlayingActivity$$ViewBinder<T extends com.mobiletrain.qqmusic.PlayingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492954, "field 'sdvCover'");
    target.sdvCover = finder.castView(view, 2131492954, "field 'sdvCover'");
    view = finder.findRequiredView(source, 2131492960, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131492960, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131492955, "field 'tvSongName'");
    target.tvSongName = finder.castView(view, 2131492955, "field 'tvSongName'");
    view = finder.findRequiredView(source, 2131492961, "field 'tvMore'");
    target.tvMore = finder.castView(view, 2131492961, "field 'tvMore'");
    view = finder.findRequiredView(source, 2131492962, "field 'lrcView'");
    target.lrcView = finder.castView(view, 2131492962, "field 'lrcView'");
    view = finder.findRequiredView(source, 2131492963, "field 'textProgressBar'");
    target.textProgressBar = finder.castView(view, 2131492963, "field 'textProgressBar'");
    view = finder.findRequiredView(source, 2131492964, "field 'llDots'");
    target.llDots = finder.castView(view, 2131492964, "field 'llDots'");
    view = finder.findRequiredView(source, 2131492966, "field 'tvCurrentTime'");
    target.tvCurrentTime = finder.castView(view, 2131492966, "field 'tvCurrentTime'");
    view = finder.findRequiredView(source, 2131492967, "field 'seekbar'");
    target.seekbar = finder.castView(view, 2131492967, "field 'seekbar'");
    view = finder.findRequiredView(source, 2131492968, "field 'tvTotalTime'");
    target.tvTotalTime = finder.castView(view, 2131492968, "field 'tvTotalTime'");
    view = finder.findRequiredView(source, 2131492965, "field 'llProgress'");
    target.llProgress = finder.castView(view, 2131492965, "field 'llProgress'");
    view = finder.findRequiredView(source, 2131492970, "field 'tvPrevious' and method 'onTvPreviousClick'");
    target.tvPrevious = finder.castView(view, 2131492970, "field 'tvPrevious'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onTvPreviousClick();
        }
      });
    view = finder.findRequiredView(source, 2131492957, "field 'tvPlayPause' and method 'onTvPlayPauseClick'");
    target.tvPlayPause = finder.castView(view, 2131492957, "field 'tvPlayPause'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onTvPlayPauseClick();
        }
      });
    view = finder.findRequiredView(source, 2131492971, "field 'tvNext' and method 'onTvNextClick'");
    target.tvNext = finder.castView(view, 2131492971, "field 'tvNext'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onTvNextClick();
        }
      });
    view = finder.findRequiredView(source, 2131492969, "field 'llControl'");
    target.llControl = finder.castView(view, 2131492969, "field 'llControl'");
    view = finder.findRequiredView(source, 2131492973, "field 'cbFavorite'");
    target.cbFavorite = finder.castView(view, 2131492973, "field 'cbFavorite'");
    view = finder.findRequiredView(source, 2131492974, "field 'tvMode'");
    target.tvMode = finder.castView(view, 2131492974, "field 'tvMode'");
    view = finder.findRequiredView(source, 2131492975, "field 'tvDownloaded'");
    target.tvDownloaded = finder.castView(view, 2131492975, "field 'tvDownloaded'");
    view = finder.findRequiredView(source, 2131492976, "field 'tvShare'");
    target.tvShare = finder.castView(view, 2131492976, "field 'tvShare'");
    view = finder.findRequiredView(source, 2131492958, "field 'tvList'");
    target.tvList = finder.castView(view, 2131492958, "field 'tvList'");
    view = finder.findRequiredView(source, 2131492972, "field 'llBottom'");
    target.llBottom = finder.castView(view, 2131492972, "field 'llBottom'");
    view = finder.findRequiredView(source, 2131492959, "field 'flRoot'");
    target.flRoot = finder.castView(view, 2131492959, "field 'flRoot'");
  }

  @Override public void unbind(T target) {
    target.sdvCover = null;
    target.tvBack = null;
    target.tvSongName = null;
    target.tvMore = null;
    target.lrcView = null;
    target.textProgressBar = null;
    target.llDots = null;
    target.tvCurrentTime = null;
    target.seekbar = null;
    target.tvTotalTime = null;
    target.llProgress = null;
    target.tvPrevious = null;
    target.tvPlayPause = null;
    target.tvNext = null;
    target.llControl = null;
    target.cbFavorite = null;
    target.tvMode = null;
    target.tvDownloaded = null;
    target.tvShare = null;
    target.tvList = null;
    target.llBottom = null;
    target.flRoot = null;
  }
}
