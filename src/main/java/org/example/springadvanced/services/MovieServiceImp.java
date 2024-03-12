package org.example.springadvanced.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.springadvanced.models.Movie;
import org.example.springadvanced.repositories.MovieRepository;
import org.example.springadvanced.util.RetrofitUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MovieServiceImp implements MovieService {

    private Retrofit retrofit;
    private MovieApi movieApi;
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository) {

        this.movieRepository = movieRepository;

        retrofit = RetrofitUtil.getRetrofitInstance();
        movieApi = retrofit.create(MovieApi.class);
    }

    @Override
    public Call<Object> discoverMovies(String authorization) {
        return null;
    }


    @Override
    public void getMovies(Callback<List<Movie>> callback) {
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
    public Object getListOfMoviesAndSave() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build();

        MovieService service = retrofit.create(MovieService.class);

        Call<Object> call = service.discoverMovies(
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjI4MzhkZGQ4ZDFjMWFlZmUwODFmMzdiYzc3NzE3MCIsIn" +
                        "N1YiI6IjY1ZjA0ZWIwN2YwNTQwMDE2NDg1ZDZmMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ" +
                        ".2sO58bPqK8B43OdbVKhc8Widfo_Bm-FuEXWrKtQKok0");

        //take original title from recieved JSON format
        Object response = call.execute().body();
        Gson gson = new Gson();
        String jsonString = gson.toJson(response);
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        JsonArray resultsArray = jsonObject.getAsJsonArray("results");

        for (int i = 0; i < resultsArray.size(); i++) {
            Movie movie = new Movie();
            JsonObject resultObject = resultsArray.get(i).getAsJsonObject();
            String original_title = resultObject.get("original_title").getAsString();
            movie.setOriginal_title(original_title);
            movieRepository.save(movie);
        }
        return response;
    }
}
