package com.example.movierating.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movierating.DB.MovieDB;
import com.example.movierating.Models.GenreRespondModel;
import com.example.movierating.Models.HorizontalModel;
import com.example.movierating.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailedHorizontalAdapter extends RecyclerView.Adapter<DetailedHorizontalAdapter.ViewHolder>{
    List<HorizontalModel> horizontalModelList;
    Context context;

    public DetailedHorizontalAdapter(List<HorizontalModel> horizontalModelList, Context context) {
        this.horizontalModelList = horizontalModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.detailedhorizontallistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        h.tvName.setText(horizontalModelList.get(position).getName());
        h.tvRating.setText(horizontalModelList.get(position).getRating()+"");
        Picasso.get().load("https://image.tmdb.org/t/p/original"+horizontalModelList.get(position).getPhoto()).into(h.imgPhoto);
        MovieDB mdb= new MovieDB(context);
        List<GenreRespondModel.GenreModel> genreModelList=mdb.getGenreByIds(horizontalModelList.get(position).getGenres());
        String genreNames = "";
        for(int i = 0; i<genreModelList.size(); i++){
            if(i< genreModelList.size()-1)
                genreNames += genreModelList.get(i).getName()+", ";
            else
                genreNames += genreModelList.get(i).getName();
        }
        h.tvGenre.setText(genreNames);
    }

    @Override
    public int getItemCount() {
        return horizontalModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
ImageView imgPhoto;
TextView tvName,tvGenre,tvRating;
        public ViewHolder(@NonNull View v) {
            super(v);
            imgPhoto=v.findViewById(R.id.imgPhoto);
            tvName=v.findViewById(R.id.tvName);
            tvGenre=v.findViewById(R.id.tvGenre);
            tvRating=v.findViewById(R.id.tvRating);
        }
    }
}
