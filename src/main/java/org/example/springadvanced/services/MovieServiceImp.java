package org.example.springadvanced.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.springadvanced.models.Movie;
import org.example.springadvanced.models.MovieDTO;
import org.example.springadvanced.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;


@Service
@Component
public class MovieServiceImp implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Call<Object> discoverMovies(String authorization) {
        return null;
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

        Object response = call.execute().body();
        Gson gson = new Gson();
        String jsonString = gson.toJson(response);
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        JsonArray resultsArray = jsonObject.getAsJsonArray("results");

        ObjectMapper mapper = new ObjectMapper();
        try {
            MovieDTO movieDTO = mapper.readValue(jsonString, MovieDTO.class);
            System.out.println(movieDTO.getResults().get(2).getOriginal_title());

        } catch (Exception e) {
            e.printStackTrace();
        }

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
