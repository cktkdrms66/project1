package com.example.proejct1.contact;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.model.Contact;
import com.example.proejct1.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private List<Boolean> ddabongs;
    private int checkIdx;
    public static String targetNumber;

    public ContactAdapter() {
        contacts = new ArrayList<>();
        ddabongs = new ArrayList<>();
        checkIdx = -1;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.contact_list_item, parent, false);
        return new ContactViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Contact item = contacts.get(position);
        holder.nameText.setText(item.getName());
        holder.numberText.setText(item.getNumber());

        if (ddabongs.get(position)) {
            holder.ddabondBtn.setVisibility(View.VISIBLE);
        } else {
            holder.ddabondBtn.setVisibility(View.GONE);
        }

        if (checkIdx == position) {
            holder.expandLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        } else {
            holder.expandLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setItems(List<Contact> items) {
        contacts = items;
        ddabongs = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            ddabongs.add(false);
            Util.saveData((Activity) MainActivity.context, "contact" + i, contacts.get(i).getName() + "/" + contacts.get(i).getNumber());
        }

        Util.saveData((Activity) MainActivity.context, "contact_count", contacts.size());
        Random random = new Random();
        for (int i = 0; i < ddabongs.size(); i++) {
            int value = random.nextInt(100);
            if (Util.getData((Activity) MainActivity.context, "contact_ddabong" + i, 0) == 0) {
                if (value > 50) {
                    ddabongs.set(i, true);
                }
            }

        }
    }

    public static void callTarget() {
        MainActivity.context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + targetNumber)));
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView numberText;
        LinearLayout background;
        LinearLayout expandLayout;

        CircleImageView callBtn;
        CircleImageView deleteBtn;
        ImageView ddabondBtn;

        ContactViewHolder(Context context, View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.contact_name);
            numberText = itemView.findViewById(R.id.contact_number);
            background = itemView.findViewById(R.id.contact_background);

            expandLayout = itemView.findViewById(R.id.expand_layout);
            callBtn = itemView.findViewById(R.id.call_btn);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
            ddabondBtn = itemView.findViewById(R.id.dda_btn);

            callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    targetNumber = contacts.get(getAdapterPosition()).getNumber();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions((Activity) MainActivity.context, new String[]{Manifest.permission.CALL_PHONE}
                                    ,MainActivity.PERMISSIONS_REQUEST_CALL_PHONE);
                        } else {
                            callTarget();
                        }
                    } else {
                        callTarget();
                    }

                }
            });

            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isExpand = expandLayout.getLayoutParams().height != 0;

                    if (checkIdx == getAdapterPosition()) {
                        checkIdx = -1;
                        changeVisibility(!isExpand);
                    } else {
                        if (checkIdx >= 0) {
                            notifyItemChanged(checkIdx);
                        }
                        checkIdx = getAdapterPosition();

                        changeVisibility(!isExpand);
                    }

                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("삭제하시겠습니까?")
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    contacts.remove(getAdapterPosition());
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                    AlertDialog alertDialog = builder.create();

                    alertDialog.show();

                }
            });

            ddabondBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int score = Util.getData((Activity) context, "score", 0) + 10;
                    Util.saveData((Activity) context, "score",
                            score);
                    Util.saveData((Activity) context, "contact_ddabong" + getAdapterPosition(), 1);
                    ddabondBtn.setVisibility(View.GONE);
                    ddabongs.set(getAdapterPosition(), false);
                    ((MainActivity) MainActivity.context).setGlobalScore(score);
                }
            });
        }

        public void changeVisibility(final boolean isExpanded) {
            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 150) : ValueAnimator.ofInt(150, 0);
            // Animation이 실행되는 시간, n/1000초
            va.setDuration(300);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // imageView의 높이 변경
                    expandLayout.getLayoutParams().height = (int) animation.getAnimatedValue();
                    expandLayout.requestLayout();
                    // imageView가 실제로 사라지게하는 부분

                }
            });
            // Animation start
            va.start();
        }
    }
}
