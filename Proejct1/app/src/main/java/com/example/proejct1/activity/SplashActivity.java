package com.example.proejct1.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.proejct1.R;

public class SplashActivity extends AppCompatActivity {

    Animation anim_FadeIn;
    Animation anim_ball;
    ImageView ddabong;
    ConstraintLayout constraintLayout;
    ImageView dda;
    ImageView dda2;
    ImageView bong;
    ImageView bong2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 화면 가로 고정
        setContentView(R.layout.splash_activity);
        ddabong =findViewById(R.id.splash_ddabong);
        dda = findViewById(R.id.dda);
        dda2 = findViewById(R.id.dda2);
        bong = findViewById(R.id.bong);
        bong2 = findViewById(R.id.bong2);
        anim_ball = AnimationUtils.loadAnimation(this, R.anim.spin);
        dda.startAnimation(anim_ball);
        dda2.startAnimation(anim_ball);
        bong.startAnimation(anim_ball);
        bong2.startAnimation(anim_ball);
        anim_FadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        anim_FadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveMain();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ddabong.startAnimation(anim_FadeIn);
    }

    private void moveMain() {
        new Handler().postDelayed(new Runnable()
        {
            @SuppressLint("ResourceType")
            @Override
            public void run()
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();	//현재 액티비티 종료
            }
        }, 20); // sec초 정도 딜레이를 준 후 시작
    }



}

