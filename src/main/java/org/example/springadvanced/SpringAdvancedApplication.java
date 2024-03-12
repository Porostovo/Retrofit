package org.example.springadvanced;

import org.example.springadvanced.models.Movie;
import org.example.springadvanced.services.MovieService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@SpringBootApplication
public class SpringAdvancedApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringAdvancedApplication.class, args);

//		Retrofit retrofit = new Retrofit.Builder()
//				.addConverterFactory(GsonConverterFactory.create())
//				.baseUrl("https://api.themoviedb.org/3/")
//				.build();
//
//		MovieService service = retrofit.create(MovieService.class);
//
//		Call<Object> call = service.discoverMovies(
//				"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjI4MzhkZGQ4ZDFjMWFlZmUwODFmMzdiYzc3NzE3MCIsInN1YiI6IjY1ZjA0ZWIwN2YwNTQwMDE2NDg1ZDZmMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2sO58bPqK8B43OdbVKhc8Widfo_Bm-FuEXWrKtQKok0"
//		);
//
//		Object response = call.execute().body();
//		System.out.println(response);



	}

}
