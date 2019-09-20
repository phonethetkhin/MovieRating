package com.example.movierating.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movierating.DB.MovieDB;
import com.example.movierating.DetailedActivity;
import com.example.movierating.Models.GenreRespondModel;
import com.example.movierating.Models.HorizontalModel;
import com.example.movierating.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {
    List<HorizontalModel> horizontalModelList;
    private  MovieDB db;

    public HorizontalAdapter(List<HorizontalModel> horizontalModelList, Context context) {
        this.horizontalModelList = horizontalModelList;
        db = new MovieDB(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontallistitem,parent,false);


        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, final int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/original"+horizontalModelList.get(position).getPhoto()).into(h.imgPhoto);
        h.tvName.setText(horizontalModelList.get(position).getName());
        h.tvRating.setText("★ "+horizontalModelList.get(position).getRating());
        List<GenreRespondModel.GenreModel> genreModel = db.getGenreByIds(horizontalModelList.get(position).getGenres());
        /*for(GenreRespondModel.GenreModel model : genreModel){
            h.tvGenre.setText(model.getName()+",");
        }*/

        String genreNames = "";
        for(int i = 0; i<genreModel.size(); i++){
            if(i< genreModel.size()-1)
                genreNames += genreModel.get(i).getName()+", ";
            else
                genreNames += genreModel.get(i).getName();
        }
        h.tvGenre.setText(genreNames);
        h.clHorizontalParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(), DetailedActivity.class);
                Bundle b=new Bundle();
                b.putIntArray("genre",horizontalModelList.get(position).getGenres());
              b.putInt("mid",horizontalModelList.get(position).getMovieID());
              i.putExtras(b);
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return horizontalModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName,tvGenre,tvRating;
        ConstraintLayout clHorizontalParent;

      public ViewHolder(@NonNull View v) {
          super(v);
          imgPhoto=v.findViewById(R.id.imgPhoto);
          tvName=v.findViewById(R.id.tvName);
          tvGenre=v.findViewById(R.id.tvGenre);
          tvRating=v.findViewById(R.id.tvRating);
            clHorizontalParent=v.findViewById(R.id.clHorizontalParent);
      }
  }
}
