//package com.example.movieApp.api.admin.seat;
//
//import com.example.movieApp.entities.Seat;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(path = "/api/admin/seat")
//public class AdminSeatController {
//
//    private final AdminSeatService adminSeatService;
//
//    @Autowired
//    public AdminSeatController(AdminSeatService adminSeatService) {
//        this.adminSeatService = adminSeatService;
//    }
//
//
//    // I am not sure if it is actually needed
//    @PostMapping(path = "/createSeat")
//    public ResponseEntity<Seat> createSeat(@RequestBody SeatDTO seatDTO){
//        return adminSeatService.createSeat(seatDTO);
//    }
//
//}
