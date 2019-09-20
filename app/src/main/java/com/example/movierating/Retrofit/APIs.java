package com.example.movierating.Retrofit;

import com.example.movierating.Models.GenreRespondModel;
import com.example.movierating.Models.MovieResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIs {
    @GET("genre/movie/list")
    Call<GenreRespondModel> GetGenre(
            @Query("api_key") String APIKEY,
            @Query("language") String Language


    );
    @GET("movie/now_playing")
    Call<MovieResponseModel> GetNowPlayingMovie(
            @Query("api_key") String Apikey,
            @Query("language") String Language,
            @Query("page") int Page


    );
    @GET("movie/popular")
    Call<MovieResponseModel> GetPopularMovie(
            @Query("api_key") String Apikey,
            @Query("language") String Language,
            @Query("page") int Page


    );
    @GET("movie/top_rated")
    Call<MovieResponseModel> GetTopRatedMovie(

            @Query("api_key") String apikey,
            @Query("language") String Language,
            @Query("page") int Page
    );
    @GET("movie/upcoming")
    Call<MovieResponseModel> GetUpcomingMovie(

            @Query("api_key") String apikey,
            @Query("language") String Language,
            @Query("page") int Page
    );

    @GET("movie/{id}")
    Call<MovieResponseModel.MovieModel> GetDetail(
            @Path("id") int MovieID,
            @Query("api_key") String Apikey,
            @Query("language") String Language
    );
    @GET("movie/{id}/similar")
    Call<MovieResponseModel> GetSimilar(
            @Path("id") int MovieID,
            @Query("api_key") String Apikey,
            @Query("language") String Language
    );




}
