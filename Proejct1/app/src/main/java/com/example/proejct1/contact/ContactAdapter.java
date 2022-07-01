package com.example.proejct1.contact;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Contact> contacts;
    private static String targetNumber;

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

        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetNumber = contacts.get(position).getNumber();

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
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setItems(List<Contact> items) {
        contacts = items;
    }

    public static void callTarget() {
        MainActivity.context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + targetNumber)));
    }

}
