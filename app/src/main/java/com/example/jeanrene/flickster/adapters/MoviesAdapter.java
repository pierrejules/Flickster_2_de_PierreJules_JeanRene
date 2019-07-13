package com.example.jeanrene.flickster.adapters;

/**
 * Created by JeanRene on 7/12/2019.
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jeanrene.flickster.R;
import com.example.jeanrene.flickster.models.Movie;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{
    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("smile","onCreateViewHolder");
        View view= LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("smile","onBindViewHolder: "+position);
        Movie movie=movies.get(position);
      //holder.tvTitles.setText("helo");
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

       TextView tvTitles;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(View itemView) {
            super(itemView);
           tvTitles = itemView.findViewById(R.id.tvTitles);
           tvOverview=itemView.findViewById(R.id.tvOverview);
           ivPoster=itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitles.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
        }
    }
}
