package com.example.moviedb_mvp.Service;

import com.example.moviedb_mvp.Model.Response.BaseResponse;
import com.example.moviedb_mvp.Model.Response.MovieDetail;
import com.example.moviedb_mvp.Model.Response.MovieList;
import com.example.moviedb_mvp.Utils.AppConstant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMovieService {

    @GET(AppConstant.TOP_RATED_MOVIE+AppConstant.API_KEY)
    Observable<BaseResponse> getTopRatedMovieList();

    @GET(AppConstant.UPCOMING_MOVIE+AppConstant.API_KEY)
    Observable<BaseResponse> getUpComingMovieList();

    @GET(AppConstant.NOW_PLAYING_MOVIE+AppConstant.API_KEY)
    Observable<BaseResponse> getNowPlayingMovieList();


    @GET(AppConstant.MOVIE_DETAIL+AppConstant.API_KEY)
    Observable<MovieDetail> getmovieDetail(@Path("movie_id") int movie_id);

    @GET(AppConstant.MOVIE_SEARCH+AppConstant.API_KEY)
    Observable<BaseResponse> getMovieSearch(@Query("query") String query);
}
