package com.example.movieApp.entityRepositories;

import com.example.movieApp.entities.MovieHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieHallRepository extends JpaRepository<MovieHall,Long> {

    List<MovieHall> findAll();
}
