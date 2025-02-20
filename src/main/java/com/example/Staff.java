package com.example;

import java.util.Set;

/**
 * Staff inherits attributes and methods from individual.
 * A staff member provides services for those who have
 * booked a deluxe room.
 */
public class Staff extends Individual
{
    private Set<DeluxeRoom> deluxeRooms;

    public Staff(String name)
    {
        super(name);
    }

    public Staff(String name, String email, String phoneNumber)
    {
        super(name, email, phoneNumber);
    }

    /**
     * All staff members should have company emails. This is
     * ensured by polymorphism
     */
    @Override
    public void setEmail(String email)
    {
        if (!email.contains("@"))
        {
            this.email = email;
            return;
        }
        // Adding the company name to the email of the staff member
        this.email = email.split("@")[0] + "@bookingcompany.com";
    }

    public Set<DeluxeRoom> getDeluxeRooms()
    {
        return deluxeRooms;
    }

    public void setDeluxeRooms(Set<DeluxeRoom> deluxeRooms)
    {
        this.deluxeRooms = deluxeRooms;
    }
}
