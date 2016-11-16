package com.mobiletrain.qqmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobiletrain.qqmusic.R;
import com.mobiletrain.qqmusic.adapter.SearchResultAdapter;
import com.mobiletrain.qqmusic.model.QueryBean;
import com.mobiletrain.qqmusic.myinterface.Song;
import com.mobiletrain.qqmusic.myinterface.SongPlayer;
import com.mobiletrain.qqmusic.myinterface.SongPlayerFragment;
import com.mobiletrain.qqmusic.util.HttpUtil;
import com.mobiletrain.qqmusic.util.JsonUtil;
import com.mobiletrain.qqmusic.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class SearchFragment extends SongPlayerFragment {

    private static final int MSG_QUERY_RESULT_JSON_GOT = 30;
    View view;
    @Bind(R.id.tvBack)
    TextView tvBack;
    @Bind(R.id.etSearch)
    EditText etSearch;
    @Bind(R.id.tvSearch)
    TextView tvSearch;
    @Bind(R.id.lvRecent)
    ListView lvRecent;
    @Bind(R.id.lvResult)
    ListView lvResult;
    SearchResultAdapter searchResultAdapter;
    QueryBean queryBean = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_QUERY_RESULT_JSON_GOT:
                    String json = (String) msg.obj;
                    queryBean = JsonUtil.parseQueryBean(json);
                    if (queryBean != null) {
                        Log.e("test", "handleMessage: " + queryBean);
                        searchResultAdapter.setData(queryBean);
                    } else {
                        Toast.makeText(getActivity(), "数据异常，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_search, container, false);
            ButterKnife.bind(this, view);
            searchResultAdapter = new SearchResultAdapter(getActivity(), queryBean);
            lvResult.setAdapter(searchResultAdapter);
        }
        return view;
    }

    @OnItemClick(R.id.lvResult)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            List<QueryBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = queryBean.getShowapi_res_body().getPagebean().getContentlist();
            List<Song> songs = new ArrayList<>();
            for (int i = 0; i < contentlist.size(); i++) {
                songs.add(contentlist.get(i));
            }

            if (player != null) {
                Log.e("test", "onItemClick: "+songs );
                player.play(songs, position);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "播放失败，请检查数据！", Toast.LENGTH_SHORT).show();
        }
    }

    SongPlayer player;

    public void setPlayer(SongPlayer player) {
        this.player = player;
    }



//    private void play( QueryBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean song) {
//        if (player!=null){
//            player.playSearchSong(song);
//        }


    @OnClick(R.id.tvSearch)
    public void onClick(View v) {
        final String keyword = etSearch.getText().toString();
        if (keyword != null && !keyword.trim().equals("")) {
            ThreadUtil.execute(new Runnable() {
                @Override
                public void run() {
                    String json = HttpUtil.query(keyword);
                    Message msg = handler.obtainMessage();
                    msg.what = MSG_QUERY_RESULT_JSON_GOT;
                    msg.obj = json;
                    handler.sendMessage(msg);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tvBack)
    public void onTvBackClick(View v) {
        if (listener != null) {
            listener.onSearchFragmentTvBackClick();
        }
    }

    OnSearchFragmentViewClickListener listener;

    public void setListener(OnSearchFragmentViewClickListener listener) {
        this.listener = listener;
    }

    public interface OnSearchFragmentViewClickListener {
        void onSearchFragmentTvBackClick();
    }
}
