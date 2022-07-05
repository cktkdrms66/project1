package com.example.proejct1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.img.Fragment2_Adapter;
import com.example.proejct1.model.Img_Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    private ArrayList<Img_Data> arrayList;
    private Fragment2_Adapter fragment2_adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.rv);
        gridLayoutManager = new GridLayoutManager(this.getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        arrayList = new ArrayList<Img_Data>();
        fragment2_adapter = new Fragment2_Adapter(arrayList);
        recyclerView.setAdapter(fragment2_adapter);

        FloatingActionButton btn_add = v.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                v.findViewById(R.id.no_image2).setVisibility(View.INVISIBLE);
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


        return v;
    }
}

