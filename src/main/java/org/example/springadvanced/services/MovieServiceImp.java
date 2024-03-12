package org.example.springadvanced.services;

import org.example.springadvanced.models.Movie;
import org.example.springadvanced.util.RetrofitUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class MovieServiceImp implements MovieService{

    private Retrofit retrofit;
    private MovieApi movieApi;

    public MovieServiceImp() {
        retrofit = RetrofitUtil.getRetrofitInstance();
        movieApi = retrofit.create(MovieApi.class);
    }

    @Override
    public Call<Object> discoverMovies(String authorization) {
        return null;
    }


    @Override
    public void getMovies( Callback<List<Movie>> callback){
       Call<List<Movie>> getMovies = movieApi.getMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjI4MzhkZGQ4ZDFjMWFlZmUwODFmMzdiYzc3NzE3MCIsInN1YiI6IjY1ZjA0ZWIwN2YwNTQwMDE2NDg1ZDZmMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2sO58bPqK8B43OdbVKhc8Widfo_Bm-FuEXWrKtQKok0");
//       try {
//           Response<List<Movie>> response = getMovies.execute();
//           if (response.isSuccessful() && response.body() != null) {
//               movieList = response.body();
//           }
//       } catch (Exception e) {
//           throw new RuntimeException(e)   }
//       return movieList;
 //  }

        getMovies.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, retrofit2.Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    List<Movie> listOfMovies = response.body();
                    callback.onResponse(call, response);

                }
            }
            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    @Override
    public void getListOfMoviesAndSave() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build();

        MovieService service = retrofit.create(MovieService.class);

        Call<Object> call = service.discoverMovies(
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjI4MzhkZGQ4ZDFjMWFlZmUwODFmMzdiYzc3NzE3MCIsIn" +
                        "N1YiI6IjY1ZjA0ZWIwN2YwNTQwMDE2NDg1ZDZmMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ" +
                        ".2sO58bPqK8B43OdbVKhc8Widfo_Bm-FuEXWrKtQKok0");

        Object response = call.execute().body();
        System.out.println(response);
    }
}
