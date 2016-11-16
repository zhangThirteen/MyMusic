package com.mobiletrain.qqmusic.fragment.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobiletrain.qqmusic.Config;
import com.mobiletrain.qqmusic.R;
import com.mobiletrain.qqmusic.adapter.LocalMusicAdapter;
import com.mobiletrain.qqmusic.model.LocalMusic;
import com.mobiletrain.qqmusic.myinterface.Song;
import com.mobiletrain.qqmusic.myinterface.SongPlayerFragment;
import com.mobiletrain.qqmusic.util.MedioUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class MineFragment extends SongPlayerFragment {

    @Bind(R.id.lvLocalMusics)
    ListView lvLocalMusics;
    private View view;
    private List<LocalMusic> localMusics;
    private LocalMusicAdapter localMusicAdapter;
    private PtrFrameLayout ptrFrameLayout;
    Handler  handler= new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       localMusics = MedioUtil.getLocalMusic(getActivity(), Config.DOWNLOAD_DIR_PATH);
     //   getData();
    }

    private void getData() {
        List<LocalMusic> list = MedioUtil.getLocalMusic(getActivity(), Config.DOWNLOAD_DIR_PATH);
        localMusics.clear();
        localMusics.addAll(list);
        if (localMusicAdapter!=null){
            localMusicAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
            ButterKnife.bind(this, view);

             ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptrfl);
            localMusicAdapter = new LocalMusicAdapter(getActivity(), localMusics);
            lvLocalMusics.setAdapter(localMusicAdapter);

            PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());
            ptrFrameLayout.setHeaderView(header);
            ptrFrameLayout.addPtrUIHandler(header);
            ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
           //         doRefresh();
                    ptrFrameLayout.refreshComplete();
                }
            });
        }


        return view;
    }

    private void doRefresh() {
        getData();
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnItemClick(R.id.lvLocalMusics)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < localMusics.size(); i++) {
            songs.add(localMusics.get(i));
        }

        if (songPlayer != null) {
            songPlayer.play(songs, position);
        }
    }


}
