package com.example;

import java.util.HashSet;
import java.util.Set;

/**
 * Deluxe rooms inherit the functionalities of the Room class.
 * All customers who book a deluxe room will be provided the
 * services of a staff member who helps improve the customer
 * experience.
 */
public class DeluxeRoom extends Room
{
    private final Set<StaffBooking> staffBookings;

    public DeluxeRoom(int area, int number, int price, HotelBuilding hotelBuilding)
    {
        super(area, number, price, hotelBuilding);
        this.staffBookings = new HashSet<>();
    }

    public Set<StaffBooking> getStaffBookings()
    {
        return staffBookings;
    }
}
