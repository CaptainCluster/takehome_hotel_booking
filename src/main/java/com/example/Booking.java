package com.example;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The class containing core booking information, such as the time
 * when the booking starts, and when it ends. It is associated with
 * the customer via their ID. A room can have multiple bookings, but
 * all occur at different times and should not overlap.
 */
public class Booking
{
    protected UUID bookingId;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected UUID customerId;

    public Booking(LocalDateTime startDate, LocalDateTime endDate)
    {
        this.bookingId = UUID.randomUUID();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking(LocalDateTime startDate, LocalDateTime endDate, UUID customerId)
    {
        this.bookingId = UUID.randomUUID();
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getStartDate()
    {
        return startDate;
    }

    public LocalDateTime getEndDate()
    {
        return endDate;
    }

    public void setStartDate(LocalDateTime startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate)
    {
        this.endDate = endDate;
    }

    /**
     * Checking whether the end date occurs/occurred
     * before or after present.
     * @return boolean
     */
    public boolean checkExpired()
    {
        return endDate.isBefore(LocalDateTime.now());
    }
}
