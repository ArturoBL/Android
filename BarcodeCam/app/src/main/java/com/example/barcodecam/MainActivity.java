package com.example.barcodecam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;

import android.os.Bundle;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ListenableFuture cameraPreviewFuture;
    private ExecutorService cameraExecutor;
    private PreviewView previewView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.previewview);
        this.getWindow().setFlags(1024,1024);
        cameraExecutor = Executors.newSingleThreadExecutor();
        
    }
}