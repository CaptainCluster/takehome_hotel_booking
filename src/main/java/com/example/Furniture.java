package com.example;

/**
 * Rooms should have various pieces of furniture. The quantity
 * of furniture in a room is zero or above.
 */
public class Furniture
{
    private String name;
    private int quantity;

    public Furniture(String name)
    {
        this.name = name;
        this.quantity = 0;
    }

    public Furniture(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
