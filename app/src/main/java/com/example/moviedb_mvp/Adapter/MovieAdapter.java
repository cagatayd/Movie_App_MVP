package com.example.moviedb_mvp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb_mvp.Model.Response.MovieDetail;
import com.example.moviedb_mvp.Model.Response.MovieList;
import com.example.moviedb_mvp.R;
import com.example.moviedb_mvp.Repository.Irepository;
import com.example.moviedb_mvp.Utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    ArrayList<MovieList>movieLists;
    Context context;
    OnMovieItemClick listener;

    public MovieAdapter(ArrayList<MovieList> movieLists, OnMovieItemClick listener,Context context) {
        this.listener = listener;
        this.movieLists = movieLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(movieLists.get(position).title);
        holder.textViewid.setText(String.valueOf(movieLists.get(position).id));
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(AppConstant.BASE_PATH_OF_IMAGE  + movieLists.get(position).poster_path).into(holder.imageView);
        holder.setOnClickListener(movieLists.get(position).id);

    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        ImageView imageView;
        TextView textView;
        TextView textViewid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.poster);
            textView=itemView.findViewById(R.id.postertitle);
            textViewid=itemView.findViewById(R.id.texviewid);

        }



        private void setOnClickListener(int id) {
            itemView.setTag(id);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onClick((int)view.getTag());
        }
    }

    public interface OnMovieItemClick {
        void onClick(int movieId);
    }

}
