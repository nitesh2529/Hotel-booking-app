package com.example.hotelbackend.response;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;


@Data
// @NoArgsConstructor
public class RoomResponse {
    @Id
    private String id;
    private String roomType;
    private BigDecimal roomNo;
    private BigDecimal roomPrice;
    private boolean isBooked;
    private String photo;
    private List<BookingResponse> bookings;

    public RoomResponse(String id, String roomType, BigDecimal roomPrice, BigDecimal roomNo) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomNo = roomNo;
    }

    public RoomResponse(String id, String roomType, BigDecimal roomPrice, BigDecimal roomNo, boolean isBooked,
                         String photoUrl, List<BookingResponse> bookings) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomNo = roomNo;
        this.isBooked = isBooked;
        this.photo = photoUrl;
        this.bookings = bookings;
    }

}
