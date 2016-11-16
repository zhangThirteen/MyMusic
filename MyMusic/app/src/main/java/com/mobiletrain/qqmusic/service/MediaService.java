package com.mobiletrain.qqmusic.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.mobiletrain.qqmusic.MainActivity;
import com.mobiletrain.qqmusic.R;
import com.mobiletrain.qqmusic.myinterface.Song;
import com.mobiletrain.qqmusic.util.MedioUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public class MediaService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Notification notification = initNotification();
        startForeground(1,notification);
    }

    private Notification initNotification() {

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(this, MainActivity.class), PendingIntent.FLAG_ONE_SHOT);
        Notification notification = new NotificationCompat.Builder(this)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.icon_music)
                .setTicker("title")

                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.mymusic))
                .setContentTitle("content title")
                .setContentText("content text")

                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);
        return notification;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void play(final Context context, List<Song> songs, int position) {
        MedioUtil.play(context, songs, position);
    }

    public void playOrPause(Context context) {
        MedioUtil.playOrPause(context);
    }


    public void playNext(Context context) {
        MedioUtil.playNext(context);
    }

    public void playPrevious(Context context) {
        MedioUtil.playPrevious(context);
    }

    public class MyBinder extends Binder {
        public MediaService getService() {
            return MediaService.this;
        }
    }
}
