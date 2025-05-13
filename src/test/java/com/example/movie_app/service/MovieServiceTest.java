package com.example.movie_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.example.movie_app.domain.Director;
import com.example.movie_app.domain.Movie;
import com.example.movie_app.exception.NoSuchEntityException;
import com.example.movie_app.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepositoryMock;

    @InjectMocks
    private MovieService underTest;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMoviesHappyPath() {
        List<Movie> expectedMovies = List.of(
                Movie.builder()
                        .id(UUID.randomUUID())
                        .coverUrl("coverUrl1")
                        .title("title1")
                        .releaseDate(LocalDate.now())
                        .lang("language1")
                        .duration(100)
                        .genre("genre1")
                        .plot("plot1")
                        .director(
                                Director.builder()
                                        .id(UUID.randomUUID())
                                        .avatar("avatar1")
                                        .name("name1")
                                        .nationality("nationality1")
                                        .dateOfBirth(LocalDate.now())
                                        .build()
                        )
                        .build(),
                Movie.builder()
                        .id(UUID.randomUUID())
                        .coverUrl("coverUrl2")
                        .title("title2")
                        .releaseDate(LocalDate.now())
                        .lang("language2")
                        .duration(110)
                        .genre("genre2")
                        .plot("plot2")
                        .director(
                                Director.builder()
                                        .id(UUID.randomUUID())
                                        .avatar("avatar2")
                                        .name("name2")
                                        .nationality("nationality2")
                                        .dateOfBirth(LocalDate.now())
                                        .build()
                        )
                        .build()
        );

        when(movieRepositoryMock.findAll()).thenReturn(expectedMovies);

        List<Movie> result = underTest.getAllMovies();

        assertEquals(expectedMovies, result);
    }

    @Test
    void findByIdWhenMovieFound() {
        UUID id = UUID.randomUUID();

       Movie expectedMovie = Movie.builder()
                .id(id)
                .coverUrl("coverUrl1")
                .title("title1")
                .releaseDate(LocalDate.now())
                .lang("language1")
                .duration(100)
                .genre("genre1")
                .plot("plot1")
               .director(
                       Director.builder()
                               .id(UUID.randomUUID())
                               .avatar("avatar1")
                               .name("name1")
                               .nationality("nationality1")
                               .dateOfBirth(LocalDate.now())
                               .build()
               )
                .build();

        Optional<Movie> expectedDirectorOptional = Optional.of(expectedMovie);

        when(movieRepositoryMock.findById(id)).thenReturn(expectedDirectorOptional);

        Movie result = underTest.findById(id);

        assertEquals(expectedMovie, result);
    }

    @Test
    void findByIdWhenMovieIsMissing() {
        UUID id = UUID.randomUUID();
        String exceptionMessage = "There was no Movie with id: " + id;

        Optional<Movie> expectedMovieOptional = Optional.empty();
        when(movieRepositoryMock.findById(id)).thenReturn(expectedMovieOptional);
        //WHEN
        Exception exception = assertThrows(NoSuchEntityException.class, () -> underTest.findById(id));
        //THEN
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void deleteByIdHappyPath() {
        UUID id = UUID.randomUUID();
        underTest.deleteById(id);
        verify(movieRepositoryMock).deleteById(id);
    }

    @Test
    void saveHappyPath() {
        Movie expectedMovie = Movie.builder()
                .id(UUID.randomUUID())
                .coverUrl("coverUrl1")
                .title("title1")
                .releaseDate(LocalDate.now())
                .lang("language1")
                .duration(100)
                .genre("genre1")
                .plot("plot1")
                .director(
                        Director.builder()
                                .id(UUID.randomUUID())
                                .avatar("avatar1")
                                .name("name1")
                                .nationality("nationality1")
                                .dateOfBirth(LocalDate.now())
                                .build()
                )
                .build();

        when(movieRepositoryMock.save(expectedMovie)).thenReturn(expectedMovie);

        Movie result = underTest.save(expectedMovie);

        assertEquals(expectedMovie, result);
    }

    @Test
    void editHappyPath() {
        Movie expectedMovie = Movie.builder()
                .id(UUID.randomUUID())
                .coverUrl("coverUrl1")
                .title("title1")
                .releaseDate(LocalDate.now())
                .lang("language1")
                .duration(100)
                .genre("genre1")
                .plot("plot1")
                .director(
                        Director.builder()
                                .id(UUID.randomUUID())
                                .avatar("avatar1")
                                .name("name1")
                                .nationality("nationality1")
                                .dateOfBirth(LocalDate.now())
                                .build()
                )
                .build();

        when(movieRepositoryMock.save(expectedMovie)).thenReturn(expectedMovie);

        Movie result = underTest.save(expectedMovie);

        assertEquals(expectedMovie, result);
    }

    @Test
    void findByTitleHappyPath() {
        String title = "title";

        List<Movie> expectedMovies = List.of(
                Movie.builder()
                        .id(UUID.randomUUID())
                        .coverUrl("coverUrl2")
                        .title("title2")
                        .releaseDate(LocalDate.now())
                        .lang("language2")
                        .duration(100)
                        .genre("genre2")
                        .plot("plot2")
                        .director(
                                Director.builder()
                                        .id(UUID.randomUUID())
                                        .avatar("avatar1")
                                        .name("name1")
                                        .nationality("nationality1")
                                        .dateOfBirth(LocalDate.now())
                                        .build()
                        )
                        .build(),
                Movie.builder()
                        .id(UUID.randomUUID())
                        .coverUrl("coverUrl3")
                        .title("title3")
                        .releaseDate(LocalDate.now())
                        .lang("language3")
                        .duration(100)
                        .genre("genre3")
                        .plot("plot3")
                        .director(
                                Director.builder()
                                        .id(UUID.randomUUID())
                                        .avatar("avatar2")
                                        .name("name2")
                                        .nationality("nationality2")
                                        .dateOfBirth(LocalDate.now())
                                        .build()
                        )
                        .build()
        );

        when(movieRepositoryMock.findByTitleContainingIgnoreCase(title)).thenReturn(expectedMovies);

        List<Movie> result = underTest.searchByTitle(title);

        assertEquals(expectedMovies, result);
    }

}
