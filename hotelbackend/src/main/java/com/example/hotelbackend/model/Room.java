package com.example.hotelbackend.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "rooms")
// @AllArgsConstructor
public class Room {

    @Id
    private String id;   // MongoDB uses String or ObjectId

    private String roomType;
    private BigDecimal roomPrice;
    private BigDecimal roomNo;
    private boolean isBooked = false;

    // ID of image stored in GridFS
    // private String photoId;
private String photoUrl;
    // Store BookedRoom documents reference
   private String photoPublicId;
    @DBRef 
    private List<BookedRoom> bookings = new ArrayList<>();

    public void addBooking(BookedRoom booking){
        if (bookings == null){
            bookings = new ArrayList<>();
        }

        bookings.add(booking);

        isBooked = true;

        // Generate unique code for confirmation
        String bookingCode = UUID.randomUUID().toString();
        booking.setBookingConfirmationCode(bookingCode);
    }

}
