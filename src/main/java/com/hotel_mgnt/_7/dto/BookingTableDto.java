package com.hotel_mgnt._7.dto;

import com.hotel_mgnt._7.entity.Tables;
import com.hotel_mgnt._7.entity.User;
import lombok.Data;

@Data
public class BookingTableDto {

    private long id;

    private User customer;

    private User waiter;

    private boolean booking_status;

    private Tables table_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getWaiter() {
        return waiter;
    }

    public void setWaiter(User waiter) {
        this.waiter = waiter;
    }

    public boolean isBooking_status() {
        return booking_status;
    }

    public void setBooking_status(boolean booking_status) {
        this.booking_status = booking_status;
    }

    public Tables getTable_id() {
        return table_id;
    }

    public void setTable_id(Tables table_id) {
        this.table_id = table_id;
    }
}