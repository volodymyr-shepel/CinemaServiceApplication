package com.example.movieApp.entityRepositories;

import com.example.movieApp.entities.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {

    @Query("SELECT CASE WHEN COUNT(ms) > 0 THEN true ELSE false END " +
            "FROM MovieSession ms " +
            "WHERE ms.movieHall.movieHallId = :movieHallId " +
            "AND (:newSessionStart BETWEEN ms.startsAt AND ms.endsAt " +
            "OR :newSessionEnd BETWEEN ms.startsAt AND ms.endsAt)")
    boolean isMovieHallOccupiedDuringPeriod(@Param("movieHallId") Long movieHallId,
                                            @Param("newSessionStart") LocalDateTime newSessionStart,
                                            @Param("newSessionEnd") LocalDateTime newSessionEnd);


    List<MovieSession> findAllByStartsAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}

