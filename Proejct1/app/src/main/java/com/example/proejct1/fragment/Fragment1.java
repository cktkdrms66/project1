package com.example.proejct1.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.activity.GameActivity2;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.contact.ContactAdapter;
import com.example.proejct1.model.Contact;
import com.example.proejct1.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ImageView noImage;

    private ContactAdapter adapter = new ContactAdapter();

    private int count = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment1, container, false);

        findViewByIds(v);

        setViews();
        setRecyclerView();

        return v;
    }

    private void findViewByIds(View v) {
        recyclerView = v.findViewById(R.id.contact_recyclerview);
        floatingActionButton = v.findViewById(R.id.float_btn);
        noImage = v.findViewById(R.id.no_image);
    }

    private void setViews() {

        if (Util.getData((Activity) MainActivity.context, "contact_count", 0) != 0) {
            floatingActionButton.setVisibility(View.GONE);
            noImage.setVisibility(View.GONE);
        } else {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).callContactPermission();
                    floatingActionButton.setVisibility(View.GONE);
                    noImage.setVisibility(View.INVISIBLE);
                }
            });

            noImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("qwe");
                    count++;

                    if (count == 5) {
                        int score = Util.getData((Activity) MainActivity.context, "score", 0) + 10000;
                        Util.saveData((Activity) MainActivity.context, "score",
                                score);
                        ((MainActivity) MainActivity.context).setGlobalScore(score);

                        count = 0;
                    }

                }
            });
        }
    }


    public void setContacts(List<Contact> contacts) {
        adapter.setItems(contacts);
        adapter.notifyDataSetChanged();

    }

    private void setRecyclerView() {
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), RecyclerView.VERTICAL, false)) ; // ?????? ?????????r

        int userCount = Util.getData((Activity) MainActivity.context, "contact_count", 0);
        List<Contact> contacts = new ArrayList<>();
        if (userCount != 0) {
            for (int i = 0; i < userCount; i++) {
                String userStr = Util.getData((Activity) MainActivity.context, "contact" + i, "");
                String[] userInfos = userStr.split("/");
                contacts.add(new Contact(userInfos[0], userInfos[1]));
            }
        }
        adapter.setItems(contacts);

        recyclerView.setAdapter(adapter);
    }

}
