package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movierating.Adapters.DetailedHorizontalAdapter;
import com.example.movierating.DB.MovieDB;
import com.example.movierating.Models.GenreRespondModel;
import com.example.movierating.Models.HorizontalModel;
import com.example.movierating.Models.MovieResponseModel;
import com.example.movierating.Models.VerticalModel;
import com.example.movierating.Retrofit.APIs;
import com.example.movierating.Retrofit.RetrofitOBj;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity {
int MovieID;
int[] Genres;
ImageView imgCoverPhoto,imgProfilePhoto;
RecyclerView rvDetailed;
TextView tvMovieName,tvReleasedDate,tvPopularity,tvOriginalLanguage,tvVoteCount,tvVoteAverage,tvGenre,tvOverView;

APIs apicall;
List<MovieResponseModel.MovieModel> movieModelList=new ArrayList<>();
List<HorizontalModel> SimilarList=new ArrayList<>();
List<HorizontalModel> RecommendedList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        MovieID=getIntent().getIntExtra("mid",0);
        Genres=getIntent().getIntArrayExtra("genre");
        imgCoverPhoto=findViewById(R.id.imgCoverPhoto);
        imgProfilePhoto=findViewById(R.id.imgProfilePhoto);
        rvDetailed=findViewById(R.id.rvDetailed);
        tvMovieName=findViewById(R.id.tvMovieName);
        tvReleasedDate=findViewById(R.id.tvReleasedDate);
        tvPopularity=findViewById(R.id.tvGetPopularity);
        tvOriginalLanguage=findViewById(R.id.tvOriginalLanguage);
        tvVoteCount=findViewById(R.id.tvVoteCount);
        tvVoteAverage=findViewById(R.id.tvVoteAverage);
        tvGenre=findViewById(R.id.tvGenre);
        tvOverView=findViewById(R.id.tvOverView);
        DetailedCall();
    }
    public void SimilarMovieCall()
    {
        apicall=RetrofitOBj.getRetrofit().create(APIs.class);
        Call<MovieResponseModel> movieResponseModelCall=apicall.GetSimilar(MovieID,"f17a830b443f5f3c5bcc8c13863083b1","en_US");
        movieResponseModelCall.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                MovieResponseModel movieResponseModel=response.body();
                movieModelList=movieResponseModel.getResults();
                for (int i = 0; i < movieModelList.size(); i++) {
                    HorizontalModel horizontalModel = new HorizontalModel(movieModelList.get(i).getPoster_path(), movieModelList.get(i).getTitle(), movieModelList.get(i).getGenre_ids(),
                            movieModelList.get(i).getVote_average(),movieModelList.get(i).getId());
        SimilarList.add(horizontalModel);
                }
                rvDetailed.setLayoutManager(new LinearLayoutManager(DetailedActivity.this,RecyclerView.HORIZONTAL,false));
                rvDetailed.setHasFixedSize(true);
                rvDetailed.setAdapter(new DetailedHorizontalAdapter(SimilarList,DetailedActivity.this));

            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {

            }
        });
    }
    private void DetailedCall()
    {
        apicall= RetrofitOBj.getRetrofit().create(APIs.class);
        Call<MovieResponseModel.MovieModel> movieResponseModelCall=apicall.GetDetail(MovieID,"f17a830b443f5f3c5bcc8c13863083b1","en_US");
        movieResponseModelCall.enqueue(new Callback<MovieResponseModel.MovieModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel.MovieModel> call, Response<MovieResponseModel.MovieModel> response) {
                if(response.isSuccessful()) {
                    MovieResponseModel.MovieModel mmodel = response.body();
                    Picasso.get().load("https://image.tmdb.org/t/p/original" + mmodel.getBackdrop_path()).into(imgCoverPhoto);
                    Picasso.get().load("https://image.tmdb.org/t/p/original"+mmodel.getPoster_path()).into(imgProfilePhoto);
                    tvMovieName.setText(mmodel.getTitle());
                    tvReleasedDate.setText("Release Date : "+mmodel.getRelease_date());
                    tvPopularity.setText("Popularity : "+mmodel.getPopularity());
                    tvVoteCount.setText(mmodel.getVote_count()+" Vote Counts");
                    tvVoteAverage.setText("★ "+mmodel.getVote_average());
                    tvOriginalLanguage.setText("Original Language : "+mmodel.getOriginal_language());
                    tvOverView.setText(mmodel.getOverview());

                    MovieDB mdb=new MovieDB(DetailedActivity.this);
                    List<GenreRespondModel.GenreModel> genreModelList=mdb.getGenreByIds(Genres);

                    String genreNames = "";
                    for(int i = 0; i<genreModelList.size(); i++){
                        if(i< genreModelList.size()-1)
                            genreNames += genreModelList.get(i).getName()+", ";
                        else
                            genreNames += genreModelList.get(i).getName();
                    }
                    tvGenre.setText(genreNames);

                    SimilarMovieCall();

                }

            }

            @Override
            public void onFailure(Call<MovieResponseModel.MovieModel> call, Throwable t) {

            }
        });

    }



}
