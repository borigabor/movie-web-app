package com.example.movie_app.controller;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.service.DirectorService;
import com.example.movie_app.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    private final DirectorService directorService;

    public MovieController(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService = directorService;
    }

    @GetMapping("/list")
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies/movies";
    }

    @GetMapping("/new")
    public String createMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("directors", directorService.getAllDirectors());
        return "movies/create-movie";
    }

    @PostMapping
    public String saveMovie(@ModelAttribute Movie movie) {
        movieService.save(movie);
        return "redirect:/movies/list";
    }

    @GetMapping("/edit/{id}")
    public String editMovieForm(@PathVariable UUID id, Model model) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("directors", directorService.getAllDirectors());
        return "movies/edit-movie";
    }

    @PostMapping("/edit")
    public String updateMovie(@ModelAttribute Movie movie) {
        movieService.edit(movie);
        return "redirect:/movies/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable UUID id) {
        movieService.deleteById(id);
        return "redirect:/movies/list";
    }


    @GetMapping("/search")
    public String searchMovie(@RequestParam String title, Model model) {
        List<Movie> movies = movieService.searchByTitle(title);
        model.addAttribute("movies", movies);
        return "movies/movies";
    }

}
