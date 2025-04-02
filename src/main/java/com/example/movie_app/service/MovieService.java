package com.example.movie_app.service;

import com.example.movie_app.domain.Director;
import com.example.movie_app.domain.Movie;
import com.example.movie_app.exception.NoSuchEntityException;
import com.example.movie_app.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public void edit(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie findById(UUID id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            throw new NoSuchEntityException("There was no movie found for this id: " + id);
        }
    }

    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }


}
