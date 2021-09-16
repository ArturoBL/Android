package com.arturo.beristain.photoview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import cn.bluemobi.dylan.photoview.library.PhotoViewAttacher;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private PhotoViewAttacher photoViewAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.iv_photo);
        Drawable bitmap = getDrawable(R.drawable.gal);
        imageView.setImageDrawable(bitmap);

        photoViewAttacher = new PhotoViewAttacher(imageView);
        photoViewAttacher.setMaximumScale(10);
    }
}