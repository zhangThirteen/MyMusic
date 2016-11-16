package com.mobiletrain.qqmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobiletrain.qqmusic.R;
import com.mobiletrain.qqmusic.model.LocalMusic;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class LocalMusicAdapter extends BaseAdapter {

    Context context;
    List<LocalMusic> data;
    private LayoutInflater infater;

    public LocalMusicAdapter(Context context, List<LocalMusic> data) {
        this.context = context;
        this.data = data;
        infater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = infater.inflate(R.layout.item_searh_result, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        LocalMusic localMusic = data.get(position);
        final String songname = localMusic.getTitle();
        String singername = localMusic.getArtist();
        String albumname = localMusic.getAlbum();

        holder.tvName.setText(songname);
        holder.tvInfo.setText(singername + "·" + albumname);


        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tvMore)
        TextView tvMore;
        @Bind(R.id.tvPlus)
        TextView tvPlus;
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvDownloaded)
        TextView tvDownloaded;
        @Bind(R.id.tvInfo)
        TextView tvInfo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
