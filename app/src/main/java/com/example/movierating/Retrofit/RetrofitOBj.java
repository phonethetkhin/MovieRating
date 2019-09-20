package com.example.movierating.Retrofit;

import com.google.gson.Gson;

import java.util.BitSet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitOBj {
    public static final String BASE_URL="https://api.themoviedb.org/3/";
public static Retrofit retrofit=null;

public static Retrofit getRetrofit()
    {
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}

