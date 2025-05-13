package com.example.movie_app.controller;

import com.example.movie_app.domain.Director;
import com.example.movie_app.service.DirectorService;
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

public class DirectorControllerTest {

    @Mock
    private DirectorService directorServiceMock;

    @Mock
    private Model model;

    @InjectMocks
    private DirectorController underTest;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDirectorsHappyPath() {
        List <Director> expectedDirectors = List.of(
          Director.builder()
                  .id(UUID.randomUUID())
                  .avatar("sample avatar url 1")
                  .name("sample name 1")
                  .dateOfBirth(LocalDate.now())
                  .build(),
                Director.builder()
                        .id(UUID.randomUUID())
                        .avatar("sample avatar url 2")
                        .name("sample name 2")
                        .dateOfBirth(LocalDate.now())
                        .build()
        );

        when(directorServiceMock.getAllDirectors()).thenReturn(expectedDirectors);

        String resultDirector = underTest.getAllDirectors(model);
        assertEquals("directors/directors", resultDirector);
        verify(model).addAttribute("directors", expectedDirectors);
    }

    @Test
    void deleteHappyPath() {
        UUID id = UUID.randomUUID();
        String resultDirector = underTest.deleteDirector(id);
        assertEquals("redirect:/directors/list", resultDirector);
        verify(directorServiceMock).deleteById(id);
    }

    @Test
    void saveHappyPath() {
        Director expectedDirector = Director.builder()
                .id(UUID.randomUUID())
                .avatar("sample director avatar url")
                .name("sample director name")
                .dateOfBirth(LocalDate.now())
                .build();

        String resultDirector = underTest.saveDirector(expectedDirector);
        assertEquals("redirect:/directors/list", resultDirector);
        verify(directorServiceMock).save(expectedDirector);
    }

    @Test
    void editHappyPath() {
        Director expectedDirector = Director.builder()
                .id(UUID.randomUUID())
                .avatar("sample director avatar url")
                .name("sample director name")
                .dateOfBirth(LocalDate.now())
                .build();

        String resultDirector = underTest.updateDirector(expectedDirector);
        assertEquals("redirect:/directors/list", resultDirector);
        verify(directorServiceMock).edit(expectedDirector);
    }

    @Test
    void findByNameHappyPath() {
        String searchName = "Sample";

        List<Director> expectedDirectors = List.of(
          Director.builder()
                  .id(UUID.randomUUID())
                  .avatar("sample director avatar 1")
                  .name("sample director name 1")
                  .dateOfBirth(LocalDate.now())
                  .build(),
                Director.builder()
                        .id(UUID.randomUUID())
                        .avatar("sample director avatar 1")
                        .name("sample director name 1")
                        .dateOfBirth(LocalDate.now())
                        .build()
        );

        when(directorServiceMock.searchByName(searchName)).thenReturn(expectedDirectors);

        String resultDirector = underTest.searchDirector(searchName, model);
        assertEquals("directors/directors", resultDirector);
        verify(directorServiceMock).searchByName(searchName);
        verify(model).addAttribute("directors", expectedDirectors);
    }

}
