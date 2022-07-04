package com.example.proejct1.person;

import static com.example.proejct1.util.Util.getStrWithHashTag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proejct1.R;
import com.example.proejct1.activity.GameActivity;
import com.example.proejct1.activity.GameActivity2;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.model.Person;
import com.example.proejct1.util.Util;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private List<Person> persons;
    private Context context;
    int score;


    public PersonAdapter() {
        persons = new ArrayList<>();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.person_cardview, parent, false);

        if (this.context == null) {
            this.context = context;
        }




        return new PersonViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person item = persons.get(position);

        String txt = getStrWithHashTag(item.getName())
                + getStrWithHashTag(item.getUniversity())
                + getStrWithHashTag(item.getSex())
                + getStrWithHashTag(item.getAge());

        Glide.with(this.context).load(item.getImg()).into(holder.personImg);
        holder.personText.setText(txt);

        if (Util.getData((Activity) context, "user" + holder.getAdapterPosition(), 0) == 1) {
            holder.pic.setVisibility(View.GONE);
        }

        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = Util.getData((Activity) context, "score", 0);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("20점을 사용해 확인하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (score >= 20) {
                                    score -= 20;
                                    Util.saveData((Activity) context, "score", score);
                                    ((MainActivity) MainActivity.context).setGlobalScore(score);
                                holder.pic.setVisibility(View.GONE);
                                Util.saveData((Activity) context, "user" + holder.getAdapterPosition(), 1);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("점수가 부족합니다")
                                            .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                }
                                            });
                                    AlertDialog alertDialog = builder.create();

                                    alertDialog.show();
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }

        });



        holder.personImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!item.getUrl().isEmpty()) {
                    Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                    context.startActivity(urlintent);
                } else {
                    Intent intent = new Intent(context, GameActivity.class);
                    context.startActivity(intent);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void setItems(List<Person> items) {
        persons = items;
    }
}
