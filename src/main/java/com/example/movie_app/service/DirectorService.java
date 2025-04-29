package com.example.movie_app.service;

import com.example.movie_app.domain.Director;
import com.example.movie_app.exception.NoSuchEntityException;
import com.example.movie_app.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public List<Director> searchByName(String name) {
        return directorRepository.findByNameContainingIgnoreCase(name);
    }

    public Director save(Director director) {
       return directorRepository.save(director);
    }

    public Director edit(Director director) {
      return directorRepository.save(director);
    }

    public Director findById(UUID id) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (optionalDirector.isPresent()) {
            return optionalDirector.get();
        } else {
            throw new NoSuchEntityException(
                    "There was no author with id: " + id
            );
        }
    }

    public void deleteById(UUID id) {
        directorRepository.deleteById(id);
    }
}

