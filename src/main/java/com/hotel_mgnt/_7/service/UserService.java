package com.hotel_mgnt._7.service;

import com.hotel_mgnt._7.dto.UserDto;
import com.hotel_mgnt._7.exceptions.Response;

public interface UserService {
    Response<UserDto> addCustomer(UserDto userDto);

    Response<UserDto> addAdmin(UserDto userDto);

    Response<UserDto> addWaiter(UserDto userDto, long id);}
