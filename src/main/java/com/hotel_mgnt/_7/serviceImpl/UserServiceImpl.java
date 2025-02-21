package com.hotel_mgnt._7.serviceImpl;

import com.hotel_mgnt._7.dto.UserDto;
import com.hotel_mgnt._7.entity.User;
import com.hotel_mgnt._7.enums.Role;
import com.hotel_mgnt._7.exceptions.ResourceNotFoundException;
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
        Response<UserDto> response = new Response<UserDto>(null, "Processing request", HttpStatus.OK);
        if (userRepository.findByPhoneNo(userDto.getPhoneNo()).isPresent()) {
            response.setMessage("User with phone number already exists");
            response.setHttpStatus(HttpStatus.CONFLICT);
            return response;
        }
        User user = dtoToEntity(userDto);
        user.setStatus(userDto.isStatus());
        user.setName(userDto.getName());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setRole(Role.ADMIN);
        user.setCreatedBy(user.getId());
        user.setLastModifiedBy(user.getId());
        User savedUser = userRepository.save(user);
        response.setData(entityToDto(savedUser));
        response.setMessage("Admin added successfully");
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }


    @Override
    public Response<UserDto> addWaiter(UserDto userDto, long id) {
        Response<UserDto> response = new Response<UserDto>(null, "Processing request", HttpStatus.OK);
        try {
            User admin = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + id));
            if (admin.getRole() == Role.ADMIN) {
                User user = dtoToEntity(userDto);
                user.setName(userDto.getName());
                user.setStatus(userDto.isStatus());
                user.setPhoneNo(userDto.getPhoneNo());
                user.setRole(Role.WAITER);
                user.setCreatedBy(admin.getId());
                user.setLastModifiedBy(admin.getId());
                User savedUser = userRepository.save(user);
                response.setData(entityToDto(savedUser));
                response.setHttpStatus(HttpStatus.CREATED);
                response.setMessage("Waiter added successfully");
            } else {
                response.setMessage("Waiter can only be added by an Admin");
                response.setHttpStatus(HttpStatus.FORBIDDEN);
            }
        } catch (ResourceNotFoundException e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.setMessage("An error occurred: " + e.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
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
