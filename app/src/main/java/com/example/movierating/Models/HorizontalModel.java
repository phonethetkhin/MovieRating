package com.example.movierating.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class HorizontalModel{
    private String Photo,Name;
    private int[]Genres;
    private double Rating;
    private int MovieID;

    public HorizontalModel(String photo, String name, int[] genres, double rating, int movieID) {
        Photo = photo;
        Name = name;
        Genres = genres;
        Rating = rating;
        MovieID = movieID;
    }

    public String getPhoto() {
        return Photo;
    }

    public String getName() {
        return Name;
    }

    public int[] getGenres() {
        return Genres;
    }

    public double getRating() {
        return Rating;
    }

    public int getMovieID() {
        return MovieID;
    }
}
