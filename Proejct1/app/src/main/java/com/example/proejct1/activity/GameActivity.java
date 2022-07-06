package com.example.proejct1.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proejct1.R;
import com.example.proejct1.model.Yabawi;
import com.example.proejct1.util.Util;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    Random random = new Random();
    Yabawi yabawi11, yabawi12, yabawi13;
    Button start_btn;
    TextView countDown_tv;
    ViewCircleMovingAni viewCircleMovingAni1 = new ViewCircleMovingAni();
    ViewCircleMovingAni viewCircleMovingAni2 = new ViewCircleMovingAni();
    ArrayList<Yabawi> yabawiArrayList = new ArrayList<>();
    int score_tv;

    int plusScore;
    TextView scoreTxt;

    int playIndex = 5;
    int playCount = 0;
    float duration = 1.5f;

    GameStartReadyThread gameStartReadThread;
    boolean gameStart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        plusScore = 5;

        yabawi11 = new Yabawi(findViewById(R.id.yabawi11_frameLayout), findViewById(R.id.ball1), findViewById(R.id.cup1));
        yabawi12 = new Yabawi(findViewById(R.id.yabawi12_frameLayout), findViewById(R.id.ball2), findViewById(R.id.cup2));
        yabawi13 = new Yabawi(findViewById(R.id.yabawi13_frameLayout), findViewById(R.id.ball3), findViewById(R.id.cup3));
        start_btn = findViewById(R.id.start_btn);
        countDown_tv = findViewById(R.id.countDown_tv);
        scoreTxt = findViewById(R.id.score_tv);
        countDown_tv.setVisibility(View.INVISIBLE);


        yabawiArrayList.add(yabawi11);
        yabawiArrayList.add(yabawi12);
        yabawiArrayList.add(yabawi13);

        score_tv = Util.getData(this, "score", 0);
        scoreTxt.setText(String.valueOf(score_tv));

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gameStart) {
                    if (!viewCircleMovingAni1.isPlaying && !viewCircleMovingAni2.isPlaying) {
                        gameStart = true;
                        countDown_tv.setVisibility(View.VISIBLE);
                        for (int i = 0; i < yabawiArrayList.size(); i++) {
                            yabawiArrayList.get(i).isHaveBall = false;
                        }
                        yabawiArrayList.get(random.nextInt(yabawiArrayList.size())).isHaveBall = true;
                        allYabawiCupOpen();
                        countDown_tv.setVisibility(view.VISIBLE);
                        gameStartReadThread = new GameStartReadyThread();
                        gameStartReadThread.start();

                    }
                }
            }
        });

        yabawi11.setOnYabawiClickListener(new Yabawi.OnYabawiClickListener() {

            @Override
            public void onClick(Yabawi yabawi) {
                allYabawiCupOpen();
                resultYabawiGame(yabawi.isHaveBall);
            }
        });

        yabawi12.setOnYabawiClickListener(new Yabawi.OnYabawiClickListener() {
            @Override
            public void onClick(Yabawi yabawi) {
                allYabawiCupOpen();
                resultYabawiGame(yabawi.isHaveBall);
            }
        });

        yabawi13.setOnYabawiClickListener(new Yabawi.OnYabawiClickListener() {
            @Override
            public void onClick(Yabawi yabawi) {
                allYabawiCupOpen();
                resultYabawiGame(yabawi.isHaveBall); 
            }
        });
    }

    private void startAnimationCircle(Yabawi yabawi1, Yabawi yabawi2){
         boolean yabawi1_isHaveBall = yabawi1.isHaveBall;
         boolean yabawi2_isHaveBall = yabawi2.isHaveBall;
         yabawi1.isHaveBall = yabawi2_isHaveBall;
         yabawi2.isHaveBall = yabawi1_isHaveBall;
        viewCircleMovingAni1.setMovingAnimationFinishListener(new ViewCircleMovingAni.MovingAnimationFinishListenger() {
            @Override
            public void onFinish(float fromXPosition, float fromYPosition, float toXPosition, float toYPosition) {
                if(!viewCircleMovingAni1.isPlaying && !viewCircleMovingAni2.isPlaying){
                    if(playCount <= playIndex){
                        Yabawi[] yabawis = getRandomYabawi();
                        startAnimationCircle(yabawis[0], yabawis[1]);
                    }else {
                        playCount = 0;
                        gameStart = false;   
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
                        gameStart = false;
                    }
                    playCount ++;
                }
            }
        });
        viewCircleMovingAni1.startViewCircleMovingAnimation(yabawi1.getView(),yabawi1.getView().getX(),yabawi1.getView().getY(),
                yabawi2.getView().getX(),yabawi2.getView().getY(),duration,true);
        viewCircleMovingAni2.startViewCircleMovingAnimation(yabawi2.getView(),yabawi2.getView().getX(),yabawi2.getView().getY(),
                yabawi1.getView().getX(),yabawi1.getView().getY(),duration,false);
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
        if(!viewCircleMovingAni1.isPlaying && !viewCircleMovingAni2.isPlaying) {
            for (int i = 0; i < yabawiArrayList.size(); i++) {
                yabawiArrayList.get(i).openYabawiCup();
            }
        }
    }
    private void allYabawiCupClose(){
        if(!viewCircleMovingAni1.isPlaying && !viewCircleMovingAni2.isPlaying) {
            for (int i = 0; i < yabawiArrayList.size(); i++) {
                yabawiArrayList.get(i).closeYabawicup();
            }
        }
    }

    public void gameStart(){
        if(!viewCircleMovingAni1.isPlaying&& !viewCircleMovingAni2.isPlaying){
            allYabawiCupClose();
            Yabawi []  yabawis = getRandomYabawi();
            startAnimationCircle(yabawis[0],yabawis[1]);
        }
    }

    private void resultYabawiGame(Boolean isHaveBall) {
        if (!viewCircleMovingAni1.isPlaying && !viewCircleMovingAni2.isPlaying) {
            if (!gameStart) {
                countDown_tv.setVisibility(View.VISIBLE);
                if (isHaveBall) {
                    countDown_tv.setText("정답입니다.");
                    score_tv += plusScore;
                    Util.saveData(this, "score", score_tv);
                    scoreTxt.setText(String.valueOf(score_tv));

                    yabwiSpeedUP();
                } else {
                    countDown_tv.setText("틀렸습니다.");
                    score_tv -= plusScore;
                    Util.saveData(this, "score", score_tv);
                    scoreTxt.setText(String.valueOf(score_tv));
                    yabwiSpeedDown();
                }
            }
        }
    }

    private void yabwiSpeedUP(){
       duration -= 0.4;
       plusScore += 5;
    }
    private void yabwiSpeedDown(){
       duration +=0.4;
       plusScore -= 5;
    }





    class GameStartReadyThread extends Thread{
        GameStartReadyHandler handler = new GameStartReadyHandler();
        @Override
        public void run(){
            super.run();
            for(int i=3; i>-1; i--){
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("i", i);
                message.setData(bundle);
                handler.sendMessage(message);
                bundle = null;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class GameStartReadyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            countDown_tv.setText(String.valueOf(msg.getData().getInt("i")));
            if(msg.getData().getInt("i") <= 0){
                countDown_tv.setVisibility(View.INVISIBLE);
                gameStart();
            }
        }
    }
}
