package com.example.movieApp.entityRepositories;
import java.util.List;
import com.example.movieApp.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findByMovieHallMovieHallId(Long movieHallId);

}
