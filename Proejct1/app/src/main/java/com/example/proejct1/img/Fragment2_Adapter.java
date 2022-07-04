package com.example.proejct1.img;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.proejct1.R;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.fragment.Fragment2;
import com.example.proejct1.model.Img_Data;
import com.example.proejct1.util.Util;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment2_Adapter extends RecyclerView.Adapter<Fragment2_Adapter.CustomViewHolder> {


    private ArrayList<Img_Data> arrayList;


    public Fragment2_Adapter(ArrayList<Img_Data> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Fragment2_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment2_item_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Fragment2_Adapter.CustomViewHolder holder, int position) {

        holder.img1.setImageResource(arrayList.get(position).getImg1());

        holder.itemView.setTag(position);




    }

    @Override
    public int getItemCount() {

        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView img2;
        protected ImageView img1;
        protected ImageView img3;
        protected ImageView img4;
        protected ImageView img5;
        protected ImageView img6;
        protected ImageView img7;
        protected ImageView img8;
        protected ImageView img9;
        protected ImageView img10;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img1 = (ImageView) itemView.findViewById(R.id.img1);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    remove(getAdapterPosition());
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dialog = new Dialog(MainActivity.context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_gamble);

                    ((TextView)dialog.findViewById(R.id.gamble_desc)).setText("20점이 차감되지만 좋은 일이 생길지도...?");

                    LottieAnimationView lottieView = ((LottieAnimationView)dialog.findViewById(R.id.lottieView));


                    lottieView.setMinAndMaxFrame(0, 12);
                    lottieView.playAnimation();


                    dialog.findViewById(R.id.gamble_cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });


                    dialog.findViewById(R.id.gamble_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int originalScore = Util.getData((Activity) MainActivity.context, "score", 0);
                            if (originalScore < 20) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.context);
                                builder.setMessage("점수가 부족합니다!")
                                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                            }
                                        });
                                AlertDialog alertDialog = builder.create();

                                alertDialog.show();
                                return;
                            }

                            dialog.findViewById(R.id.gamble_button).setClickable(false);

                            ((TextView)dialog.findViewById(R.id.gamble_count)).setText("두근두근...");

                            dialog.findViewById(R.id.gamble_cancel).setVisibility(View.INVISIBLE);


                            lottieView.setMinAndMaxFrame(0, 50);
                            lottieView.loop(false);
                            lottieView.playAnimation();

                            Util.saveData((Activity) MainActivity.context, "score",
                                    originalScore - 20);

                            int value = new Random().nextInt(100);

                            originalScore = Util.getData((Activity) MainActivity.context, "score", 0);

                            int plusScore = 0;
                            if (value < 2) {
                                plusScore = 0;
                            } else if (value < 20) {
                                plusScore = 30;
                            } else if (value < 50) {
                                plusScore = -30;
                            } else if (value < 80) {
                                plusScore = 50;
                            } else if (value < 95) {
                                plusScore = -50;
                            } else {
                                plusScore = 10000;
                            }

                            Util.saveData((Activity) MainActivity.context, "score",
                                    originalScore + plusScore);
                            ((MainActivity) MainActivity.context).setGlobalScore(originalScore + plusScore);

                            Timer timer = new Timer();
                            int finalPlusScore = plusScore;
                            int finalOriginalScore = originalScore;
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    String text = finalPlusScore + "점 획득!!!";
                                    if (finalPlusScore < 0) {
                                        text = ((-1) * finalPlusScore) + "점 꼴으셨습니다";
                                    }

                                    String finalText = text;
                                    ((MainActivity) MainActivity.context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((TextView)dialog.findViewById(R.id.gamble_count)).setText(finalText);

                                            dialog.findViewById(R.id.gamble_button).setClickable(true);
                                            dialog.findViewById(R.id.gamble_button).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    ((MainActivity) MainActivity.context).setGlobalScore(finalOriginalScore + finalPlusScore);
                                                    dialog.cancel();
                                                }
                                            });
                                        }
                                    });

                                }
                            }, 2000);

                        }
                    });

                    dialog.show();
                }
            });

        }
    }
}
