package com.example.movieApp.entities;

import com.example.movieApp.appUser.AppUser;
import com.example.movieApp.entities.MovieSession;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Entity
public class Ticket {




    // total price will be retrieved dynamically, when needed
    @Id
    @GeneratedValue
    private Long ticketId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;


    private BigDecimal totalPrice;


    public Ticket(AppUser appUser, BigDecimal totalPrice) {
        this.appUser = appUser;
        this.totalPrice = totalPrice;
    }
    public Ticket(){

    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
