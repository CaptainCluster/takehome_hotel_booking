package com.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest
{
    /**
     * The system should block the creation of new reservations
     * if a customer already has a non-expired reservation
     */
    @Test
    void testExpiredReservation()
    {
        LocalDateTime startDate = LocalDateTime.of(1999, 3, 12, 18, 30);
        LocalDateTime endDate = LocalDateTime.of(1999, 3, 13, 10, 0);
        Booking booking = new Booking(startDate, endDate);

        assertEquals(true, booking.checkExpired(), "The reservation should be considered an expired one!");
    }

    @Test
    void testNonExpiredReservation()
    {
        LocalDateTime startDate = LocalDateTime.of(3000, 3, 12, 18, 30);
        LocalDateTime endDate = LocalDateTime.of(3000, 3, 13, 10, 0);
        Booking booking = new Booking(startDate, endDate);

        assertEquals(false, booking.checkExpired(), "The reservation has not expired!");
    }

}