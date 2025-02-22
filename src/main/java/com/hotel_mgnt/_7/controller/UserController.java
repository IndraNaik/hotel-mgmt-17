package com.hotel_mgnt._7.controller;

import com.hotel_mgnt._7.dto.UserDto;
import com.hotel_mgnt._7.exceptions.BadRequestAlertException;
import com.hotel_mgnt._7.exceptions.Response;
import com.hotel_mgnt._7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user/add")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/Customer")
    public ResponseEntity<Response<UserDto>> addCustomer(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addCustomer(userDto));
    }

    @PostMapping("/Admin")
    public ResponseEntity<Response<UserDto>> addAdmin(@RequestBody UserDto userDto) {
        if (userDto.getId() != null) {
            throw new BadRequestAlertException();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAdmin(userDto));
    }

    @PostMapping("/Waiter/{id}")
    public ResponseEntity<Response<UserDto>> addWaiter(@RequestBody UserDto userDto, @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addWaiter(userDto, id));
    }
}