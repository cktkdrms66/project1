package com.example.proejct1.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.model.Person;
import com.example.proejct1.person.PersonAdapter;
import com.example.proejct1.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;

public class PersonListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public TextView scoreTxt;
    private PersonAdapter adapter = new PersonAdapter();

    public KonfettiView konfettiView;

    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        context = this;

        findViewByIds();
        setRecyclerView();

        scoreTxt.setText(String.valueOf(Util.getData(this, "score", 0)));

    }

    @Override
    protected void onDestroy() {
        context = null;
        super.onDestroy();
    }

    private void findViewByIds() {
        recyclerView = findViewById(R.id.person_recyclerview);
        konfettiView = findViewById(R.id.viewKonfetti);
        scoreTxt = findViewById(R.id.global_score_txt2);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤r

        List<Person> persons = new ArrayList<>();
        String personsTxt = Util.readRawTxt(this, R.raw.persons);

        ObjectMapper mapper = new ObjectMapper();

        try {
             persons = mapper.readValue(personsTxt, new TypeReference<List<Person>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter.setItems(persons);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    public void setGlobalScore(int score) {
        scoreTxt.setText(String.valueOf(score));
    }
}
