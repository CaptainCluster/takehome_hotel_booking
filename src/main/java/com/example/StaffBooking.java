package com.example;

import java.time.LocalDateTime;

public class StaffBooking extends Booking
{
    private Staff staff;

    public StaffBooking(LocalDateTime startDate, LocalDateTime endDate, Staff staff)
    {
        super(startDate, endDate);
        this.staff = staff;
    }

    public Staff getStaff()
    {
        return staff;
    }

    public void setStaff(Staff staff)
    {
        this.staff = staff;
    }
}
