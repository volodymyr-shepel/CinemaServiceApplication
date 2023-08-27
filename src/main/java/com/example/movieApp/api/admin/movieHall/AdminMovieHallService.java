package com.example.movieApp.api.admin.movieHall;

import com.example.movieApp.api.admin.seat.AdminSeatService;
import com.example.movieApp.entities.MovieHall;
import com.example.movieApp.entityRepositories.MovieHallRepository;
import com.example.movieApp.entityRepositories.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminMovieHallService {

    private final MovieHallRepository movieHallRepository;
    private final SeatRepository seatRepository;

    private final AdminSeatService adminSeatService;




    @Autowired
    public AdminMovieHallService(MovieHallRepository movieHallRepository, SeatRepository seatRepository, AdminSeatService adminSeatService) {
        this.movieHallRepository = movieHallRepository;
        this.seatRepository = seatRepository;
        this.adminSeatService = adminSeatService;
    }



    public ResponseEntity<MovieHall> addMovieHall(MovieHallDTO movieHallDTO) {

        MovieHall movieHall = new MovieHall(movieHallDTO.getMovieHallCapacity(), movieHallDTO.getMovieHallNumber(), movieHallDTO.getMovieHallDescription());
        movieHallRepository.save(movieHall);

        setupMovieHallWithSeats(movieHall,movieHallDTO.getSeatsPerRow());

        return ResponseEntity.ok(movieHall);

    }
//    @Transactional
//    public ResponseEntity<String> updateMovieHall(Long movieHallId, MovieHall updatedMovieHall) {
//
//        Optional<MovieHall> optionalMovie = movieHallRepository.findById(movieHallId);
//        if (optionalMovie.isPresent()) {
//
//            MovieHall movieHall = optionalMovie.get();
//            movieHall.setDescription(updatedMovieHall.getDescription());
//            movieHall.setCapacity(updatedMovieHall.getCapacity());
//
//
//            return ResponseEntity.ok("Movie Updated successfully");
//
//        }
//        else{
//            return ResponseEntity.notFound().build();
//        }
//    }

    @Transactional
    public ResponseEntity<Long> removeMovieHall(Long movieHallId) {
        if(movieHallRepository.existsById(movieHallId)){
            movieHallRepository.deleteById(movieHallId);
            return ResponseEntity.ok().body(movieHallId);
        }
        else{
            String errorMessage = "Movie hall with ID " + movieHallId + " not found.";

            return ResponseEntity.notFound().header("X-Error-Message", errorMessage).build();

        }
    }

    @Transactional
    public void setupMovieHallWithSeats(MovieHall movieHall, int seatsPerRow) {

        int capacity = movieHall.getCapacity();

        for (int seatNumber = 1; seatNumber <= capacity; seatNumber++) {
            int rowNumber = (seatNumber - 1) / seatsPerRow + 1;
            int seatInRow = (seatNumber - 1) % seatsPerRow + 1;
            adminSeatService.createSeat(movieHall, rowNumber, seatInRow);
        }

    }

    public List<MovieHall> getAllMovieHalls() {
        return movieHallRepository.findAll();
    }
}
