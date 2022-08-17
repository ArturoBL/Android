package com.arturobl.broadcastcharger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        if (intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED")){
            Toast.makeText(context, "Power is off", Toast.LENGTH_SHORT).show();
        }else{
            if (intent.getAction().equals("android.intent.action.ACTION_POWER_CONNECTED")){
                Toast.makeText(context, "Power is on", Toast.LENGTH_SHORT).show();
            }
        }
    }
}