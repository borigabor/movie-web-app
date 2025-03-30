package com.example.movie_app.controller;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/list")
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies/movies";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable UUID id) {
        movieService.deleteById(id);
        return "redirect:/movies/list";
    }


}
