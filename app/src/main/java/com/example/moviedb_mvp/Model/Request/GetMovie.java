package com.example.moviedb_mvp.Model.Request;

public class GetMovie {
    public int movie_id;

    public GetMovie(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }
}
