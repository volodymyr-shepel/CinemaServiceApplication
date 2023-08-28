package com.example.movieApp.api.user;

import com.example.movieApp.entities.appUser.AppUser;
import com.example.movieApp.email.EmailService;
import com.example.movieApp.entities.Booking;
import com.example.movieApp.entities.SeatStatus;
import com.example.movieApp.entities.Ticket;
import com.example.movieApp.entityRepositories.BookingRepository;
import com.example.movieApp.entityRepositories.TicketRepository;
import com.example.movieApp.excepitons.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserApiPurchaseService {

    private final BookingRepository bookingRepository;
    private final UserApiReservationService userApiReservationService;

    private final TicketRepository ticketRepository;

    private final EmailService emailService;

    @Autowired
    public UserApiPurchaseService(BookingRepository bookingRepository, UserApiReservationService userApiReservationService, TicketRepository ticketRepository, EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.userApiReservationService = userApiReservationService;
        this.ticketRepository = ticketRepository;
        this.emailService = emailService;
    }

    public ResponseEntity<Ticket> purchaseTicket() {
        AppUser appUser = getAuthenticatedUser();
        LocalDateTime currentTime = LocalDateTime.now();

        List<Booking> reservations = userApiReservationService.getReservations();
        if (reservations.isEmpty()) {
            throw new InstanceNotFoundException("There are no active reservations to purchase tickets for.");
        }
        BigDecimal totalPrice = calculateTotalPrice(reservations);
        Ticket ticket = new Ticket(appUser,totalPrice);
        for(Booking reservation : reservations){
            reservation.setTicket(ticket);
            reservation.setSeatStatus(SeatStatus.PURCHASED);
        }
        ticketRepository.save(ticket);
        //updateBookingStatusAndTicket(reservations,ticket);

        String generatedEmail = emailService.generateTicketPurchaseEmail(appUser,reservations,totalPrice,"Card");
        emailService.send(appUser.getEmail(),generatedEmail,"Ticket Purchase Confirmation");
        return ResponseEntity.ok().body(ticket);
    }
    private AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (AppUser) authentication.getPrincipal();
        } else {
            throw new IllegalStateException("The user is not authenticated");
        }
    }
    private BigDecimal calculateTotalPrice(List<Booking> reservations){
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for(Booking reservation : reservations){
            totalPrice = totalPrice.add(reservation.getSeatPricing().getPrice());
        }
        return totalPrice;
    }

    public List<Booking> getTicket(Long ticketId) {
        return bookingRepository.findAllByTicketTicketId(ticketId);
    }

//    @Transactional
//    private void updateBookingStatusAndTicket(List<Booking> reservations,Ticket ticket){
//
//    }

}
