package com.hotel_mgnt._7.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class BookingTable extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private User customer;


    @ManyToOne
    @JoinColumn(name = "waiter_id",referencedColumnName = "id")
    private User waiter;


    private boolean booking_status;


    @OneToOne
    @JoinColumn(name="table_id",referencedColumnName = "id")
    private Tables table;

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

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }
}