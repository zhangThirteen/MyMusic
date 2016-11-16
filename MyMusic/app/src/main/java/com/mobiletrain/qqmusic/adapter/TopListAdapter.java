package com.mobiletrain.qqmusic.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiletrain.qqmusic.R;
import com.mobiletrain.qqmusic.model.TopBean;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class TopListAdapter extends BaseAdapter {
    List<HashMap<String, Object>> data;
    Context context;
    private LayoutInflater inflater;
//    private final LayoutInflater inflater;


    public TopListAdapter(List<HashMap<String, Object>> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.item_tops, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HashMap<String, Object> map = data.get(position);
        String name = (String) map.get("name");
        holder.tvName.setText(name);

        TopBean topBean = (TopBean) map.get("data");

        if (topBean == null) {
            return convertView;
        }
        Log.e("testaa", "getView: "+topBean+"/");
        if (topBean.getShowapi_res_body().getPagebean().getSonglist()==null){
            return convertView;
        }
        List<TopBean.ShowapiResBodyBean.PagebeanBean.SonglistBean> songlist = topBean.getShowapi_res_body().getPagebean().getSonglist();
        TopBean.ShowapiResBodyBean.PagebeanBean.SonglistBean firstSong = songlist.get(0);
        TopBean.ShowapiResBodyBean.PagebeanBean.SonglistBean secondSong = songlist.get(1);
        TopBean.ShowapiResBodyBean.PagebeanBean.SonglistBean thirdSong = songlist.get(2);

        TextView tvFirstInfo = (TextView) holder.singleI.findViewById(R.id.tvSingleInfo);
        TextView tvSecondInfo = (TextView) holder.singleII.findViewById(R.id.tvSingleInfo);
        TextView tvThirdInfo = (TextView) holder.singleIII.findViewById(R.id.tvSingleInfo);
        tvFirstInfo.setText("1 " + firstSong.getSongname() + "-" + firstSong.getSingername());
        tvSecondInfo.setText("2 " + secondSong.getSongname() + "-" + secondSong.getSingername());
        tvThirdInfo.setText("3 " + thirdSong.getSongname() + "-" + thirdSong.getSingername());

        TextView tvFirstArrow = (TextView) holder.singleI.findViewById(R.id.arrow);
        TextView tvSecondArrow = (TextView) holder.singleII.findViewById(R.id.arrow);
        TextView tvThirdArrow = (TextView) holder.singleIII.findViewById(R.id.arrow);
        tvFirstArrow.setVisibility(View.GONE);
        tvSecondArrow.setVisibility(View.VISIBLE);
        tvThirdArrow.setVisibility(View.GONE);

        String url = firstSong.getAlbumpic_big();
        Log.d("test", "getView: "+url);
        holder.sdvCover.setImageURI(Uri.parse(url));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.sdvCover)
        ImageView sdvCover;
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.headphone)
        TextView headphone;
        @Bind(R.id.singleI)
        View singleI;
        @Bind(R.id.singleII)
        View singleII;
        @Bind(R.id.singleIII)
        View singleIII;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
