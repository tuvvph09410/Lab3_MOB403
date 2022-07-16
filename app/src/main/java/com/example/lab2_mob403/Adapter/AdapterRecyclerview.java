package com.example.lab2_mob403.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_mob403.Model.Movies;
import com.example.lab2_mob403.R;

import java.util.List;

public class AdapterRecyclerview extends RecyclerView.Adapter<AdapterRecyclerview.ViewHolder> {
    private List<Movies> movieList;

    public AdapterRecyclerview(List<Movies> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_Name.setText(movieList.get(position).getName());
        holder.tv_Phone.setText(movieList.get(position).getEmail());


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Name, tv_Phone, tv_Email;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tvName);
            tv_Phone = itemView.findViewById(R.id.tvPhone);
            tv_Email = itemView.findViewById(R.id.tvEmail);
        }
    }


}
