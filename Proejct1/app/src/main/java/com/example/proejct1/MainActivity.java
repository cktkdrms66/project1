package com.example.proejct1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proejct1.img.Fragment2_Adapter;
import com.example.proejct1.model.Img_Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Img_Data> arrayList;
    private Fragment2_Adapter fragment2_adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment2);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        arrayList = new ArrayList<Img_Data>();
        fragment2_adapter = new Fragment2_Adapter(arrayList);
        recyclerView.setAdapter(fragment2_adapter);

        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.add(new Img_Data(R.drawable.one));
                arrayList.add(new Img_Data(R.drawable.two));
                arrayList.add(new Img_Data(R.drawable.three));
                arrayList.add(new Img_Data(R.drawable.four));
                arrayList.add(new Img_Data(R.drawable.five));
                arrayList.add(new Img_Data(R.drawable.six));
                arrayList.add(new Img_Data(R.drawable.seven));
                arrayList.add(new Img_Data(R.drawable.eight));
                arrayList.add(new Img_Data(R.drawable.nine));
                arrayList.add(new Img_Data(R.drawable.ten));
                arrayList.add(new Img_Data(R.drawable.eleven));
                arrayList.add(new Img_Data(R.drawable.thirteen));
                arrayList.add(new Img_Data(R.drawable.fourteen));
                arrayList.add(new Img_Data(R.drawable.fifteen));
                arrayList.add(new Img_Data(R.drawable.sixteen));
                arrayList.add(new Img_Data(R.drawable.seventeen));
                arrayList.add(new Img_Data(R.drawable.eighteen));
                arrayList.add(new Img_Data(R.drawable.nineteen));
                arrayList.add(new Img_Data(R.drawable.twenty));
                fragment2_adapter.notifyDataSetChanged();
            }
        });

    }
}