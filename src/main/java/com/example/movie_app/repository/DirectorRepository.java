package com.example.movie_app.repository;

import com.example.movie_app.domain.Director;
import com.example.movie_app.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {

    List<Director> findByNameContainingIgnoreCase(String name);
}
