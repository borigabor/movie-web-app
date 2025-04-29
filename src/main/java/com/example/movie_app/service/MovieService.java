package com.example.movie_app.service;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.exception.NoSuchEntityException;
import com.example.movie_app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }


    public List<Movie> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public Movie save(Movie movie) {
       return movieRepository.save(movie);
    }

    public Movie edit(Movie movie) {
       return movieRepository.save(movie);
    }

    public Movie findById(UUID id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            throw new NoSuchEntityException(
                    "There was no Movie with id: " + id
            );
        }
    }

    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }
}

