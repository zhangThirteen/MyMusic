// Generated code from Butter Knife. Do not modify!
package com.mobiletrain.qqmusic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.mobiletrain.qqmusic.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492952, "field 'flContainer'");
    target.flContainer = finder.castView(view, 2131492952, "field 'flContainer'");
    view = finder.findRequiredView(source, 2131492954, "field 'sdvCover'");
    target.sdvCover = finder.castView(view, 2131492954, "field 'sdvCover'");
    view = finder.findRequiredView(source, 2131492955, "field 'tvSongName'");
    target.tvSongName = finder.castView(view, 2131492955, "field 'tvSongName'");
    view = finder.findRequiredView(source, 2131492956, "field 'tvSingerAlbum'");
    target.tvSingerAlbum = finder.castView(view, 2131492956, "field 'tvSingerAlbum'");
    view = finder.findRequiredView(source, 2131492957, "field 'tvPlayPause', method 'onPlayOnclick', and method 'onClick'");
    target.tvPlayPause = finder.castView(view, 2131492957, "field 'tvPlayPause'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onPlayOnclick(p0);
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492958, "field 'tvList'");
    target.tvList = finder.castView(view, 2131492958, "field 'tvList'");
    view = finder.findRequiredView(source, 2131492953, "field 'llPlaying' and method 'llClick'");
    target.llPlaying = finder.castView(view, 2131492953, "field 'llPlaying'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.llClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.flContainer = null;
    target.sdvCover = null;
    target.tvSongName = null;
    target.tvSingerAlbum = null;
    target.tvPlayPause = null;
    target.tvList = null;
    target.llPlaying = null;
  }
}
