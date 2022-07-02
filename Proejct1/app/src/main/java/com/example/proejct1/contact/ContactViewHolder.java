package com.example.proejct1.contact;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    TextView nameText;
    TextView numberText;
    LinearLayout background;
    LinearLayout expandLayout;

    ImageButton callBtn;
    ImageButton messageBtn;
    ImageButton deleteBtn;

    ContactViewHolder(Context context, View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.contact_name);
        numberText = itemView.findViewById(R.id.contact_number);
        background = itemView.findViewById(R.id.contact_background);

        expandLayout = itemView.findViewById(R.id.expand_layout);
        callBtn = itemView.findViewById(R.id.call_btn);
        messageBtn = itemView.findViewById(R.id.message_btn);
        deleteBtn = itemView.findViewById(R.id.delete_btn);

    }
}