package org.example.springadvanced.services;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.io.IOException;

public interface MovieService {
    @GET("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    Call<Object> discoverMovies(@Header("Authorization") String authorization);

    Object getListOfMoviesAndSave() throws IOException;
}
