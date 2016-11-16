package com.mobiletrain.qqmusic.fragment.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mobiletrain.qqmusic.R;
import com.mobiletrain.qqmusic.adapter.TopListAdapter;
import com.mobiletrain.qqmusic.model.TopBean;
import com.mobiletrain.qqmusic.myinterface.Song;
import com.mobiletrain.qqmusic.myinterface.SongPlayerFragment;
import com.mobiletrain.qqmusic.util.HttpUtil;
import com.mobiletrain.qqmusic.util.JsonUtil;
import com.mobiletrain.qqmusic.util.ThreadUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class OnlineFragment extends SongPlayerFragment {

    private static final int MSG_JSON_GOT = 20;
    @Bind(R.id.lvTops)
    ListView lvTops;
    private View view;
    TopListAdapter topListAdapter;
    boolean flag = true;

    List<HashMap<String, Object>> data = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_JSON_GOT:
                    Bundle bundle = msg.getData();
                    int order = bundle.getInt("order");
                    String json = bundle.getString("json");

                    TopBean topBean = JsonUtil.parseTopBean(json);
                    if (topBean != null) {
                        HashMap<String, Object> map = data.get(order);
                        map.put("data", topBean);
                        topListAdapter.notifyDataSetChanged();
                    } else if (flag) {
                        flag = false;
                        Toast.makeText(getActivity(), "数据异常，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };

    String[] array = {
            "23-销量",
            "26-热歌",
            "18-民谣",
            "19-摇滚",
            "5-内地",
            "6-港台",
            "3-欧美",
            "16-韩国",
            "17-日本",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_online, container, false);
            ButterKnife.bind(this, view);
            initData();

            topListAdapter = new TopListAdapter(data, getActivity());
            lvTops.setAdapter(topListAdapter);

//                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_tops, lvTops, false);
//                int itemHeight = view.getLayoutParams().height;
//                lvTops.setLayoutParams(new LinearLayout.LayoutParams(view.getLayoutParams().width, itemHeight * array.length));


        }
        return view;
    }

    @OnItemClick(R.id.lvTops)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, Object> map = data.get(position);
        TopBean bean = (TopBean) map.get("data");
        List<TopBean.ShowapiResBodyBean.PagebeanBean.SonglistBean> songlist = bean.getShowapi_res_body().getPagebean().getSonglist();
        List<Song> song = new ArrayList<>();
        for (int i=0;i<songlist.size();i++){
            song.add(songlist.get(i));
        }

        if (songPlayer!=null){
            songPlayer.play(song,0);
        }
    }






    private void initData() {
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            String[] temp = str.split("-");
            final String topId = temp[0];
            String name = temp[1] + "榜";
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("order", i);
            map.put("data", null);
            data.add(map);

            final int order = i;
            ThreadUtil.execute(new Runnable() {
                @Override
                public void run() {
                    String json = HttpUtil.getTops(topId);

                    Message message = handler.obtainMessage();
                    message.what = MSG_JSON_GOT;
                    Bundle bundle = new Bundle();
                    bundle.putInt("order", order);
                    bundle.putString("json", json);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
