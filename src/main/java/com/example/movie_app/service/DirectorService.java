package com.example.movie_app.service;

import com.example.movie_app.domain.Director;
import com.example.movie_app.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public void deleteById(UUID id) {
        directorRepository.deleteById(id);
    }


}
