package com.arturobl.checkwifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.vision.clearcut.LogUtils;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetConnection.checkConnection(getApplicationContext()) ==  ConnectivityManager.TYPE_WIFI){
                    Toast.makeText(getApplicationContext(),"Wifi",Toast.LENGTH_SHORT).show();
                }else{
                    if (InternetConnection.checkConnection(getApplicationContext()) ==  ConnectivityManager.TYPE_MOBILE){
                        Toast.makeText(getApplicationContext(),"Móvil",Toast.LENGTH_SHORT).show();
                    }else{
                      Toast.makeText(getApplicationContext(),"No se reconoce conexión",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}