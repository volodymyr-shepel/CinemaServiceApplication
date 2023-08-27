package com.example.movieApp.entityRepositories;

import com.example.movieApp.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByMovieTitle(String movieTitle);
    boolean existsByMovieTitle(String movieTitle);
    List<Movie> findAll();



}
