package com.mobiletrain.qqmusic.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiletrain.qqmusic.R;
import com.mobiletrain.qqmusic.myinterface.SongPlayerFragment;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class FindFragment extends SongPlayerFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_find, container, false);
        }
        return view;
    }
}
