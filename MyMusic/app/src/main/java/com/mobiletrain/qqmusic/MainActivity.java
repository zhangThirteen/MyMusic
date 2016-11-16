package com.mobiletrain.qqmusic;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobiletrain.qqmusic.fragment.MainFragment;
import com.mobiletrain.qqmusic.fragment.MoreFragment;
import com.mobiletrain.qqmusic.fragment.SearchFragment;
import com.mobiletrain.qqmusic.myinterface.Song;
import com.mobiletrain.qqmusic.myinterface.SongPlayActivity;
import com.mobiletrain.qqmusic.myinterface.SongPlayer;
import com.mobiletrain.qqmusic.util.MedioUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends SongPlayActivity implements MainFragment.onMainFragmentListen,
        MoreFragment.onMoreFragmentClickListen,
        SearchFragment.OnSearchFragmentViewClickListener,
        SongPlayer {

    @Bind(R.id.flContainer)
    FrameLayout flContainer;
    @Bind(R.id.sdvCover)
    ImageView sdvCover;
    @Bind(R.id.tvSongName)
    TextView tvSongName;
    @Bind(R.id.tvSingerAlbum)
    TextView tvSingerAlbum;
    @Bind(R.id.tvPlayPause)
    TextView tvPlayPause;
    @Bind(R.id.tvList)
    TextView tvList;
    @Bind(R.id.llPlaying)
    LinearLayout llPlaying;
    private MainFragment mainFragment;
    private MoreFragment moreFragment;
    private Fragment currentFragment;
    private SearchFragment searchFragment;
    private int currentSongId = -1;
    private MyReceiver myReceiver;
    private IntentFilter intentFilter;
    private ObjectAnimator rotationAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainFragment = new MainFragment();
        mainFragment.setListener(this);
        currentFragment = mainFragment;
        showFragment(mainFragment, false);


        initRotationAnim();
    }

    @OnClick(R.id.tvPlayPause)
    public void onPlayOnclick(View view) {

    }


    @Override
    public void onTvMenuClick() {
        if (moreFragment == null) {
            moreFragment = new MoreFragment();
            moreFragment.setListen(this);
        }
        ;
        showFragment(moreFragment, true);
    }

    @Override
    public void onTvSearchClick() {
        if (searchFragment == null) {
            searchFragment = new SearchFragment();
            searchFragment.setListener(this);
            searchFragment.setPlayer(this);
        }

        showFragment(searchFragment, true);
    }


    private void showFragment(Fragment fragment, boolean b) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            ft.add(R.id.flContainer, fragment);
        }
        if (b) {
            if (fragment instanceof MainFragment && currentFragment instanceof MoreFragment) {
                ft.setCustomAnimations(R.anim.enter_leftword, R.anim.exit_leftword);
                mainFragment.vMask.setVisibility(View.GONE);
            } else if (fragment instanceof MoreFragment && currentFragment instanceof MainFragment) {
                ft.setCustomAnimations(R.anim.enter_rightword, R.anim.exit_rightword);
                mainFragment.vMask.setVisibility(View.VISIBLE);
            } else if (fragment instanceof MainFragment && currentFragment instanceof SearchFragment) {
                ft.setCustomAnimations(R.anim.enter_rightword, R.anim.exit_rightword);
                mainFragment.vMask.setVisibility(View.GONE);
            } else {
                ft.setCustomAnimations(R.anim.enter_leftword, R.anim.exit_leftword);
                mainFragment.vMask.setVisibility(View.VISIBLE);
            }
        }
        ft.hide(currentFragment).show(fragment);
        ft.commit();
        currentFragment = fragment;
    }

    @Override
    public void onMoreFragmentTvBlackClick() {
        showFragment(mainFragment, true);
    }

    @Override
    public void onSearchFragmentTvBackClick() {
        showFragment(mainFragment, true);
    }

    @OnClick(R.id.llPlaying)
    public void llClick() {
        Intent intent = new Intent(this, PlayingActivity.class);
        intent.putExtra("songid", currentSongId);
        startActivity(intent);

    }


    @Override
    protected void onStart() {
        super.onStart();
        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(MedioUtil.ACTION_PLAYING_STATE_CHANGED);
        registerReceiver(myReceiver, intentFilter);

    }

    @Override
    public void play(List<Song> songs, int position) {
        if (mediaService != null) {
            mediaService.play(this, songs, position);
        }
    }


    class MyReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(final Context context, Intent intent) {
            String action = intent.getAction();
            if (MedioUtil.ACTION_PLAYING_STATE_CHANGED.equals(action)) {
                try {
                    updatePlayingState();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void updatePlayingState() {
        Song currentPlayingSong = MedioUtil.currentPlayingSong;
        tvSongName.setText(currentPlayingSong.getISongname());
        tvSingerAlbum.setText(currentPlayingSong.getISingername());
        String imgUrl = currentPlayingSong.getIAlbumpic_small();
        if (imgUrl != null) {
            sdvCover.setImageURI(Uri.parse(imgUrl));
        }
        if (MedioUtil.isPlaying) {
            tvPlayPause.setBackgroundResource(R.mipmap.pause_big);
            rotationAnimator.start();
        } else {
            tvPlayPause.setBackgroundResource(R.mipmap.play_big);
            rotationAnimator.cancel();
        }
    }

    private void initRotationAnim() {
        rotationAnimator = ObjectAnimator.ofFloat(sdvCover, "rotation", 0, 360 * 1000);
        rotationAnimator.setDuration(6000 * 1000);
        rotationAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotationAnimator.setInterpolator(new LinearInterpolator());
    }

    @OnClick(R.id.tvPlayPause)
    public void onClick(View v) {
        if (mediaService != null) {
            mediaService.playOrPause(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

}


//    private void lyric() {
//        ThreadUtil.execute(new Runnable() {
//            @Override
//            public void run() {
//                //getTops();
//                //getQuerys();
//                String json = HttpUtil.getLyric(4833285);
//                LyricBean lyricBean = JsonUtil.parseLyricBean(json);
//                String lyric = lyricBean.getShowapi_res_body().getLyric();
//                lyric = lyric.replaceAll("&#58", ":");
//                lyric = lyric.replaceAll("&#32", " ");
//                lyric = lyric.replaceAll("&#40", "(");
//                lyric = lyric.replaceAll("&#41", ")");
//                lyric = lyric.replaceAll("&#46;", ".");
//                lyric = lyric.replaceAll("&#10;", "\n");
//                lyric = lyric.replaceAll("&#38;", "&");
//                lyric = lyric.replaceAll("&#45;", "-");
//                Message message = handler.obtainMessage();
//                message.what = MSG_JSON_GOT;
//                message.obj = lyric;
//                handler.sendMessage(message);
//            }
//        });
//    }



