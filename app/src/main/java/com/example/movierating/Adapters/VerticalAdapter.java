package com.example.movierating.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movierating.Models.VerticalModel;
import com.example.movierating.R;

import java.util.List;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {
private List<VerticalModel> verticalModelList;
Context context;

    public VerticalAdapter(List<VerticalModel> verticalModelList, Context context) {
        this.verticalModelList = verticalModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.verticallistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(verticalModelList.get(position).getTitle());
        holder.rvHoriznotal.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        holder.rvHoriznotal.setHasFixedSize(true);
        holder.rvHoriznotal.setAdapter(new HorizontalAdapter(verticalModelList.get(position).getHorizontalModelList(), context));
    }

    @Override
    public int getItemCount() {
        return verticalModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerView rvHoriznotal;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvTitle=v.findViewById(R.id.tvTitle);
            rvHoriznotal=v.findViewById(R.id.rvHorizontal);
        }
    }
}
