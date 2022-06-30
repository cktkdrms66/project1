package com.example.proejct1.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proejct1.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private ImageView targetImg;
    private TextView scoreTxt;

    private int score = 0;
    private boolean isOk = true;

    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        findViewByIds();

        setBtn();




    }

    private void findViewByIds() {
        targetImg = findViewById(R.id.target_image);
        scoreTxt = findViewById(R.id.score);

    }

    private void setBtn() {
        targetImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOk) {
                    score++;

                } else {
                    score = 0;
                }

                scoreTxt.setText(score);
            }
        });
    }

//    private void startTimer() {
//        timer = new Timer();
//
//        timer.schedule(setSad, 0,60 * 1000);
//    }
//    TimerTask setSad = new TimerTask() {
//        @Override
//        public void run() {
//            Random random = new Random();
//            int value = random.nextInt(100);
//
//            if (value > 90) {
//
//            }
//        }
//    }

}
