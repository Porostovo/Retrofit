package org.example.springadvanced.controllers;


import org.example.springadvanced.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebController {

    private final MovieService movieService;

    @Autowired
    public WebController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }
}
