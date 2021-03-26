package com.abhi.moviemania.apis;



import com.abhi.moviemania.models.Movies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/{filter}")
    Observable<Movies> getMovies(@Path("filter") String filter, @Query("api_key") String apiKey);

}
