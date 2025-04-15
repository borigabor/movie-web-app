package com.example.movie_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.movie_app.domain.Director;
import com.example.movie_app.exception.NoSuchEntityException;
import com.example.movie_app.repository.DirectorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


 public class DirectorServiceTest {

    @Mock
    private DirectorRepository directorRepositoryMok;

    @InjectMocks
    private DirectorService underTest;

     @BeforeEach
     void setup() {
         MockitoAnnotations.openMocks(this);
     }

    @Test
    void getAllDirectorsHappyPath() {
        //Given Ez maga a setup
        List<Director> expectedDirectors = List.of(
                Director.builder()
                        .id(UUID.randomUUID())
                        .avatar("avatar1")
                        .name("name1")
                        .nationality("nationality1")
                        .dateOfBirth(LocalDate.now())
                        .build(),
                Director.builder()
                        .id(UUID.randomUUID())
                        .avatar("avatar2")
                        .name("name2")
                        .nationality("nationality2")
                        .dateOfBirth(LocalDate.now())
                        .build()
        );
        when(directorRepositoryMok.findAll()).thenReturn(expectedDirectors);

        //When eredtei metódus hívás
        List<Director> result = underTest.getAllDirectors();

        // Then a 2 lista összehasonlítása
        Assertions.assertIterableEquals(expectedDirectors, result);
    }

    @Test
     void findByIdWhenDirectorFound() {
         UUID id = UUID.randomUUID();
         Director expectedDirector = Director.builder()
                 .id(id)
                 .avatar("avatar1")
                 .name("name1")
                 .nationality("nationality1")
                 .dateOfBirth(LocalDate.now())
                 .build();

        Optional<Director> expectedDirectorOptional = Optional.of(expectedDirector);

        when(directorRepositoryMok.findById(id)).thenReturn(expectedDirectorOptional);

        //WHEN
        Director result = underTest.findById(id);

        //THEN
        assertEquals(expectedDirector, result);

    }

    @Test
     void findByIdWhenDirectorIsMissing() {
     UUID id = UUID.randomUUID();
     String exceptionMessage = "There was no author with id: " + id;
     // Üresnek kell lenni mert nem fog találni semmit.
     Optional<Director> expectedDirectorOptional = Optional.empty();
     when(directorRepositoryMok.findById(id)).thenReturn(expectedDirectorOptional);
     //WHEN
     //nem várunk vissza semmit így egyböl assertolunk
     Exception exception = assertThrows(NoSuchEntityException.class, () -> underTest.findById(id));
     //THEN
     assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
     void deleteByIdHappyPhat() {
        //GIVEN
        UUID id = UUID.randomUUID();
        //WHEN
        underTest.deleteById(id);
        //THEN
        verify(directorRepositoryMok).deleteById(id);
    }

    @Test
     void saveHappyPath() {
         //GIVEN
        Director expectedDirector = Director.builder()
                .id (UUID.randomUUID())
                .avatar("avatar2")
                .name("name2")
                .nationality("nationality2")
                .dateOfBirth(LocalDate.now())
                .build();

        when(directorRepositoryMok.save(expectedDirector)).thenReturn(expectedDirector);
        //WHEN
       Director result = underTest.save(expectedDirector);
        //THEN
        assertEquals(expectedDirector, result);
    }

    @Test
     void editHappyPath() {
         Director expectedDirector = Director.builder()
                 .id (UUID.randomUUID())
                 .avatar("avatar3")
                 .name("name3")
                 .nationality("nationality3")
                 .dateOfBirth(LocalDate.now())
                 .build();

         when(directorRepositoryMok.save(expectedDirector)).thenReturn(expectedDirector);

         Director result = underTest.save(expectedDirector);

         assertEquals(expectedDirector, result);
    }

    @Test
     void findByNameHappyPath() {
         String name = "name";

         List<Director> expectedDirector = List.of(
                 Director.builder()
                         .id(UUID.randomUUID())
                         .avatar("avatar1")
                         .name("name1")
                         .nationality("nationality1")
                         .dateOfBirth(LocalDate.now())
                         .build(),
                 Director.builder()
                         .id(UUID.randomUUID())
                         .avatar("avatar2")
                         .name("name2")
                         .nationality("nationality2")
                         .dateOfBirth(LocalDate.now())
                         .build()
         );

        when(directorRepositoryMok.findByNameContainingIgnoreCase(name)).thenReturn(expectedDirector);

        List<Director> result = underTest.searchByName(name);

        assertEquals(expectedDirector, result);

    }

}
