package com.example.jeanrene.flickster.models;

/**
 * Created by JeanRene on 7/12/2019.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;
import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String posterPath;
    String title;
    String overview;
    double voteAverage;
    int movieId;

    public Movie() {
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath=jsonObject.getString("poster_path");
        title=jsonObject.getString("title");
        overview=jsonObject.getString("overview");
        voteAverage=jsonObject.getDouble("vote_average");
        movieId=jsonObject.getInt("id");
    }

    public static List<Movie> fomJsonArray(JSONArray movieJsonArray) throws JSONException{
        List<Movie> movies=new ArrayList<>();
        for (int i=0;i<movieJsonArray.length();i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getMovieId() {
        return movieId;
    }
}
