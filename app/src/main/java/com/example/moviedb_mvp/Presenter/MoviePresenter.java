package com.example.moviedb_mvp.Presenter;

import com.example.moviedb_mvp.Model.Response.BaseResponse;
import com.example.moviedb_mvp.Model.Response.MovieDetail;
import com.example.moviedb_mvp.Model.Response.MovieList;
import com.example.moviedb_mvp.Repository.Irepository;
import com.example.moviedb_mvp.Service.IMovieService;
import com.example.moviedb_mvp.Utils.AppConstant;

import java.util.List;

import javax.net.ssl.SSLServerSocket;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviePresenter implements Irepository.Presenter {

    IMovieService iMovieService;

    Irepository.View mview;
    Irepository.DetailView detailView;

    public MoviePresenter( Irepository.View mview , Irepository.DetailView detailView) {
        this.mview = mview;
        this.detailView = detailView;


        iMovieService  = new Retrofit.Builder()
                .baseUrl(AppConstant.SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(IMovieService.class);
    }


    @Override
    public void FetchMoviesDetail(int movie_id) {
        iMovieService.getmovieDetail(movie_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MovieDetail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        detailView.showProgressBar();
                    }

                    @Override
                    public void onNext(@NonNull MovieDetail movieDetail) {
                        detailView.hideProgressBar();
                        detailView.loadMovie(movieDetail);
                       
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        detailView.hideProgressBar();
                        detailView.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void FetchTopRatedMovies() {


        iMovieService.getTopRatedMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mview.showProgressBar();
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse baseResponses) {
                        mview.hideProgressBar();
                        mview.loadList(baseResponses.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mview.hideProgressBar();
                        mview.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void FetchUpComingMovies() {

        iMovieService.getUpComingMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>(){

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        mview.showProgressBar();
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse baseResponse) {
                        mview.hideProgressBar();
                        mview.loadList(baseResponse.getResults());

                    }


                    @Override
                    public void onError(@NonNull Throwable e) {

                        mview.hideProgressBar();
                        mview.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });




    }

    @Override
    public void FetchNowPlayingMovies() {
        iMovieService.getNowPlayingMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>(){

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        mview.showProgressBar();
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse baseResponse) {
                        mview.hideProgressBar();
                        mview.loadList(baseResponse.getResults());

                    }


                    @Override
                    public void onError(@NonNull Throwable e) {

                        mview.hideProgressBar();
                        mview.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void searchMovie(String query) {

        iMovieService.getMovieSearch(query )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        mview.showProgressBar();
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse baseResponse) {
                        mview.hideProgressBar();
                        mview.loadList(baseResponse.getResults());

                    }


                    @Override
                    public void onError(@NonNull Throwable e) {

                        mview.hideProgressBar();
                        mview.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}


