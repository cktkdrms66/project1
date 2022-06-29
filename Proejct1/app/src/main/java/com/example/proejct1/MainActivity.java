package com.example.proejct1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    Button button;

    ImageView img;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView1 = findViewById(R.id.name);
        button = findViewById(R.id.button111);
        img = findViewById(R.id.iuimg);
        recyclerView = findViewById(R.id.recylerview);


        textView1.setText("fjweipfwefpejfopwjfpewfpjewqopfpejowfpfsejfpisefjpsfpj");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.iu2);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤

        Tab1Adapter adapter = new Tab1Adapter();

        ArrayList<String> tests = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String str = i + "번째 아이템";
            tests.add(str);
        }

        adapter.setItems(tests);

        recyclerView.setAdapter(adapter);



    }
}