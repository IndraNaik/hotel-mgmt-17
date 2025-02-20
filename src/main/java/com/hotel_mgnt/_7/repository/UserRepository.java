package com.hotel_mgnt._7.repository;

import com.hotel_mgnt._7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
