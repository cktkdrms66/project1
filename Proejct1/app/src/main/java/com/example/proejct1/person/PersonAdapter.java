package com.example.proejct1.person;

import static com.example.proejct1.util.Util.getStrWithHashTag;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proejct1.R;
import com.example.proejct1.activity.GameActivity;
import com.example.proejct1.activity.GameActivity2;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.activity.PersonListActivity;
import com.example.proejct1.model.Person;
import com.example.proejct1.util.Util;

import java.util.ArrayList;
import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

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

        String txt = getStrWithHashTag(item.getName());

        Glide.with(this.context).load(item.getImg()).into(holder.personImg);

        if (Util.getData((Activity) context, "user" + holder.getAdapterPosition(), 0) == 1) {
            holder.pic.setVisibility(View.GONE);
            txt = getStrWithHashTag(item.getName()) + getStrWithHashTag(item.getUniversity())
                    + getStrWithHashTag(item.getSex())
                    + getStrWithHashTag(item.getAge());
        } else {
            holder.pic.setVisibility(View.VISIBLE);
        }

        holder.personText.setText(txt);

        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = Util.getData((Activity) context, "score", 0);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                int userCount = Util.getData((Activity) context, "user_count", 0);

                int needScore = userCount * 20 + 10;

                builder.setMessage(needScore + "?????? ????????? ?????????????????????????")
                        .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (score >= needScore) {
                                    KonfettiView konfettiView = ((PersonListActivity) PersonListActivity.context).konfettiView;
                                    konfettiView.build()
                                            .addColors(Color.BLUE, Color.CYAN, Color.MAGENTA)
                                            .setDirection(0.0, 359.0)
                                            .setSpeed(1f, 13f)
                                            .setFadeOutEnabled(true)
                                            .setTimeToLive(1000L)
                                            .addShapes(Shape.RECT, Shape.CIRCLE)
                                            .addSizes(new Size(12, 5))
                                            .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                                            .streamFor(300, 2000L);

                                    score -= needScore;
                                    Util.saveData((Activity) context, "score", score);
                                    ((MainActivity) MainActivity.context).setGlobalScore(score);
                                    ((PersonListActivity) PersonListActivity.context).setGlobalScore(score);
                                    holder.pic.setVisibility(View.GONE);
                                    String txt = getStrWithHashTag(item.getName()) + getStrWithHashTag(item.getUniversity())
                                            + getStrWithHashTag(item.getSex())
                                            + getStrWithHashTag(item.getAge());
                                    holder.personText.setText(txt);
                                    Util.saveData((Activity) context, "user" + holder.getAdapterPosition(), 1);

                                    int resultUserCount = userCount + 1;
                                    Util.saveData((Activity) context, "user_count", resultUserCount);
                                    ((PersonListActivity) PersonListActivity.context).setUserCount(resultUserCount);
                                    ((PersonListActivity) PersonListActivity.context).setUserCount(resultUserCount);

                                    if (resultUserCount == 20) {
                                        Dialog dialog = new Dialog(PersonListActivity.context);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        dialog.setContentView(R.layout.dialog_cele);
                                        dialog.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                dialog.cancel();
                                            }
                                        });
                                        dialog.show();
                                    }
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("????????? ???????????????")
                                            .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                }
                                            });
                                    AlertDialog alertDialog = builder.create();

                                    alertDialog.show();
                                }
                            }
                        })
                        .setNegativeButton("??????", new DialogInterface.OnClickListener() {
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
