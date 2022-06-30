package com.example.proejct1.img;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.fragment.Fragment2;

import java.util.ArrayList;

public class Fragment2_Adapter extends RecyclerView.Adapter<Fragment2_Adapter.CustomViewHolder> {


    private ArrayList<Fragment2> arrayList;

    public Fragment2_Adapter(ArrayList<Fragment2> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Fragment2_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment2_item_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Fragment2_Adapter.CustomViewHolder holder, int position) {

        holder.iv_profile.setImageResource(arrayList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.img1);
        }
    }
}
