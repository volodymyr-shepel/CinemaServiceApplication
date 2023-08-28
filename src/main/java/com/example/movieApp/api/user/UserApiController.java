package com.example.movieApp.api.user;

import com.example.movieApp.entities.Booking;
import com.example.movieApp.entities.Seat;
import com.example.movieApp.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserApiController {

    private final UserApiReservationService userApiReservationService;

    private final UserApiPurchaseService userApiPurchaseService;

    @Autowired
    public UserApiController(UserApiReservationService userApiReservationService, UserApiPurchaseService userApiPurchaseService) {
        this.userApiReservationService = userApiReservationService;
        this.userApiPurchaseService = userApiPurchaseService;
    }

    @GetMapping(path = "/getAvailableSeats")
    public List<Seat> getAvailableSeats(@RequestParam Long movieSessionId){
        return userApiReservationService.getAvailableSeats(movieSessionId);
    }

    @GetMapping(path = "/getReservations")
    public List<Booking> getReservations(){
        return userApiReservationService.getReservations();
    }

    @PostMapping(path = "/reserveSeats")
    public @ResponseBody List<Booking> reserveSeat(@RequestBody ReservationRequest reservationRequest){
        return userApiReservationService.reserveSeats(reservationRequest);
    }


    @DeleteMapping(path = "/cancelAllReservations")
    public ResponseEntity<String> cancelAllReservations(){
        return userApiReservationService.cancelAllReservations();
    }

    @PostMapping(path = "/purchaseTicket")
    public ResponseEntity<Ticket> purchaseTicket(){
        return userApiPurchaseService.purchaseTicket();
    }


    @GetMapping(path = "/getTicket/{ticketId}")
    public List<Booking> getTicket(@PathVariable Long ticketId){
        return userApiPurchaseService.getTicket(ticketId);
    }
}
