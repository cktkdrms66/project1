package com.example.proejct1.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proejct1.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ImageView ddabong = findViewById(R.Id.ddabong);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.spin);
        ddabong.setAnimation(anim);
        moveMain(2);

    }

    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable()
        {
            @SuppressLint("ResourceType")
            @Override
            public void run()
            {

                //new Intent(현재 context, 이동할 activity)
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);	//intent 에 명시된 액티비티로 이동

                finish();	//현재 액티비티 종료
            }
        }, 1000 * sec); // sec초 정도 딜레이를 준 후 시작
    }



}

