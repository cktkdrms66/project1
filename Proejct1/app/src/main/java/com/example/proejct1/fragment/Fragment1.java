package com.example.proejct1.fragment;

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
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.contact.ContactAdapter;
import com.example.proejct1.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ImageView noImage;

    private ContactAdapter adapter = new ContactAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment1, container, false);

        findViewByIds(v);

        setFloatingActionButton();
        setRecyclerView();

        return v;
    }

    private void findViewByIds(View v) {
        recyclerView = v.findViewById(R.id.contact_recyclerview);
        floatingActionButton = v.findViewById(R.id.float_btn);
        noImage = v.findViewById(R.id.no_image);
    }

    private void setFloatingActionButton() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).callContactPermission();
                floatingActionButton.setVisibility(View.INVISIBLE);
                noImage.setVisibility(View.INVISIBLE);
            }
        });
    }


    public void setContacts(List<Contact> contacts) {
        adapter.setItems(contacts);
        adapter.notifyDataSetChanged();

    }

    private void setRecyclerView() {
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), RecyclerView.VERTICAL, false)) ; // 상하 스크롤r

        adapter.setItems(new ArrayList<Contact>());

        recyclerView.setAdapter(adapter);
    }

}
