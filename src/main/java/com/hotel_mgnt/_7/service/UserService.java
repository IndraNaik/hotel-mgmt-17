package com.hotel_mgnt._7.service;

import com.hotel_mgnt._7.dto.UserDto;
import com.hotel_mgnt._7.exceptions.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Response<UserDto> addCustomer(UserDto userDto);

    Response<UserDto> addAdmin(UserDto userDto);

    Response<UserDto> addWaiter(UserDto userDto, long id);

    Page<UserDto> allUsers(Pageable pageable);

    Response<Page<UserDto>> getAllAdmins(int page, int size);

    Response<Page<UserDto>> getAllCustomers(int page, int size);

    Response<Page<UserDto>> getAllWaiters(int page, int size);

    Response<UserDto> softDeleteWaiter(long waiterId, long adminId);
}
