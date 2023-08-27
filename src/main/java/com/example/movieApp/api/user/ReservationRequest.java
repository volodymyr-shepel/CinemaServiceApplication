package com.example.movieApp.api.user;


import java.util.List;

public class ReservationRequest {
    private List<BookingDTO> bookingDTOList;

    public ReservationRequest(List<BookingDTO> bookingDTOList) {
        this.bookingDTOList = bookingDTOList;
    }
    public ReservationRequest(){

    }

    public List<BookingDTO> getBookingDTOList() {
        return bookingDTOList;
    }

    public void setBookingDTOList(List<BookingDTO> bookingDTOList) {
        this.bookingDTOList = bookingDTOList;
    }
}
