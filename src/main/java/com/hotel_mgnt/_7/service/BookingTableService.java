package com.hotel_mgnt._7.service;

import com.hotel_mgnt._7.dto.BookingTableDto;
import com.hotel_mgnt._7.exceptions.Response;

public interface BookingTableService {
    Response<BookingTableDto> bookTable(BookingTableDto bookingTableDto);

    Response<BookingTableDto> assignWaiter(BookingTableDto bookingTableDto);
}
