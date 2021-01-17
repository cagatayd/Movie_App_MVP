package com.example.moviedb_mvp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviedb_mvp.Adapter.MovieAdapter;
import com.example.moviedb_mvp.Model.Response.MovieList;
import com.example.moviedb_mvp.Presenter.MoviePresenter;
import com.example.moviedb_mvp.R;
import com.example.moviedb_mvp.Repository.Irepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements Irepository.View , MovieAdapter.OnMovieItemClick {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private MoviePresenter moviePresenter;
    private SearchView searchView;
    private List<Movie> movieList = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_top_rated:
                    movieList.clear();
                    moviePresenter.FetchTopRatedMovies();
                    return true;
                case R.id.navigation_up_coming:
                    movieList.clear();
                    moviePresenter.FetchUpComingMovies();
                    return true;
                case R.id.navigation_now_playing:
                    movieList.clear();
                    moviePresenter.FetchNowPlayingMovies();
                    return true;
        }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        init();
        moviePresenter = new MoviePresenter(MainActivity.this, null);
        moviePresenter.FetchTopRatedMovies();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                moviePresenter.searchMovie(query);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                moviePresenter.searchMovie(newText);
                return false;
            }
        });
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if(progressBar!=null && progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void init() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);
        searchView=findViewById(R.id.searchView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void showError(String errorMsg) {

        Toast.makeText(MainActivity.this, ""+errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadList(List<MovieList> movieLists) {
        movieAdapter = new MovieAdapter((ArrayList<MovieList>) movieLists,this, getApplicationContext());
        movieAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(movieAdapter);

    }

    @Override
    public void openDetailPage(int movie_id) {
        Intent intent = new Intent(MainActivity.this,Activity_Detail.class);
        intent.putExtra("movieid",movie_id);
        startActivity(intent);
    }


    @Override
    public void onClick(int movieId) {
        openDetailPage(movieId);
    }
}