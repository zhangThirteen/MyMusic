package com.mobiletrain.qqmusic.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mobiletrain.qqmusic.model.LyricBean;
import com.mobiletrain.qqmusic.model.QueryBean;
import com.mobiletrain.qqmusic.model.TopBean;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class JsonUtil {

    public static TopBean parseTopBean(String json) {
        TopBean topBean = null;
        try {
            topBean = new Gson().fromJson(json, TopBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return topBean;
    }

    public static QueryBean parseQueryBean(String json) {
        QueryBean queryBean = null;
        try {
            queryBean = new Gson().fromJson(json, QueryBean.class);

        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return queryBean;
    }

    public static LyricBean parseLyricBean(String json) {
        LyricBean lyricBean = new Gson().fromJson(json, LyricBean.class);
        return lyricBean;
    }
}
