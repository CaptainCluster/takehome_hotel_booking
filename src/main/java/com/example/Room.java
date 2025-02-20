package com.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room
{
    protected int areaMeasure;
    protected int number;
    protected int price;
    protected final Set<Booking> bookings;
    protected HotelBuilding hotelBuilding;
    protected List<Furniture> furnitures;

    public Room(int areaMeasure, int number, int price, HotelBuilding hotelBuilding)
    {
        this.areaMeasure = areaMeasure;
        this.number = number;
        this.price = price;
        this.bookings = new HashSet<>();
        this.hotelBuilding = hotelBuilding;
    }

    public int getAreaMeasure()
    {
        return areaMeasure;
    }

    public void setAreaMeasure(int areaMeasure)
    {
        this.areaMeasure = areaMeasure;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public Set<Booking> getReservations()
    {
        return bookings;
    }

}
