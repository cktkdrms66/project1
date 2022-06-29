package com.example.proejct1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.contact.ContactAdapter;
import com.example.proejct1.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment1, container, false);
        recyclerView = v.findViewById(R.id.contact_recyclerview);

        setRecyclerView();

        return v;
    }

    public void setContacts(List<Contact> contacts) {
        adapter.setItems(contacts);
        adapter.notifyDataSetChanged();

    }

    private void setRecyclerView() {
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), RecyclerView.VERTICAL, false)) ; // 상하 스크롤r


        adapter = new ContactAdapter();

        adapter.setItems(new ArrayList<Contact>());

        recyclerView.setAdapter(adapter);
    }



}
