package com.example.mywallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    WallpaperManager wallpaperManager;
    Bitmap bitmap1,bitmap2;
    DisplayMetrics displayMetrics;
    int width, height;
    BitmapDrawable bitmapDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.buttonset);
        ImageView imageView= findViewById(R.id.imageview);
        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        bitmap1=bitmapDrawable.getBitmap();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetScreenWidthheight();
                setBitmapSize();
                wallpaperManager=wallpaperManager.getInstance(MainActivity.this);
                try {
                    wallpaperManager .setBitmap(bitmap2);
                    wallpaperManager.suggestDesiredDimensions(width,height);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void setBitmapSize() {
        bitmap2=Bitmap.createScaledBitmap(bitmap1,width,height,false);
    }

    private void GetScreenWidthheight() {
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }
}