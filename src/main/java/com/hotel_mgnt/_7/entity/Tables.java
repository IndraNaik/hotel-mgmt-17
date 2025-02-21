package com.hotel_mgnt._7.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tables extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private boolean bookingStatus;

    @OneToOne(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private BookingTable bookingTable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingTable getBookingTable() {
        return bookingTable;
    }

    public void setBookingTable(BookingTable bookingTable) {
        this.bookingTable = bookingTable;
    }
}