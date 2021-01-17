package com.example.moviedb_mvp.Repository;

import com.example.moviedb_mvp.Model.Response.MovieDetail;
import com.example.moviedb_mvp.Model.Response.MovieList;

import java.util.List;

public interface Irepository {

    interface View
    {
        void showProgressBar();
        void hideProgressBar();
        void init();
        void showError(String errorMsg);
        void loadList(List<MovieList>movieLists);
        void openDetailPage(int movie_id); // anasayfaya gelen filmleri id sine göre o filmin detayına yollıcağımız için

        
    }

    interface DetailView{
        void showProgressBar();
        void hideProgressBar();
        void init();
        void showError(String errorMsg);
        void loadMovie(MovieDetail movieDetail);
    }

     interface Presenter
    {
        void FetchMoviesDetail(int movie_id);
        void FetchTopRatedMovies();
        void FetchUpComingMovies();
        void FetchNowPlayingMovies();
        void searchMovie(String query);

    }


}
