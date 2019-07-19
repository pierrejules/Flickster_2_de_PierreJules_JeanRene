package com.example.jeanrene.flickster.adapters;

/**
 * Created by JeanRene on 7/12/2019.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jeanrene.flickster.DetailActivity;
import com.example.jeanrene.flickster.R;
import com.example.jeanrene.flickster.models.Movie;

import org.parceler.Parcels;

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
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
           tvTitles = itemView.findViewById(R.id.tvTitles);
           tvOverview=itemView.findViewById(R.id.tvOverview);
           ivPoster=itemView.findViewById(R.id.ivPoster);
           container=itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitles.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);

            container.setOnClickListener(new View.OnClickListener() {
               // tvTitles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(context,movie.getTitle(),Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(context, DetailActivity.class);
                   // i.putExtra("title",movie.getTitle());
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
