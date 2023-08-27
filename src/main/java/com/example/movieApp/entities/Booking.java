package com.example.movieApp.entities;

import com.example.movieApp.appUser.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"seat_id", "movie_session_id"}))
public class Booking {
    @Id
    @GeneratedValue
    private Long bookingId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "movie_session_id")
    private MovieSession movieSession;



    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seat_pricing_id")
    private SeatPricing seatPricing;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @NotNull
    private LocalDateTime bookingTime; // Time when the booking was made

    @NotNull
    private LocalDateTime expirationTime; // Time when the reservation expires

    public Booking(Seat seat, MovieSession movieSession, SeatPricing seatPricing, AppUser appUser) {
        this.seat = seat;
        this.movieSession = movieSession;
        this.seatPricing = seatPricing;
        this.appUser = appUser;
    }
    public Booking(){

    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public MovieSession getMovieSession() {
        return movieSession;
    }

    public void setMovieSession(MovieSession movieSession) {
        this.movieSession = movieSession;
    }

    public SeatPricing getSeatPricing() {
        return seatPricing;
    }

    public void setSeatPricing(SeatPricing seatPricing) {
        this.seatPricing = seatPricing;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
