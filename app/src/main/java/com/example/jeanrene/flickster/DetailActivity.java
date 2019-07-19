package com.example.jeanrene.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.jeanrene.flickster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends YouTubeBaseActivity {
    //private static final String YOUTUBE_API_KEY="AIzaSyCG_p8VI49RC4npidCWE0T0VuGAWv9EDNs";
    private static final String YOUTUBE_API_KEY="AIzaSyC0tla3B2nUReRqSTM8DR1ksM31xbbY-cA";
    private static final String TRAILERS_API="https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvTitles;
    TextView tvOverview;
    RatingBar ratingBar;
    Movie movie;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitles=findViewById(R.id.tvTitles);
        tvOverview=findViewById(R.id.tvOverview);
        ratingBar=findViewById(R.id.ratingBar);
        youTubePlayerView=findViewById(R.id.player);

       // movie=getIntent().getParcelableExtra("movie");
       // String title=getIntent().getStringExtra("titles");
        movie=(Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitles.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
       // ratingBar.setRating((float) movie.getVoteAverage());
       ratingBar.setRating(4.4f);

        AsyncHttpClient client=new AsyncHttpClient();
        client.get(String.format(TRAILERS_API,movie.getMovieId()),new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
               // super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray results=response.getJSONArray("results");
                    if (results.length()==0){
                        return;
                    }
                    JSONObject movieTrailer=results.getJSONObject(0);
                    String youtubeKey=movieTrailer.getString("key");
                    initializeYoutube(youtubeKey);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
    private void initializeYoutube(final String youtubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("smile","on init success");
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("smile","on init failure");
            }
        });
    }
}
