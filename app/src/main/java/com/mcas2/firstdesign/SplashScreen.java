package com.mcas2.firstdesign;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.blurry.Blurry;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        openLogin();

        ImageView logoSplash = findViewById(R.id.logoSplash);

        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation zoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);

        logoSplash.startAnimation(zoomInAnimation);

        ImageView background = findViewById(R.id.backgroundSplash);
        RequestBuilder<Drawable> a = Glide.with(this).load("https://images.unsplash.com/photo-1582103287241-2762adba6c36?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");

        a.centerCrop().into(background);
        a.transform(new BlurTransformation( 20, 2)).centerCrop().into(background);

        a.transform(new CenterCrop(), new BlurTransformation(20, 2));
    }

    public void openLogin() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 3000);
    }
}
