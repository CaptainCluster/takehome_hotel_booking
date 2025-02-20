package com.example;

import java.time.LocalDateTime;
import java.util.*;

/**
 * A singleton class that allows users to book 12-hour timeslots for
 * hotel rooms.
 *
 * Automatically checks for overlapping slots to ensure only one
 * customer can have a room reserved at once.
 */
public class SchedulingSystem
{
    private static SchedulingSystem instance;
    private Set<HotelBuilding> hotelBuildings;
    private Customer sessionCustomer;

    private SchedulingSystem() {}



    public static SchedulingSystem getInstance()
    {
        if(instance == null)
        {
            instance = new SchedulingSystem();
        }
        return instance;
    }

    /**
     * Reserving a hotel room for a customer
     */
    public void bookHotelRoom(HotelBuilding hotelBuilding, Customer customer, LocalDateTime newStartDate)
    {
        Set<Room> availableRooms = hotelBuilding.getRooms();
        List<Room> listAvailableRooms = List.copyOf(availableRooms);

        Room roomToReserve = listAvailableRooms.get(new Random().nextInt(listAvailableRooms.size()));

        // Creating the reservation
        Booking booking = new Booking(newStartDate, newStartDate.plusHours(12));

        /**
         * If the proposed reservation is invalid, it will not be added.
         * An error is printed.
         */
        if (!checkBookingValidity(booking, roomToReserve))
        {
            System.err.println(customer.getName()
                    + " failed to reserve room #"
                    + roomToReserve.getNumber()
                    + " due to an overlapping booking!");
            return;
        }
        roomToReserve.getReservations().add(booking);
        customer.setReservation(booking);
    }


    /**
     * Ensures a reservation timing is valid and does not overlap with
     * another one. Should be run before adding the reservation.
     *
     * The reservation should be completely before all the other ones
     * or completely after them.
     *
     * @returns boolean
     * true  - reservation is valid
     * false - reservation is invalid
     */
    public boolean checkBookingValidity(Booking newBooking, Room room)
    {
        // A reservation should not be expired at the moment
        if (newBooking.checkExpired())
        {
            System.err.println("Bookings should be made for the future, not the past!");
            return false;
        }

        LocalDateTime newStartDate = newBooking.getStartDate();
        LocalDateTime newEndDate = newBooking.getEndDate();

        // Ensuring the start date is before end date, and that the start and end are not the same
        if (newStartDate.isAfter(newEndDate) || newStartDate.equals(newEndDate))
        {
            return false;
        }

        // Each reservation slot is 12 hours. This makes checking for overlaps
        // simpler
        for (Booking booking : room.getReservations())
        {

            LocalDateTime startDate = booking.getStartDate();
            LocalDateTime endDate = booking.getEndDate();

            if (newStartDate.isBefore(startDate))
            {
                if (newEndDate.isBefore(endDate) && newEndDate.isAfter(startDate))
                {
                    return false;
                }
            }
            else if (startDate.isBefore(newStartDate))
            {
                if (endDate.isBefore(newEndDate) && endDate.isAfter(newStartDate))
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public Set<HotelBuilding> getHotelBuildings()
    {
        return hotelBuildings;
    }

    public void setHotelBuildings(Set<HotelBuilding> hotelBuildings)
    {
        this.hotelBuildings = hotelBuildings;
    }

    public Customer getSessionCustomer()
    {
        return sessionCustomer;
    }

    public void setSessionCustomer(Customer sessionCustomer)
    {
        this.sessionCustomer = sessionCustomer;
    }
}
