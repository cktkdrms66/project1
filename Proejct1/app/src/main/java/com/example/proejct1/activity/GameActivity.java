package com.example.proejct1.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proejct1.R;
import com.example.proejct1.model.Yabawi;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    Yabawi yabawi11, yabawi12, yabawi13;
    Button start_btn;
    ViewCircleMovingAni viewCircleMovingAni1 = new ViewCircleMovingAni();
    ViewCircleMovingAni viewCircleMovingAni2 = new ViewCircleMovingAni();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        yabawi11 = new Yabawi(findViewById(R.id.yabawi11_frameLayout), findViewById(R.id.ball1), findViewById(R.id.cup1));
        yabawi12 = new Yabawi(findViewById(R.id.yabawi12_frameLayout), findViewById(R.id.ball2), findViewById(R.id.cup2));
        yabawi13 = new Yabawi(findViewById(R.id.yabawi13_frameLayout), findViewById(R.id.ball3), findViewById(R.id.cup3));
        start_btn = findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationCircle(yabawi11,yabawi12);
            }
        });
    }

    private void startAnimationCircle(Yabawi yabawi11, Yabawi yabawi12){
        viewCircleMovingAni1.setMovingAnimationFinishListener(new ViewCircleMovingAni.MovingAnimationFinishListenger() {
            @Override
            public void onFinish(float fromXPosition, float fromYPosition, float toXPosition, float toYPosition) {

            }
        });
        viewCircleMovingAni2.setMovingAnimationFinishListener(new ViewCircleMovingAni.MovingAnimationFinishListenger() {
            @Override
            public void onFinish(float fromXPosition, float fromYPosition, float toXPosition, float toYPosition) {

            }
        });
        viewCircleMovingAni1.startViewCircleMovingAnimation(yabawi11.getView(),yabawi11.getView().getX(),yabawi11.getView().getY(),
                yabawi12.getView().getX(),yabawi12.getView().getY(),(float) 1.0,true);
        viewCircleMovingAni1.startViewCircleMovingAnimation(yabawi12.getView(),yabawi12.getView().getX(),yabawi12.getView().getY(),
                yabawi11.getView().getX(),yabawi11.getView().getY(),(float) 1.0,false);
    }
}
