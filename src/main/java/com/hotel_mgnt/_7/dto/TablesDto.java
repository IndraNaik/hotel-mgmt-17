package com.hotel_mgnt._7.dto;

import com.hotel_mgnt._7.entity.User;
import lombok.Data;

@Data
public class TablesDto {

    private long id;

    private boolean status;

    private User admin;

    private float price;

    private boolean booking_status;

}
