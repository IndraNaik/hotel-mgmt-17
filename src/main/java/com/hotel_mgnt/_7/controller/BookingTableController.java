package com.hotel_mgnt._7.controller;

import com.hotel_mgnt._7.dto.BookingTableDto;
import com.hotel_mgnt._7.exceptions.Response;
import com.hotel_mgnt._7.service.BookingTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookingTableController {

    @Autowired
    private BookingTableService bookingTableService;

    @PostMapping("table")
    public ResponseEntity<Response<BookingTableDto>> bookTable(@RequestBody BookingTableDto bookingTableDto) {
        return ResponseEntity.ok(bookingTableService.bookTable(bookingTableDto));
    }

    @PostMapping("assignWaiter")
    public ResponseEntity<Response<BookingTableDto>> assignWaiter(@RequestBody BookingTableDto bookingTableDto) {
        return ResponseEntity.ok(bookingTableService.assignWaiter(bookingTableDto));
    }

}
