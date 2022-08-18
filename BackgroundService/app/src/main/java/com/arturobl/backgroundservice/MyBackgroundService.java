package com.arturobl.backgroundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyBackgroundService extends Service {
    private Boolean stopped = false;

    @Override
    public void onDestroy() {
        stopped = true;
        Log.e("Service","Service desroyed...");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Se crea un tread que se ejecuta cada 5 segundos
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    serviceToast("Message from service");

                    Log.e("Service","Service is running...");
                    try{
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    /* Toast que se puede ejecutar desde el servicio
       *Nota: lo servicios no pueden ejecutar elementos de interfaz de usuario
    * */
    public void serviceToast(String msg){
        Handler mainHandler = new Handler(getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                // Do your stuff here related to UI, e.g. show toast
                Toast.makeText(getApplicationContext(), "Message from service", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
