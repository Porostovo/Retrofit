package org.example.springadvanced.controllers;

import org.example.springadvanced.models.Movie;
import org.example.springadvanced.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
public class MainController {

    final MovieService movieService;

    @Autowired
    public MainController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping({"", "/", "index"})
    public String getMainPage() {

        return "index";
    }
    //Once you have been issued a key, an example API key based request looks like this:
    //curl --request GET \
    //--url 'https://api.themoviedb.org/3/movie/11?api_key=3b2838ddd8d1c1aefe081f37bc777170'


//    @GetMapping("/movies")
//    public List<Movie> getMovies() {
//        return movieService.getMovies();
//    }

    @GetMapping("/movies2")
    public String listOfMovies() throws IOException {
        movieService.getListOfMoviesAndSave();


        return "nic";
    }

}
