package com.example.proejct1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class Tab1ViewHolder extends RecyclerView.ViewHolder {

    TextView textView;


    Tab1ViewHolder(Context context, View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.name);
    }
}
