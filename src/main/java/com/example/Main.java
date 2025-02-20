package com.example;

import java.time.LocalDateTime;
import java.util.*;

public class Main
{
    /**
     * NOTE! The main function centers around demonstrating aspects regarding
     * the requirements defined in the take-home assignment.
     *
     * The main program runs a scenario where multiple users exist, but a new
     * one can be created. A hotel building with a room exists, with one
     * time being already booked.
     *
     * The main purpose of this is to simulate a scenario where the customer
     * can attempt to book a time for a room where overlap would occur.
     */
    public static void main(String[] args)
    {
        Customer customer = null;
        Scanner sc = new Scanner(System.in);
        try
        {
            System.out.println("Would you like to create a new user or select an existing one?");
            System.out.println("1) New, 2) old");
            int userInput = Integer.parseInt(sc.nextLine());

            /**
             * Case 1 - Create new user
             * Case 2 - Select an existing user
             */
            switch (userInput) {
                case 1:
                    System.out.println("Name: ");
                    String customerName = sc.nextLine();
                    customer = new Customer(customerName);
                    break;

                case 2:
                    int selectCustomerInput;
                    int index = 0;
                    List<Customer> customers = createCustomerMockData();
                    for (Customer existingCustomer : customers) {
                        System.out.println((index+1) + ") " + existingCustomer.getName());
                        index++;
                    }
                    selectCustomerInput = Integer.parseInt(sc.nextLine());
                    customer = customers.get(selectCustomerInput-1);
            }
            SchedulingSystem schedulingSystem = SchedulingSystem.getInstance();
            schedulingSystem.setSessionCustomer(customer);

            // Null-check on the customer that should be in use by now
            if (customer == null)
            {
                System.err.println("Customer shouldn't be null after starting the session.");
            }

            // Creating a hotel room
            Set<Room> roomSet = new HashSet<>();
            HotelBuilding hotelBuilding = new HotelBuilding("Pennsylvania", roomSet);
            Room room = new Room(34, 1, 250, hotelBuilding);
            hotelBuilding.getRooms().add(room);

            // Creating a hotel building where the room is
            Set<HotelBuilding> hotelBuildingSet = new HashSet<>();
            hotelBuildingSet.add(hotelBuilding);
            schedulingSystem.setHotelBuildings(hotelBuildingSet);

            // Another Customer books a time in the room
            Customer bookingCustomer = new Customer("James Hetfield");
            LocalDateTime bookingCustomerStartTime = LocalDateTime
                    .of(2025, 3, 1, 0, 0);
            schedulingSystem.bookHotelRoom(hotelBuilding, bookingCustomer, bookingCustomerStartTime);

            System.out.println("Welcome, " + schedulingSystem.getSessionCustomer().getName() + "!");

            // Printing the other customer's booking to promote the demonstration
            // of cases where an attempt to make an overlapping booking is made
            System.out.println("The room you are booking already has the following bookings: "
                    + bookingCustomerStartTime);

            // Your booking (if you book for the same time, it will be rejected)
            LocalDateTime startTime = askInputStartTime(sc);
            schedulingSystem.bookHotelRoom(hotelBuilding, customer, startTime);

            assert customer != null;
            if (customer.getReservation() == null)
            {
                System.err.println("Hotel room booking failed.");
                return;
            }
            System.out.println("You successfully booked a room!");
        }
        catch (NumberFormatException e)
        {
            System.err.println("Invalid input. Please enter a valid number.");
        }
        catch (Exception e)
        {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            sc.close();
        }
    }

    /**
     * Some mock-data that can be used when choosing an existing
     * customer (a loose simulation of a system where accounts should
     * exist)
     * @return List<Customer> a list of Customer objects
     */
    static List<Customer> createCustomerMockData()
    {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Ville"));
        customers.add(new Customer("Joel"));
        return customers;
    }

    /**
     *
     * @param sc - Scanner for collecting inputs
     * @return - LocalDateTime - The starting time for reservation
     */
    static LocalDateTime askInputStartTime(Scanner sc)
    {
        System.out.println("When would you like the booked time to be?");
        final int year = 2025;
        final int minutes = 0;

        System.out.println("Month: ");
        int month = Integer.parseInt(sc.nextLine());

        System.out.println("Day: ");
        int day = Integer.parseInt(sc.nextLine());

        System.out.println("Clock (hours)");
        int hours = Integer.parseInt(sc.nextLine());

        return LocalDateTime.of(year, month, day, hours, minutes);
    }
}