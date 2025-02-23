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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<UserDto> allUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::entityToDto);
    }

    @Override
    public Response<Page<UserDto>> getAllAdmins(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> adminsPage = userRepository.findByRole(Role.ADMIN, pageable);
        Page<UserDto> dtoPage = adminsPage.map(this::entityToDto);
        return new Response<>(dtoPage, "Admins fetched successfully", HttpStatus.OK);
    }

    @Override
    public Response<Page<UserDto>> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> adminsPage = userRepository.findByRole(Role.CUSTOMER, pageable);
        Page<UserDto> dtoPage = adminsPage.map(this::entityToDto);
        return new Response<>(dtoPage, "Customers fetched successfully", HttpStatus.OK);
    }

    @Override
    public Response<Page<UserDto>> getAllWaiters(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> adminsPage = userRepository.findByRoleAndStatusTrue(Role.WAITER, pageable);
        Page<UserDto> dtoPage = adminsPage.map(this::entityToDto);
        return new Response<>(dtoPage, "Waiters fetched successfully", HttpStatus.OK);
    }

    @Override
    public Response<UserDto> softDeleteWaiter(long waiterId, long adminId) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));

        if (admin.getRole() != Role.ADMIN) {
            return new Response<UserDto>(null, "Only Admins can deactivate waiters", HttpStatus.FORBIDDEN);
        }

        User waiter = userRepository.findById(waiterId)
                .orElseThrow(() -> new ResourceNotFoundException("Waiter not found with ID: " + waiterId));

        if (waiter.getRole() != Role.WAITER) {
            return new Response<UserDto>(null, "The provided user is not a Waiter", HttpStatus.BAD_REQUEST);
        }

        waiter.setStatus(false);
        userRepository.save(waiter);

        return new Response<UserDto>(null, "Waiter deactivated successfully", HttpStatus.OK);
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
