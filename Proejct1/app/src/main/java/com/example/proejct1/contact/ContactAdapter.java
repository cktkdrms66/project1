package com.example.proejct1.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter() {
        contacts = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact item = contacts.get(position);
        holder.nameText.setText(item.getName());
        holder.numberText.setText(item.getNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setItems(List<Contact> items) {
        contacts = items;
    }

}
