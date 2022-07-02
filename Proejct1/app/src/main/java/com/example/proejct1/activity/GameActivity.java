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

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    Random random = new Random();
    Yabawi yabawi11, yabawi12, yabawi13;
    Button start_btn;
    ViewCircleMovingAni viewCircleMovingAni1 = new ViewCircleMovingAni();
    ViewCircleMovingAni viewCircleMovingAni2 = new ViewCircleMovingAni();
    ArrayList<Yabawi> yabawiArrayList = new ArrayList<>();

    int playIndex = 10;
    int playCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        yabawi11 = new Yabawi(findViewById(R.id.yabawi11_frameLayout), findViewById(R.id.ball1), findViewById(R.id.cup1));
        yabawi12 = new Yabawi(findViewById(R.id.yabawi12_frameLayout), findViewById(R.id.ball2), findViewById(R.id.cup2));
        yabawi13 = new Yabawi(findViewById(R.id.yabawi13_frameLayout), findViewById(R.id.ball3), findViewById(R.id.cup3));
        start_btn = findViewById(R.id.start_btn);


        yabawiArrayList.add(yabawi11);
        yabawiArrayList.add(yabawi12);
        yabawiArrayList.add(yabawi13);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!viewCircleMovingAni1.isPlaying&& !viewCircleMovingAni2.isPlaying){
                    for(int i=0; i<yabawiArrayList.size(); i ++){
                        yabawiArrayList.get(i).isHaveBall = false;
                    }
                    yabawiArrayList.get(random.nextInt(yabawiArrayList.size())).isHaveBall = true;
                    Yabawi []  yabawis = getRandomYabawi();
                    startAnimationCircle(yabawis[0],yabawis[1]);
                }

            }
        });

        yabawi11.setOnYabawiClickListener(new Yabawi.OnYabawiClickListener() {

            @Override
            public void onClick(Yabawi yabawi) {
                allYabawiCupOpen();
            }
        });

        yabawi12.setOnYabawiClickListener(new Yabawi.OnYabawiClickListener() {
            @Override
            public void onClick(Yabawi yabawi) {
                allYabawiCupOpen();
            }
        });

        yabawi13.setOnYabawiClickListener(new Yabawi.OnYabawiClickListener() {
            @Override
            public void onClick(Yabawi yabawi) {
                allYabawiCupOpen();
            }
        });
    }

    private void startAnimationCircle(Yabawi yabawi1, Yabawi yabawi2){
        viewCircleMovingAni1.setMovingAnimationFinishListener(new ViewCircleMovingAni.MovingAnimationFinishListenger() {
            @Override
            public void onFinish(float fromXPosition, float fromYPosition, float toXPosition, float toYPosition) {
                if(!viewCircleMovingAni1.isPlaying && !viewCircleMovingAni2.isPlaying){
                    if(playCount <= playIndex){
                        Yabawi[] yabawis = getRandomYabawi();
                        startAnimationCircle(yabawis[0], yabawis[1]);
                    }else {
                        playCount = 0;
                    }
                    playCount ++;
                }

            }
        });
        viewCircleMovingAni2.setMovingAnimationFinishListener(new ViewCircleMovingAni.MovingAnimationFinishListenger() {
            @Override
            public void onFinish(float fromXPosition, float fromYPosition, float toXPosition, float toYPosition) {
                if(!viewCircleMovingAni1.isPlaying && !viewCircleMovingAni2.isPlaying){
                    if(playCount <= playIndex){
                        Yabawi[] yabawis = getRandomYabawi();
                        startAnimationCircle(yabawis[0], yabawis[1]);
                    }else {
                        playCount = 0;
                    }
                    playCount ++;
                }
            }
        });
        viewCircleMovingAni1.startViewCircleMovingAnimation(yabawi1.getView(),yabawi1.getView().getX(),yabawi1.getView().getY(),
                yabawi2.getView().getX(),yabawi2.getView().getY(),(float) 1.0,true);
        viewCircleMovingAni2.startViewCircleMovingAnimation(yabawi2.getView(),yabawi2.getView().getX(),yabawi2.getView().getY(),
                yabawi1.getView().getX(),yabawi1.getView().getY(),(float) 1.0,false);
    }

    private Yabawi [] getRandomYabawi(){
        Yabawi[] yabawis = new Yabawi[2];
        int randomInt1;
        int randomInt2;
        while(true){
            randomInt1 = random.nextInt(yabawiArrayList.size());
            randomInt2 = random.nextInt(yabawiArrayList.size());
            if(randomInt1 != randomInt2){
                break;
            }
        }
        yabawis[0] = yabawiArrayList.get(randomInt1);
        yabawis[1] = yabawiArrayList.get(randomInt2);

        return yabawis;
    }

    private void allYabawiCupOpen(){
        for (int i=0; i<yabawiArrayList.size(); i++){
            yabawiArrayList.get(i).openYabawiCup();
        }
    }
    private void allYabawiCupClose(){
        for (int i=0; i<yabawiArrayList.size(); i++){
            yabawiArrayList.get(i).closeYabawicup();
        }
    }


}
