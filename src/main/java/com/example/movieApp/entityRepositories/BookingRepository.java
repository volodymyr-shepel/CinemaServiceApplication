package com.example.movieApp.entityRepositories;

import com.example.movieApp.appUser.AppUser;
import com.example.movieApp.entities.Booking;
import com.example.movieApp.entities.SeatStatus;
import com.example.movieApp.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("SELECT DISTINCT b.seat.seatId FROM Booking b WHERE b.movieSession.movieSessionId = :givenMovieSessionId")
    List<Long> findBookedSeatIds(@Param("givenMovieSessionId") Long movieSessionId);


    List<Booking> findAllByAppUserAndSeatStatus(AppUser appUser, SeatStatus seatStatus);

    void deleteAllByAppUserAndSeatStatus(AppUser appUser, SeatStatus seatStatus);

    List<Booking> findAllByExpirationTimeBeforeAndSeatStatus(LocalDateTime currentDateTime, SeatStatus seatStatus);

    boolean existsByAppUserAndSeatStatus(AppUser appUser, SeatStatus seatStatus);

    List<Booking> findAllByTicketTicketId(Long ticketId);

    //    @Transactional
//    @Modifying
//    @Query("UPDATE Booking b SET b.seatStatus = 'PURCHASED', b.ticket = :newTicket " +
//            "WHERE b.appUser = :appUser AND b.expirationTime >= :currentTime")
//    void updateStatusAndTicketForNonExpiredReservationsByAppUser(
//            AppUser appUser, LocalDateTime currentTime, Ticket newTicket);
//
//    @Query("SELECT COALESCE(SUM(sp.price), 0) FROM Booking b " +
//            "JOIN b.seatPricing sp " +
//            "WHERE b.appUser = :appUser")
//    BigDecimal calculateTotalPriceForUser(AppUser appUser);
}

