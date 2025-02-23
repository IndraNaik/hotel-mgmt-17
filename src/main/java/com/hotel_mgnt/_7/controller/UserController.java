package com.hotel_mgnt._7.controller;

import com.hotel_mgnt._7.dto.UserDto;
import com.hotel_mgnt._7.entity.Tables;
import com.hotel_mgnt._7.exceptions.BadRequestAlertException;
import com.hotel_mgnt._7.exceptions.Response;
import com.hotel_mgnt._7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add/customer")
    public ResponseEntity<Response<UserDto>> addCustomer(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addCustomer(userDto));
    }

    @PostMapping("/add/admin")
    public ResponseEntity<Response<UserDto>> addAdmin(@RequestBody UserDto userDto) {
        if (userDto.getId() != null) {
            throw new BadRequestAlertException();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAdmin(userDto));
    }

    @PostMapping("/add/waiter/{id}")
    public ResponseEntity<Response<UserDto>> addWaiter(@RequestBody UserDto userDto, @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addWaiter(userDto, id));
    }

    @GetMapping("/get-all")
    public Page<UserDto> getUsers(Pageable pageable){
        return userService.allUsers(pageable);
    }

    @GetMapping("/get/admins")
    public ResponseEntity<Response<Page<UserDto>>> getAllAdmins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.getAllAdmins(page, size));
    }

    @GetMapping("/get/customers")
    public ResponseEntity<Response<Page<UserDto>>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.getAllCustomers(page, size));
    }

    @GetMapping("/get/waiters")
    public ResponseEntity<Response<Page<UserDto>>> getAllWaiters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.getAllWaiters(page, size));
    }

    @DeleteMapping("/delete/waiters/{waiterId}/byAdmin/{adminId}")
    public ResponseEntity<Response<UserDto>> softDeleteWaiter(
            @PathVariable long waiterId,
            @PathVariable long adminId) {
        return ResponseEntity.ok(userService.softDeleteWaiter(waiterId, adminId));
    }


}