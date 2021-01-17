package com.example.moviedb_mvp.Model.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {


    @SerializedName("title")
    public String title;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("id")
    public int id;

    @SerializedName("genre_ids")
    public List<Integer> genre_ids;

    public MovieList(String title, String poster_path,int id,List<Integer>genre_ids) {
        this.title = title;
        this.poster_path = poster_path;
        this.id=id;
        this.genre_ids=genre_ids;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
