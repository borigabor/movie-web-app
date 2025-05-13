package com.example.movie_app.controller;

import com.example.movie_app.domain.Director;
import com.example.movie_app.domain.Movie;
import com.example.movie_app.repository.MovieRepository;
import com.example.movie_app.service.DirectorService;
import com.example.movie_app.service.MovieService;
import com.example.movie_app.service.MovieServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class MovieControllerTest {

    @Mock
    private MovieService movieServiceMock;

    @Mock
    private Model model;

    @InjectMocks
    private MovieController underTest;

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

        when(movieServiceMock.getAllMovies()).thenReturn(expectedMovies);

        String resultView = underTest.getAllMovies(model);

        assertEquals("movies/movies", resultView);
        verify(model).addAttribute("movies", expectedMovies);
    }

    @Test
    void deleteByIdHappyPath() {
        UUID id = UUID.randomUUID();

        String resultMovie = underTest.deleteMovie(id);
        assertEquals("redirect:/movies/list", resultMovie);
        verify(movieServiceMock).deleteById(id);
    }

    @Test
    void saveHappyPath() {
        Movie expectedMovie = Movie.builder()
                .id(UUID.randomUUID())
                .coverUrl("sample movie cover url")
                .title("sample movie title")
                .releaseDate(LocalDate.now())
                .lang("sample movie lang")
                .duration(100)
                .genre("sample movie plot")
                .plot("sample movie plot")
                .director(
                    Director.builder()
                            .id(UUID.randomUUID())
                            .avatar("sample director avatar")
                            .name("sample director name")
                            .dateOfBirth(LocalDate.now())
                            .build()
                )
                .build();

                String resultMovie = underTest.saveMovie(expectedMovie);
                assertEquals("redirect:/movies/list", resultMovie);
                verify(movieServiceMock).save(expectedMovie);
    }

    @Test
    void editHappyPath() {
        Movie expectedMovie = Movie.builder()
                .id(UUID.randomUUID())
                .coverUrl("sample movie cover url")
                .title("sample movie title")
                .releaseDate(LocalDate.now())
                .lang("sample movie lang")
                .duration(100)
                .genre("sample movie plot")
                .plot("sample movie plot")
                .director(
                        Director.builder()
                                .id(UUID.randomUUID())
                                .avatar("sample director avatar")
                                .name("sample director name")
                                .dateOfBirth(LocalDate.now())
                                .build()
                )
                .build();

                String resultMovie = underTest.updateMovie(expectedMovie);
                assertEquals("redirect:/movies/list", resultMovie);
                verify(movieServiceMock).edit(expectedMovie);
    }

    @Test
    void findByTitleHappyPath() {
        String searchTitle = "Sample";

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

        when(movieServiceMock.searchByTitle(searchTitle)).thenReturn(expectedMovies);

        String resultMovie = underTest.searchMovie(searchTitle, model);
        assertEquals("movies/movies", resultMovie);
        verify(movieServiceMock).searchByTitle(searchTitle);
        verify(model).addAttribute("movies", expectedMovies);

    }

}
