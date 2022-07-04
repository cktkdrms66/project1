package com.example.proejct1.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.activity.GameActivity;
import com.example.proejct1.activity.GameActivity2;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.R;
import com.example.proejct1.activity.PersonListActivity;
import com.example.proejct1.model.Person;
import com.example.proejct1.person.PersonAdapter;
import com.example.proejct1.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {

    CardView button1;
    CardView button2;
    CardView button3;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment3, container, false);

        button1 = v.findViewById(R.id.cardview1);
        button2 = v.findViewById(R.id.cardview2);
        button3 = v.findViewById(R.id.cardview3);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity2.class);
                getActivity().startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                getActivity().startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonListActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return v;
    }

}

