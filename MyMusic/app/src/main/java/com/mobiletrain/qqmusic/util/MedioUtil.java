package com.mobiletrain.qqmusic.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.mobiletrain.qqmusic.model.LocalMusic;
import com.mobiletrain.qqmusic.myinterface.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/11 0011.
 */
public class MedioUtil {
    public static List<Song> currentList;
    public static Song currentPlayingSong;
    public static LocalMusic localMusic;
    private static int currentPosition = -1;
    public static MediaPlayer mediaPlayer;
    public static boolean isPlaying = false;
    public static final String ACTION_PLAYING_STATE_CHANGED = "playingStateChanged";
    public static final String ACTION_PLAYING_SONG_CHANGED = "playingSongChanged";


    public static void play(final Context context, List<Song> songs, int position) {

        currentList = songs;
        currentPlayingSong = songs.get(position);
        currentPosition = position;


        String path;

        if (!currentPlayingSong.isIDownloaded()) {
            path = currentPlayingSong.getIDownUrl();
        } else {
            path = currentPlayingSong.getIFilePath();
        }

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            mediaPlayer.reset();
            if (!currentPlayingSong.isIDownloaded()) {
                mediaPlayer.setDataSource(context, Uri.parse(path));
            } else {
                mediaPlayer.setDataSource(path);
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
            isPlaying = true;

            Intent intent = new Intent();
            intent.setAction(ACTION_PLAYING_STATE_CHANGED);
            context.sendBroadcast(intent);

            Intent intent2 = new Intent(ACTION_PLAYING_SONG_CHANGED);
            context.sendBroadcast(intent2);


            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    isPlaying = false;
                    playNext(context);
                    Intent intent = new Intent();
                    intent.setAction(ACTION_PLAYING_STATE_CHANGED);
                    context.sendBroadcast(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void playOrPause(Context context) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                isPlaying = false;
            } else {
                mediaPlayer.start();
                isPlaying = true;
            }

            context.sendBroadcast(new Intent(ACTION_PLAYING_STATE_CHANGED));

        } else {
            Toast.makeText(context, "播放器未就绪", Toast.LENGTH_SHORT).show();
        }
    }

    public static void playNext(Context context) {
        if (currentList != null) {
            int newPosition = 0;
            while (((newPosition = new Random().nextInt(currentList.size())) == currentPosition)) {
            }
            play(context, currentList, newPosition);
        } else {
            Toast.makeText(context, "播放列表空空如也", Toast.LENGTH_SHORT).show();
        }
    }

    public static void playPrevious(Context context) {
        if (currentList != null) {
            int newPosition = 0;
            while (((newPosition = new Random().nextInt(currentList.size())) == currentPosition)) {
            }
            play(context, currentList, newPosition);
        } else {
            Toast.makeText(context, "播放列表空空如也", Toast.LENGTH_SHORT).show();
        }
    }

    public static List<LocalMusic> getLocalMusic(Context context, String dirPath) {
        List<LocalMusic> localMusics = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                MediaStore.Audio.Media.DATA + " like?",
                new String[]{dirPath + "%"},
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
            String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))); // 音乐标题
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)); // 艺术家
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)); // 专辑
            String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            long albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)); // 时长
            long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)); // 文件大小
            String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // 是否为音乐

            if (isMusic != 0) {
                LocalMusic music = new LocalMusic(id, title, artist, album, displayName, albumId, duration, size, filePath);
                localMusics.add(music);
            }
        }
        return localMusics;
    }
}
