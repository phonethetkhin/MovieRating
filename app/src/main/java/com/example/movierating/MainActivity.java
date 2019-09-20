package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.movierating.Adapters.VerticalAdapter;
import com.example.movierating.DB.MovieDB;
import com.example.movierating.Models.GenreRespondModel;
import com.example.movierating.Models.HorizontalModel;
import com.example.movierating.Models.MovieResponseModel;
import com.example.movierating.Models.VerticalModel;
import com.example.movierating.Retrofit.APIs;
import com.example.movierating.Retrofit.RetrofitOBj;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvVertical;
    List<VerticalModel> verticalModelList = new ArrayList<>();
    List<HorizontalModel> NowPlaying = new ArrayList<>();
    List<HorizontalModel> Popular = new ArrayList<>();
    List<HorizontalModel> TopRated = new ArrayList<>();
    List<HorizontalModel> Upcoming = new ArrayList<>();
    List<MovieResponseModel.MovieModel> movieModelList = new ArrayList<>();

    APIs apicall;
    MovieDB db = new MovieDB(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvVertical = findViewById(R.id.rvVertical);
        GenreCall();
        NowPlayingMovieCall();



    }

    public void NowPlayingMovieCall() {
        apicall = RetrofitOBj.getRetrofit().create(APIs.class);
        Call<MovieResponseModel> mrModel = apicall.GetNowPlayingMovie("f17a830b443f5f3c5bcc8c13863083b1", "en_US", 1);
        mrModel.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                if (response.isSuccessful()) {

                    MovieResponseModel movieResponseModel = response.body();

                    movieModelList = movieResponseModel.getResults();
                    for (int i = 0; i < movieModelList.size(); i++) {
                        HorizontalModel horizontalModel = new HorizontalModel(movieModelList.get(i).getPoster_path(), movieModelList.get(i).getTitle(), movieModelList.get(i).getGenre_ids(),
                                movieModelList.get(i).getVote_average(),movieModelList.get(i).getId());
                        NowPlaying.add(horizontalModel);
                    }
                    verticalModelList.add(new VerticalModel("Now Playing", NowPlaying));
                    rvVertical.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    rvVertical.setHasFixedSize(true);
                    rvVertical.setAdapter(new VerticalAdapter(verticalModelList, MainActivity.this));
                    PopularMovieCall();

                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void GenreCall() {
        apicall = RetrofitOBj.getRetrofit().create(APIs.class);
        Call<GenreRespondModel> grModel = apicall.GetGenre("f17a830b443f5f3c5bcc8c13863083b1", "en_US");
        grModel.enqueue(new Callback<GenreRespondModel>() {
            @Override
            public void onResponse(Call<GenreRespondModel> call, Response<GenreRespondModel> response) {


                db.deleteAllGenre();
                db.AddGenre(response.body().getGenres());


            }

            @Override
            public void onFailure(Call<GenreRespondModel> call, Throwable t) {

            }
        });
    }

    public void PopularMovieCall() {
        apicall = RetrofitOBj.getRetrofit().create(APIs.class);
        Call<MovieResponseModel> movieResponseModelCall = apicall.GetPopularMovie("f17a830b443f5f3c5bcc8c13863083b1", "en_US", 1);
        movieResponseModelCall.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                if (response.isSuccessful()) {


                    MovieResponseModel mrModel = response.body();
                    movieModelList = mrModel.getResults();
                    for (int i = 0; i < movieModelList.size(); i++) {
                        HorizontalModel horizontalModel = new HorizontalModel(movieModelList.get(i).getPoster_path(), movieModelList.get(i).getTitle(), movieModelList.get(i).getGenre_ids(),
                                movieModelList.get(i).getVote_average(),movieModelList.get(i).getId());
                        Popular.add(horizontalModel);
                    }
                    verticalModelList.add(new VerticalModel("Popular Movies", Popular));
                    rvVertical.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    rvVertical.setHasFixedSize(true);
                    rvVertical.setAdapter(new VerticalAdapter(verticalModelList, MainActivity.this));

                    TopRatedCall();

                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {

            }
        });
    }

    public void TopRatedCall() {
        apicall = RetrofitOBj.getRetrofit().create(APIs.class);
        Call<MovieResponseModel> movieResponseModelCall = apicall.GetTopRatedMovie("f17a830b443f5f3c5bcc8c13863083b1", "en_US", 1);
        movieResponseModelCall.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                if (response.isSuccessful()) {


                    MovieResponseModel mrModel = response.body();
                    movieModelList = mrModel.getResults();
                    for (int i = 0; i < movieModelList.size(); i++) {
                        HorizontalModel horizontalModel = new HorizontalModel(movieModelList.get(i).getPoster_path(), movieModelList.get(i).getTitle(), movieModelList.get(i).getGenre_ids(),
                                movieModelList.get(i).getVote_average(),movieModelList.get(i).getId());
                        TopRated.add(horizontalModel);
                    }
                    verticalModelList.add(new VerticalModel("Top Rated Movie", TopRated));
                    rvVertical.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    rvVertical.setHasFixedSize(true);
                    rvVertical.setAdapter(new VerticalAdapter(verticalModelList, MainActivity.this));

UpcomingMovieCall();
                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {

            }
        });
    }
    public void UpcomingMovieCall() {
        apicall = RetrofitOBj.getRetrofit().create(APIs.class);
        Call<MovieResponseModel> movieResponseModelCall = apicall.GetUpcomingMovie("f17a830b443f5f3c5bcc8c13863083b1", "en_US", 1);
        movieResponseModelCall.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                if (response.isSuccessful()) {


                    MovieResponseModel mrModel = response.body();
                    movieModelList = mrModel.getResults();
                    for (int i = 0; i < movieModelList.size(); i++) {
                        HorizontalModel horizontalModel = new HorizontalModel(movieModelList.get(i).getPoster_path(), movieModelList.get(i).getTitle(), movieModelList.get(i).getGenre_ids(),
                                movieModelList.get(i).getVote_average(),movieModelList.get(i).getId());
                        Upcoming.add(horizontalModel);
                    }
                    verticalModelList.add(new VerticalModel("Upcoming Movies", Upcoming));
                    rvVertical.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    rvVertical.setHasFixedSize(true);
                    rvVertical.setAdapter(new VerticalAdapter(verticalModelList, MainActivity.this));


                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {

            }
        });
    }
}
