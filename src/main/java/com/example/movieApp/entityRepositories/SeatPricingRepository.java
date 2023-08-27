package com.example.movieApp.entityRepositories;

import com.example.movieApp.entities.SeatPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatPricingRepository extends JpaRepository<SeatPricing,Long> {
    Optional<SeatPricing> findOneByMovieMovieIdAndMovieHallMovieHallId(Long movieId, Long movieHallId);

    List<SeatPricing> findAll();


    Optional<SeatPricing> findByMovieMovieIdAndMovieHallMovieHallId(Long movieId, Long movieHallId);
}
