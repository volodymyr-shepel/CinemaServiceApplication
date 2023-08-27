package com.example.movieApp.scheduler;

import com.example.movieApp.entities.SeatStatus;
import com.example.movieApp.entityRepositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExpiredReservationCleanUpTask {

    private final ExpiredReservationCleanupService expiredReservationCleanupService;


    @Autowired
    public ExpiredReservationCleanUpTask(ExpiredReservationCleanupService expiredReservationCleanupService) {
        this.expiredReservationCleanupService = expiredReservationCleanupService;
    }

    @Scheduled(fixedRate = 60000)
    public void cleanupExpiredReservations(){
        expiredReservationCleanupService.cleanUpExpiredReservations();
    }

}
