package com.hotel_mgnt._7.serviceImpl;

import com.hotel_mgnt._7.dto.UserDto;
import com.hotel_mgnt._7.entity.User;
import com.hotel_mgnt._7.enums.Role;
import com.hotel_mgnt._7.exceptions.Response;
import com.hotel_mgnt._7.repository.UserRepository;
import com.hotel_mgnt._7.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Response<UserDto> addCustomer(UserDto userDto) {
        var user = dtoToEntity(userDto);
        user.setStatus(userDto.isStatus());
        user.setName(userDto.getName());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setRole(Role.CUSTOMER);
        user.setCreatedBy(user.getId());
        user.setLastModifiedBy(user.getId());
        var savedUser = userRepository.save(user);
        return new Response<>(entityToDto(savedUser), "Customer added", HttpStatus.CREATED);
    }

    @Override
    public Response<UserDto> addAdmin(UserDto userDto) {
        return null;
    }

    @Override
    public Response<UserDto> addWaiter(UserDto userDto, long id) {
        return null;
    }


    // Entity to Dto
    private UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    // Dto to Entity
    private User dtoToEntity(UserDto userDto) {
        User user = new User();
        user = modelMapper.map(userDto, User.class);
        return user;
    }
}
