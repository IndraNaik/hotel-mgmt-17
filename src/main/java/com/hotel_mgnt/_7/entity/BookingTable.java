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

}