package org.example.springadvanced;

import org.example.springadvanced.models.Movie;
import org.example.springadvanced.services.MovieService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitMovie {

    interface requestMovie {
        @GET("/3/movie/{movieId}")
        Call<Movie> movieDetail(@Path("movieId") String movieId);
    }


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MovieService movieService = retrofit.create(MovieService.class);


}
