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
}