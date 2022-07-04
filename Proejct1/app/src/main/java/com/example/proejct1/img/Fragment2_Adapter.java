package com.example.proejct1.img;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proejct1.R;
import com.example.proejct1.activity.MainActivity;
import com.example.proejct1.fragment.Fragment2;
import com.example.proejct1.model.Img_Data;
import com.example.proejct1.util.Util;

import java.util.ArrayList;
import java.util.Random;

public class Fragment2_Adapter extends RecyclerView.Adapter<Fragment2_Adapter.CustomViewHolder> {


    private ArrayList<Img_Data> arrayList;


    public Fragment2_Adapter(ArrayList<Img_Data> arrayList) {
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

        holder.img1.setImageResource(arrayList.get(position).getImg1());

        holder.itemView.setTag(position);




    }

    @Override
    public int getItemCount() {

        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView img2;
        protected ImageView img1;
        protected ImageView img3;
        protected ImageView img4;
        protected ImageView img5;
        protected ImageView img6;
        protected ImageView img7;
        protected ImageView img8;
        protected ImageView img9;
        protected ImageView img10;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img1 = (ImageView) itemView.findViewById(R.id.img1);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    remove(getAdapterPosition());
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int score = Util.getData((Activity) MainActivity.context, "score", 0) - 10 + new Random().nextInt(20);
                    Util.saveData((Activity) MainActivity.context, "score",
                            score);
                    ((MainActivity) MainActivity.context).setGlobalScore(score);
                    remove(getAdapterPosition());
                }
            });

        }
    }
}
