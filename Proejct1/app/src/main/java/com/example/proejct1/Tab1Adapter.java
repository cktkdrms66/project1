package com.example.proejct1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab1Adapter extends RecyclerView.Adapter<Tab1ViewHolder> {

    private ArrayList<String> accounts;

    public Tab1Adapter() {
        accounts = new ArrayList<>();
    }

    @NonNull
    @Override
    public Tab1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.tab_1_item, parent, false);
        return new Tab1ViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tab1ViewHolder holder, int position) {
        holder.textView.setText(accounts.get(position));
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public void setItems(ArrayList<String> items) {
        accounts = items;
    }

}
