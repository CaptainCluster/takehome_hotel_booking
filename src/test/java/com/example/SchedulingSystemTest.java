package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchedulingSystemTest
{

    /**
     * Setting up the scheduling system with mock data before tests.
     * This occurs before every single test.
     */
    @BeforeEach
    public void testSetup()
    {
        SchedulingSystem schedulingSystem = SchedulingSystem.getInstance();

        // Room data
        Set<Room> roomSet = new HashSet<>();

        HotelBuilding hotelBuilding = new HotelBuilding("Oklahoma", roomSet);
        Room room1 = new Room(34, 1, 200, hotelBuilding);
        hotelBuilding.getRooms().add(room1);

        Set<HotelBuilding> hotelBuildingSet = new HashSet<>();
        hotelBuildingSet.add(hotelBuilding);
        schedulingSystem.setHotelBuildings(hotelBuildingSet);
    }

   

    /**
     * Testing whether multiple people can reserve a room from a hotel building.
     * The timeslots are valid, and thus both customers should be able to do it
     * successfully.
     */
    @Test
    void testManyCanReserveRooms()
    {
        int successfulReservations = 0; // Expected: 2

        // Fetching a non-reserved room (created in setup)
        SchedulingSystem schedulingSystem = SchedulingSystem.getInstance();
        List<HotelBuilding> hotelBuildingList = List.copyOf(schedulingSystem.getHotelBuildings());
        HotelBuilding hotelBuilding = hotelBuildingList.get(0);

        // Creating two customers. Both Jan and Mik should be able to
        // book the room without an issue.
        Customer customer = new Customer("Jan");
        Customer customer2 = new Customer("Mik");

        LocalDateTime startDate = LocalDateTime.of(2027, 1, 1, 0, 0);
        LocalDateTime startDate2 = LocalDateTime.of(2027, 2, 2, 0, 0);

        schedulingSystem.bookHotelRoom(hotelBuilding, customer, startDate);
        schedulingSystem.bookHotelRoom(hotelBuilding, customer2, startDate2);

        for (Room room : hotelBuilding.getRooms())
        {
            successfulReservations += room.getReservations().size();
        }

        assertEquals(2, successfulReservations, "Not all customers were able to reserve rooms!");
    }

    /**
     * Overlapping bookings indicate the chronologically latter one
     * is invalid, and thus it should be automatically rejected by
     * the system.
     */
    @Test
    public void testNoOverlappingBookings()
    {
        int successfulReservations = 0; // Expected: 1

        // Fetching a non-reserved room (created in setup)
        SchedulingSystem schedulingSystem = SchedulingSystem.getInstance();
        List<HotelBuilding> hotelBuildingList = List.copyOf(schedulingSystem.getHotelBuildings());
        HotelBuilding hotelBuilding = hotelBuildingList.get(0);

        // Creating two customers. Jan should be able to book a room, whereas Mik
        // will try to propose an overlapping booking.
        Customer customer = new Customer("Jan");
        Customer customer2 = new Customer("Mik");

        // Reservation times for the customers (startDate2 is invalid and overlaps)
        LocalDateTime startDate = LocalDateTime.of(2027, 1, 1, 0, 0);
        LocalDateTime startDate2 = LocalDateTime.of(2027, 1, 1, 6, 0);

        // Attempting to reserve
        schedulingSystem.bookHotelRoom(hotelBuilding, customer, startDate);
        schedulingSystem.bookHotelRoom(hotelBuilding, customer2, startDate2);

        // Reservation array indicates how many are successful
        for (Room room : hotelBuilding.getRooms())
        {
            successfulReservations += room.getReservations().size();
        }
        assertEquals(1, successfulReservations, "Overlapping bookings detected!");
    }
}