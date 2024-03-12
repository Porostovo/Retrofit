package org.example.springadvanced.services;

import org.example.springadvanced.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

import java.util.List;

public interface MovieApi {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/3/movie/now_playing?language=en-US&page=1")
    Call<List<Movie>> getMovies(@Header("Authorization") String auth);
}
