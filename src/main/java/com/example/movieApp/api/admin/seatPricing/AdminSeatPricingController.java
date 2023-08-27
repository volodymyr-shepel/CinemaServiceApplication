package com.example.movieApp.api.admin.seatPricing;

import com.example.movieApp.entities.SeatPricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/admin/seatPricing")
public class AdminSeatPricingController {
    private final AdminSeatPricingService adminSeatPricingService;

    @Autowired
    public AdminSeatPricingController(AdminSeatPricingService adminSeatPricingService) {
        this.adminSeatPricingService = adminSeatPricingService;
    }
    @GetMapping(path = "/getAllSeatPrices")
    public List<SeatPricing> getAllSeatPrices(){
        return adminSeatPricingService.getAllSeatPrices();
    }

    @PostMapping(path = "/setUpThePrice")
    public ResponseEntity<SeatPricing> setSeatPricing(@RequestBody SeatPricingDTO seatPricingDTO){
        return adminSeatPricingService.setSeatPricing(seatPricingDTO);
    }
    @PutMapping(path = "/updateTheSeatPrice")
    public ResponseEntity<SeatPricing> updateSeatPricing(@RequestBody SeatPricingDTO seatPricingDTO){
        return adminSeatPricingService.updateSeatPricing(seatPricingDTO);
    }

}
