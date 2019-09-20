package com.example.movierating.Models;

import java.util.List;

public class GenreRespondModel {
    List<GenreModel> genres;

    public GenreRespondModel(List<GenreModel> genres) {
        this.genres = genres;
    }

    public List<GenreModel> getGenres() {
        return genres;
    }

    public static class GenreModel
    {
        private int id;
        private String name;

        public GenreModel(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
