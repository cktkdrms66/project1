package com.example.proejct1.contact;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    TextView nameText;
    TextView numberText;
    LinearLayout background;

    ContactViewHolder(Context context, View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.contact_name);
        numberText = itemView.findViewById(R.id.contact_number);
        background = itemView.findViewById(R.id.contact_background);
    }
}