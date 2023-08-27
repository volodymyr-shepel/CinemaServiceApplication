package com.example.movieApp.api.admin.seat;

import com.example.movieApp.entities.MovieHall;
import com.example.movieApp.entities.Seat;
import com.example.movieApp.entityRepositories.MovieHallRepository;
import com.example.movieApp.entityRepositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminSeatService {
    private final SeatRepository seatRepository;
    private final MovieHallRepository movieHallRepository;

    @Autowired
    public AdminSeatService(SeatRepository seatRepository, MovieHallRepository movieHallRepository) {
        this.seatRepository = seatRepository;
        this.movieHallRepository = movieHallRepository;
    }

    public ResponseEntity<Seat> createSeat(SeatDTO seatDTO){


        Optional<MovieHall> optionalMovieHall = movieHallRepository.findById(seatDTO.getMovieHallId());
        if(optionalMovieHall.isEmpty()){
            String errorMessage = "Movie hall with ID " + seatDTO.getMovieHallId() + " not found.";

            return ResponseEntity.notFound().header("X-Error-Message", errorMessage).build();
        }

        Seat seat = new Seat();
        MovieHall movieHall = optionalMovieHall.get();
        seat.setRowNumber(seatDTO.getRowNumber());
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setMovieHall(movieHall);

        seatRepository.save(seat);

        movieHall.setCapacity(movieHall.getCapacity() + 1);

        return ResponseEntity.ok().body(seat);

    }

    public void createSeat(MovieHall movieHall, int rowNumber, int seatNumber){

        Seat seat = new Seat();
        seat.setRowNumber(rowNumber);
        seat.setSeatNumber(seatNumber);
        seat.setMovieHall(movieHall);

        seatRepository.save(seat);
    }
}
