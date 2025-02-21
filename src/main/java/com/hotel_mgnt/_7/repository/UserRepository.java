package com.hotel_mgnt._7.repository;

import com.hotel_mgnt._7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Object> findByPhoneNo(String phoneNo);
}
