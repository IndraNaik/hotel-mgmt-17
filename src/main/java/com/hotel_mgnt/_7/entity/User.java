package com.hotel_mgnt._7.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel_mgnt._7.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private String phoneNo;

    private boolean status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Tables> tables;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<BookingTable> bookingTables1;
    @OneToMany(mappedBy = "waiter")
    @JsonIgnore
    private List<BookingTable> bookingTables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Tables> getTables() {
        return tables;
    }

    public void setTables(List<Tables> tables) {
        this.tables = tables;
    }

    public List<BookingTable> getBookingTables1() {
        return bookingTables1;
    }

    public void setBookingTables1(List<BookingTable> bookingTables1) {
        this.bookingTables1 = bookingTables1;
    }

    public List<BookingTable> getBookingTables() {
        return bookingTables;
    }

    public void setBookingTables(List<BookingTable> bookingTables) {
        this.bookingTables = bookingTables;
    }
}
