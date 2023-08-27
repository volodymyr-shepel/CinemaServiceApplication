package com.example.movieApp.api.admin.seatPricing;

import com.example.movieApp.entities.Movie;
import com.example.movieApp.entities.MovieHall;
import com.example.movieApp.entities.MovieSession;
import com.example.movieApp.entities.SeatPricing;
import com.example.movieApp.entityRepositories.MovieHallRepository;
import com.example.movieApp.entityRepositories.MovieRepository;
import com.example.movieApp.entityRepositories.SeatPricingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.util.List;
import java.util.Optional;

@Service
public class AdminSeatPricingService {


    private final MovieRepository movieRepository;
    private final MovieHallRepository movieHallRepository;
    private final SeatPricingRepository seatPricingRepository;



    @Autowired
    public AdminSeatPricingService(MovieRepository movieRepository, MovieHallRepository movieHallRepository, SeatPricingRepository seatPricingRepository) {
        this.movieRepository = movieRepository;
        this.movieHallRepository = movieHallRepository;
        this.seatPricingRepository = seatPricingRepository;
    }

    public ResponseEntity<SeatPricing> setSeatPricing(SeatPricingDTO seatPricingDTO) {

        Optional<MovieHall> movieHallOptional = movieHallRepository.findById(seatPricingDTO.getMovieHallId());

        // check whether the provided movie hall does exist
        if (movieHallOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // check if movie with provided id does exist
        Optional<Movie> movieOptional = movieRepository.findById(seatPricingDTO.getMovieId());
        if (movieOptional.isEmpty()) {

            return ResponseEntity.notFound().build();

        }
        SeatPricing seatPricing = new SeatPricing();

        seatPricing.setMovie(movieOptional.get());
        seatPricing.setMovieHall(movieHallOptional.get());
        seatPricing.setPrice(seatPricingDTO.getPrice());

        seatPricingRepository.save(seatPricing);
        return ResponseEntity.status(HttpStatus.CREATED).body(seatPricing);
    }

    @Transactional
    public ResponseEntity<SeatPricing> updateSeatPricing(SeatPricingDTO seatPricingDTO) {
        Optional<SeatPricing> seatPricingOptional = seatPricingRepository.findOneByMovieMovieIdAndMovieHallMovieHallId(seatPricingDTO.getMovieId(),seatPricingDTO.getMovieHallId());
        if(seatPricingOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        SeatPricing seatPricing = seatPricingOptional.get();
        seatPricing.setPrice(seatPricingDTO.getPrice());

        return ResponseEntity.ok(seatPricing);
    }

    public List<SeatPricing> getAllSeatPrices() {
        return seatPricingRepository.findAll();
    }
}
