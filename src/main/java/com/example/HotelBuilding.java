package com.example;

import java.util.Set;

/**
 * A HotelBuilding can have multiple hotel rooms. The relation between a Room
 * and a HotelBuilding is one where the former is strongly dependent on the latter.
 */
public class HotelBuilding
{
    private String location;
    private final Set<Room> rooms;


    public HotelBuilding(String location, Set<Room> rooms)
    {
        this.location = location;
        this.rooms = rooms;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Set<Room> getRooms()
    {
        return rooms;
    }
}
