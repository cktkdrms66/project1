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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.activity.GameActivity;
import com.example.proejct1.activity.GameActivity2;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.R;
import com.example.proejct1.model.Person;
import com.example.proejct1.person.PersonAdapter;
import com.example.proejct1.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {

    private RecyclerView recyclerView;
    private PersonAdapter adapter = new PersonAdapter();
    Button button1;
    Button button2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment3, container, false);

        findViewByIds(v);
        setRecyclerView();

        ((MainActivity) getActivity()).setPersonData();

        button1 = v.findViewById(R.id.button1);
        button2 = v.findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                getActivity().startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity2.class);
                getActivity().startActivity(intent);
            }
        });


        return v;
    }

    private void findViewByIds(View v) {
        recyclerView = v.findViewById(R.id.person_recyclerview);
    }

    public void setPersons(List<Person> persons) {
        adapter.setItems(persons);
        adapter.notifyDataSetChanged();

    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), RecyclerView.VERTICAL, false)) ; // 상하 스크롤r
        adapter.setItems(new ArrayList<Person>());
        recyclerView.setAdapter(adapter);
    }




}

