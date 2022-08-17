package com.arturobl.broadcastcharger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //oreo para arriba
        MyReceiver myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        registerReceiver(myReceiver, intentFilter);
    }
}