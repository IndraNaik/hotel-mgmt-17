package com.hotel_mgnt._7.repository;

import com.hotel_mgnt._7.entity.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TablesRepository extends JpaRepository<Tables, Long> {
    Page<Tables> findAll(Pageable pageable);

}
