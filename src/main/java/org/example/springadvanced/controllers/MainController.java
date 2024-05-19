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

    @GetMapping("/movies2")
    public Object listOfMovies() throws IOException {
        return movieService.getListOfMoviesAndSave();
    }

}
