package com.example.movierating.Models;

import java.util.List;

public class MovieResponseModel {
    List<MovieModel> results;

    public MovieResponseModel(List<MovieModel> results) {
        this.results = results;
    }

    public List<MovieModel> getResults() {
        return results;
    }

    public static class MovieModel
    {
        private int id;
        private double popularity;
        private int vote_count;
        private String poster_path;
        private String backdrop_path;
        private String original_language;
        private int[] genre_ids;
        private String title;
        private double vote_average;
        private String overview;
        private String release_date;

        public MovieModel(int id, double popularity, int vote_count, String poster_path, String backdrop_path, String original_language,
                          int[] genre_ids, String title, double vote_average, String overview, String release_date) {
            this.id = id;
            this.popularity = popularity;
            this.vote_count = vote_count;
            this.poster_path = poster_path;
            this.backdrop_path = backdrop_path;
            this.original_language = original_language;
            this.genre_ids = genre_ids;
            this.title = title;
            this.vote_average = vote_average;
            this.overview = overview;
            this.release_date = release_date;
        }

        public int getId() {
            return id;
        }

        public double getPopularity() {
            return popularity;
        }

        public int getVote_count() {
            return vote_count;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public int[] getGenre_ids() {
            return genre_ids;
        }

        public String getTitle() {
            return title;
        }

        public double getVote_average() {
            return vote_average;
        }

        public String getOverview() {
            return overview;
        }

        public String getRelease_date() {
            return release_date;
        }
    }
}
