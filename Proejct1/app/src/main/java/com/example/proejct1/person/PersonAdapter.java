package com.example.proejct1.person;

import static com.example.proejct1.util.util.getStrWithHashTag;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proejct1.R;
import com.example.proejct1.activity.GameActivity;
import com.example.proejct1.activity.GameActivity2;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.fragment.Fragment3;
import com.example.proejct1.model.Contact;
import com.example.proejct1.model.Person;
import com.example.proejct1.model.University;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private List<Person> persons;
    private Context context;

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
