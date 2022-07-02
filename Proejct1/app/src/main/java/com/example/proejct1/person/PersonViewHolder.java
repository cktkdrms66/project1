package com.example.proejct1.person;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;

public class PersonViewHolder extends RecyclerView.ViewHolder {

    ImageView personImg;
    TextView personText;


    PersonViewHolder(Context context, View itemView) {
        super(itemView);
        personImg = itemView.findViewById(R.id.person_img);
        personText = itemView.findViewById(R.id.person_info_txt);
    }
}