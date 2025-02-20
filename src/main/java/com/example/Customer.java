package com.example;

import java.util.UUID;

/**
 * Practically the one reserving a room. Inherits from Individual class and
 * contains information regarding booking.
 */
public class Customer extends Individual
{
    private UUID customerId;
    private Booking booking;

    public Customer(String name)
    {
        super(name);
        this.customerId = UUID.randomUUID();
    }

    public Customer(String name, String email, String phoneNumber)
    {
        super(name, email, phoneNumber);
    }

    public UUID getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(UUID customerId)
    {
        this.customerId = customerId;
    }

    public Booking getReservation()
    {
        return booking;
    }

    public void setReservation(Booking booking)
    {
        this.booking = booking;
    }
}
