package com.mobiletrain.qqmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiletrain.qqmusic.R;
import com.mobiletrain.qqmusic.myinterface.SongPlayerFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class onlineConcreteFragment extends SongPlayerFragment {

    @Bind(R.id.tvBackOnline)
    TextView tvBackOnline;
    @Bind(R.id.tvTopBar)
    TextView tvTopBar;
    @Bind(R.id.lvTopConcrete)
    ListView lvTopConcrete;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_online_concrete, container, false);

        }
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
