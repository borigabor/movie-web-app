package com.example.movie_app.service;

import com.example.movie_app.domain.Director;
import com.example.movie_app.domain.Movie;
import com.example.movie_app.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }



}
