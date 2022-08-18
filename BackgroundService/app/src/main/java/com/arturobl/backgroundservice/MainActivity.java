package com.arturobl.backgroundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/******************************************************************
        Se tiene que agregar lo siguiente al manifest:
        <service android:name=".MyBackgroundService"/>

        El Background service se detiene cuando la activity
        se cierra desde el administrador de tareas de android.
*******************************************************************/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btnClick(View view){
        Intent serviceInent = new Intent(this, MyBackgroundService.class);
        startService(serviceInent);
    }
}