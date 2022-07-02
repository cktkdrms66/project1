package com.example.proejct1.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proejct1.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity2 extends AppCompatActivity {

    private ImageView targetImg;
    private TextView scoreTxt;
    private Button bragBtn;
    private Button clickBtn;

    private int score = 0;
    private boolean isOk = true;

    private Timer timer;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        pref = getPreferences(Activity.MODE_PRIVATE);
        editor = pref.edit();

        score = pref.getInt("score", 0);

        findViewByIds();
        setBtns();
        startTimer();

        scoreTxt.setText(String.valueOf(score));
    }

    @Override
    protected void onDestroy() {
        stopTimer();
        super.onDestroy();
    }

    private void findViewByIds() {
        targetImg = findViewById(R.id.target_image);
        scoreTxt = findViewById(R.id.score);
        bragBtn = findViewById(R.id.brag_btn);
        clickBtn = findViewById(R.id.click_btn);
    }

    private void setBtns() {
        targetImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOk) {
                    score++;
                } else {
                    score = 0;
                    Toast.makeText(view.getContext(), "(훌쩍...)", Toast.LENGTH_SHORT).show();
                }

                scoreTxt.setText(String.valueOf(score));
                editor.putInt("score", score);
                editor.commit();
            }
        });

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOk) {
                    score++;
                } else {
                    score = 0;
                    Toast.makeText(view.getContext(), "(훌쩍...)", Toast.LENGTH_SHORT).show();
                }

                scoreTxt.setText(String.valueOf(score));
                editor.putInt("score", score);
                editor.commit();
            }
        });

        bragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void startTimer() {
        timer = new Timer();

        timer.schedule(setIcon, 0,500);
    }

    TimerTask setIcon = new TimerTask() {
        @Override
        public void run() {
            Random random = new Random();
            int value = random.nextInt(100);

            if (value > 17) {
                isOk = true;
                clickBtn.setText(R.string.dda);
                targetImg.setImageResource(R.drawable.ddabong);
            } else {
                isOk = false;
                clickBtn.setText(R.string.sad);
                targetImg.setImageResource(R.drawable.sad);
            }
        }
    };

    private void stopTimer() {
        timer.cancel();
    }

}
