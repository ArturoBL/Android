package com.darklaker.camxtorch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.CheckBox;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private PreviewView previewView;
    private ListenableFuture cameraProviderFuture;
    private CheckBox chkFlash;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkFlash = findViewById(R.id.chkFlash);
        previewView = findViewById(R.id.previewview);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != (PackageManager.PERMISSION_GRANTED)){
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA},101);
                    }else{
                        ProcessCameraProvider processCameraProvider = (ProcessCameraProvider) cameraProviderFuture.get();
                        Preview preview = new Preview.Builder().build();
                        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
                        preview.setSurfaceProvider(previewView.getSurfaceProvider());
                        ImageCapture imageCapture = new ImageCapture.Builder().build();
                        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                                .setTargetResolution(new Size(1280,720))
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build();
                        processCameraProvider.unbindAll();
                        camera = processCameraProvider.bindToLifecycle((LifecycleOwner) MainActivity.this, cameraSelector, preview, imageCapture, imageAnalysis);

                    }
                }catch (ExecutionException e){
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));

        chkFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camera.getCameraInfo().hasFlashUnit()){
                    if (chkFlash.isChecked()){
                        camera.getCameraControl().enableTorch(true);
                     }else{
                        camera.getCameraControl().enableTorch(false);
                    }
                }
            }
        });
    }

}