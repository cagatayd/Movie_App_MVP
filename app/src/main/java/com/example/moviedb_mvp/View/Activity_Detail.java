package com.example.moviedb_mvp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviedb_mvp.Adapter.MovieAdapter;
import com.example.moviedb_mvp.Model.Response.MovieDetail;
import com.example.moviedb_mvp.Presenter.MoviePresenter;
import com.example.moviedb_mvp.R;
import com.example.moviedb_mvp.Repository.Irepository;
import com.example.moviedb_mvp.Utils.AppConstant;
import com.squareup.picasso.Picasso;

public class Activity_Detail extends AppCompatActivity implements Irepository.DetailView {

    private ProgressBar progressBar;
    private TextView textViewTitle, textViewVote, textViewoverview, textViewpopularity;
    private AppCompatImageView detailposter;
    private MoviePresenter moviePresenter;
    private MovieDetail movieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detail);

        int movieId = getIntent().getIntExtra("movieid", 0);
        init();
        moviePresenter = new MoviePresenter(null, Activity_Detail.this);
        moviePresenter.FetchMoviesDetail(movieId);

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if (progressBar != null && progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void init() {
        progressBar = findViewById(R.id.progressBar);
        textViewTitle = findViewById(R.id.title);
        textViewVote = findViewById(R.id.vote);
        detailposter = findViewById(R.id.detailposter);
        textViewoverview = findViewById(R.id.overview);
        textViewpopularity = findViewById(R.id.popularity);

    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(Activity_Detail.this, errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMovie(MovieDetail movieDetail) {

//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.build().load(AppConstant.BASE_PATH_OF_IMAGE  + movieDetail.getBackdrop_path()).into(detailposter);
        Glide.with(getApplicationContext()).load(AppConstant.BASE_PATH_OF_IMAGE + movieDetail.getBackdrop_path()).into(detailposter);

        textViewTitle.setText(movieDetail.title);
        textViewVote.setText("\t" + getResources().getString(R.string.vote_count_hint) + String.valueOf(movieDetail.getVote_count()) + "\t" + (getResources().getString(R.string.vote_average_hint)) + "\t" + (movieDetail.vote_average));
        textViewpopularity.setText("\t" + getResources().getString(R.string.popularity) + (movieDetail.popularity) + "\t" + getResources().getString(R.string.language) + (movieDetail.original_language));
        textViewoverview.setText(movieDetail.overview);


    }
}