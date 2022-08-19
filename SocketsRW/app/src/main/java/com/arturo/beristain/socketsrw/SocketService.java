package com.arturo.beristain.socketsrw;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.Socket;

public class SocketService extends Service {
    Socket s;
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            s = new Socket("192.168.0.6", 777);
            player = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
            player.setLooping(true);
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            s.close();
            player.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
