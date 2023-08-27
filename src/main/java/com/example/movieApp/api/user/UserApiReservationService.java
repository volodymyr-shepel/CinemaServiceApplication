package com.example.movieApp.api.user;

import com.example.movieApp.appUser.AppUser;
import com.example.movieApp.entities.*;
import com.example.movieApp.entityRepositories.*;
import com.example.movieApp.excepitons.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiReservationService {
    private final MovieSessionRepository movieSessionRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;
    private final SeatPricingRepository seatPricingRepository;


    @Autowired
    public UserApiReservationService(MovieSessionRepository movieSessionRepository,
                                     SeatRepository seatRepository,
                                     BookingRepository bookingRepository,
                                     SeatPricingRepository seatPricingRepository) {

        this.movieSessionRepository = movieSessionRepository;
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
        this.seatPricingRepository = seatPricingRepository;
    }


    public List<Seat> getAvailableSeats(Long movieSessionId) {

        Optional<MovieSession> movieSessionOptional = movieSessionRepository.findById(movieSessionId);
        if(movieSessionOptional.isEmpty()){
            throw new InstanceNotFoundException("Movie session with provided id does not exist");
        }
        Long movieHallId = movieSessionOptional.get().getMovieHall().getMovieHallId();
        List<Seat> allSeats = seatRepository.findByMovieHallMovieHallId(movieHallId);
        List<Long> takenSeats = bookingRepository.findBookedSeatIds(movieSessionId);

        return allSeats.stream()
                .filter(seat -> !takenSeats.contains(seat.getSeatId()))
                .collect(Collectors.toList());


    }

    @Transactional
    public List<Booking> reserveSeats(ReservationRequest reservationRequest) {
        List<Booking> bookings = new ArrayList<>();

        AppUser appUser = getAuthenticatedUser();

        // Check if the user has active reservations and throw an exception if necessary
        checkAndThrowIfHasReservations(appUser);

        LocalDateTime currentReservationTime = LocalDateTime.now();  // Use current time as reservation time

        for (BookingDTO bookingDTO : reservationRequest.getBookingDTOList()) {
            // Check if reservation time is valid before creating a booking
            checkReservationBeforeMovieStart(currentReservationTime, bookingDTO.getMovieSessionId());

            Booking booking = createBooking(bookingDTO, appUser);
            bookingRepository.save(booking);
            bookings.add(booking);
        }

        return bookings;
    }


    public List<Booking> getReservations() {
        AppUser appUser = getAuthenticatedUser();
        return bookingRepository.findAllByAppUserAndSeatStatus(appUser,SeatStatus.RESERVED);
    }

    @Transactional
    public ResponseEntity<String> cancelAllReservations() {
        AppUser appUser = getAuthenticatedUser();
        bookingRepository.deleteAllByAppUserAndSeatStatus(appUser,SeatStatus.RESERVED);
        return ResponseEntity.ok("Reservations have been canceled");
    }



    // Helper methods to retrieve entities and perform validations

    private AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (AppUser) authentication.getPrincipal();
        } else {
            throw new IllegalStateException("The user is not authenticated");
        }
    }

    private Booking createBooking(BookingDTO bookingDTO, AppUser appUser) {
        MovieSession movieSession = getMovieSession(bookingDTO.getMovieSessionId());
        Seat seat = getSeat(bookingDTO.getSeatId());

        validateSeatAvailability(seat, movieSession.getMovieSessionId());

        SeatPricing seatPricing = getSeatPricing(bookingDTO.getMovieSessionId());

        Booking booking = new Booking();
        booking.setSeat(seat);
        booking.setMovieSession(movieSession);
        booking.setSeatPricing(seatPricing);
        booking.setAppUser(appUser);
        booking.setSeatStatus(SeatStatus.RESERVED);
        booking.setBookingTime(LocalDateTime.now());

        // reservation expires in 15 minutes
        booking.setExpirationTime(LocalDateTime.now().plusMinutes(15));

        bookingRepository.save(booking);
        return booking;
    }


    private MovieSession getMovieSession(Long movieSessionId) {
        return movieSessionRepository.findById(movieSessionId)
                .orElseThrow(() -> new EntityNotFoundException("Movie Session with provided id does not exist.Operation was rolled back"));
    }

    private Seat getSeat(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new EntityNotFoundException("Seat with provided id does not exist.Operation was rolled back"));
    }

    private void validateSeatAvailability(Seat seat, Long movieSessionId) {
        if (isSeatTaken(seat, movieSessionId)) {
            throw new SeatAlreadyTakenException("the seat with id " + seat.getSeatId() + " is already taken.Operation was rolled back");
        }
    }

    // Get seat pricing based on the movieSessionId
    private SeatPricing getSeatPricing(Long movieSessionId) {
        MovieSession movieSession = movieSessionRepository.findById(movieSessionId)
                .orElseThrow(() -> new EntityNotFoundException("Movie Session with provided id does not exist.Operation was rolled back"));

        Long movieId = movieSession.getMovie().getMovieId();
        Long movieHallId = movieSession.getMovieHall().getMovieHallId();

        SeatPricing seatPricing = seatPricingRepository.findByMovieMovieIdAndMovieHallMovieHallId(movieId, movieHallId)
                .orElseThrow(() -> new EntityNotFoundException("Seat Pricing not found for the given Movie Session."));

        return seatPricing;

    }

    private boolean isSeatTaken(Seat seat,Long movieSessionId){
        List<Seat> availableSeats = getAvailableSeats(movieSessionId);
        return !availableSeats.contains(seat);

    }

    public void checkAndThrowIfHasReservations(AppUser appUser) throws ActiveReservationException {
        if (bookingRepository.existsByAppUserAndSeatStatus(appUser,SeatStatus.RESERVED)) {
            throw new ActiveReservationException("You have active reservations. Please finish or cancel them to proceed.");
        }
    }
    private void checkReservationBeforeMovieStart(LocalDateTime reservationTime, Long movieSessionId) throws InvalidReservationTimeException {
        MovieSession movieSession = getMovieSession(movieSessionId);
        LocalDateTime movieStartAt = movieSession.getStartsAt();

        if (reservationTime.isAfter(movieStartAt)) {
            throw new InvalidReservationTimeException("Reservation can't be made after the movie session has started.");
        }
    }


}
