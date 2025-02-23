package com.hotel_mgnt._7.repository;

import com.hotel_mgnt._7.entity.User;
import com.hotel_mgnt._7.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Object> findByPhoneNo(String phoneNo);

//    @Query(value ="select * from users where status=1", nativeQuery = true)
    Page<User> findByRole(Role role, Pageable pageable);

    Page<User> findByRoleAndStatusTrue(Role role, Pageable pageable);
}
