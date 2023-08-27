package com.example.movieApp.scheduler;

import com.example.movieApp.appUser.AppUser;
import com.example.movieApp.email.EmailService;
import com.example.movieApp.entities.Booking;
import com.example.movieApp.entities.SeatStatus;
import com.example.movieApp.entityRepositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Service
public class ExpiredReservationCleanupService {
    private final BookingRepository bookingRepository;

    private final EmailService emailService;

    @Autowired
    public ExpiredReservationCleanupService(BookingRepository bookingRepository, EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void cleanUpExpiredReservations() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Booking> expiredReservations = bookingRepository.findAllByExpirationTimeBeforeAndSeatStatus(currentTime, SeatStatus.RESERVED);

        Set<AppUser> distinctUsers = extractDistinctUsers(expiredReservations);
        deleteExpiredReservations(expiredReservations);

        sendEmailsToDistinctUsers(distinctUsers);
    }

    private Set<AppUser> extractDistinctUsers(List<Booking> expiredReservations) {
        Set<AppUser> distinctUsers = new HashSet<>();
        for (Booking booking : expiredReservations) {
            distinctUsers.add(booking.getAppUser());
        }
        return distinctUsers;
    }

    private void deleteExpiredReservations(List<Booking> expiredReservations) {
        for (Booking booking : expiredReservations) {
            bookingRepository.delete(booking);
        }
    }

    private void sendEmailsToDistinctUsers(Set<AppUser> distinctUsers) {
        for (AppUser appUser : distinctUsers) {
            emailService.send(appUser.getEmail(), emailService.generateReservationExpiredEmail(appUser.getFirstName()), "Reservation expired");
        }
    }


}
