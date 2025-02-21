package com.hotel_mgnt._7.service;

import com.hotel_mgnt._7.dto.TablesDto;
import com.hotel_mgnt._7.entity.Tables;
import com.hotel_mgnt._7.exceptions.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TablesService {
    Response<TablesDto> addTables(TablesDto tablesDto);

    Page<Tables> allTables(Pageable pageable);
}
