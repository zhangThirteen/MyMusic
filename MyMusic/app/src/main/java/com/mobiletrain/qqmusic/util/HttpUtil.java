package com.mobiletrain.qqmusic.util;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.mobiletrain.qqmusic.model.QueryBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class HttpUtil {

    private static final int APP_ID = 25268;
    private static final String APP_SIGN = "4b3eaab2157d432aa809b83811956cf1";
    private static OkHttpClient okHttpClient;

    public static String getTops(String topId) {
        return getString("https://route.showapi.com/213-4?showapi_appid=" + APP_ID + "&topid=" + topId + "&showapi_sign=" + APP_SIGN);
    }

    @NonNull
    private static String getString(String urlStr) {
        String json = "";
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                inputStream = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = br.readLine()) != null) {
                    json += line;
                }
                return json;
            } else {
                json = "responseCode" + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            json = "Exception" + e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }

    public static String getLyric(int musicId) {
        String json = getString("http://route.showapi.com/213-2?musicid=" + musicId + "&showapi_appid=" + APP_ID + "&showapi_sign=" + APP_SIGN);
        return json;
    }

    public static String query(String keyword) {
        String json = okHttpGetString("http://route.showapi.com/213-1?keyword=" + keyword + "&page=1&showapi_appid=" + APP_ID + "&showapi_sign=" + APP_SIGN);
        return json;
    }

    private static String okHttpGetString(String url) {
        String json = "";
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .tag("tag")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                json = response.body().string();
            } else {
                json = "response not successful";
            }
        } catch (IOException e) {
            e.printStackTrace();
            json = "IOException" + e;
        }
        return json;
    }

    public static boolean downloadMusic(final Context context, final QueryBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean song,String dirPath,Handler handler) {
        boolean downloadSuccess = false;
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        Request request = new Request.Builder()
                .url(song.getDownUrl())
                .tag("tag")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                Log.e("test", "downloadMusic: "+inputStream);
                downloadSuccess = MyFileUtil.writeFile(context, inputStream, dirPath, song.getSongname() + ".mp3");
                Log.e("test", "downloadMusic: "+downloadSuccess);
                final boolean success = downloadSuccess;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, success ? song.getSongname() + "下载成功" : song.getSongname() + "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return downloadSuccess;

    }
}
