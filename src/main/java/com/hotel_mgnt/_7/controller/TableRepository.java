package com.hotel_mgnt._7.controller;

import com.hotel_mgnt._7.dto.TablesDto;
import com.hotel_mgnt._7.entity.Tables;
import com.hotel_mgnt._7.exceptions.Response;
import com.hotel_mgnt._7.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tables")
public class TableRepository {

    @Autowired
    private TablesService tablesService;

    @PostMapping("/add")
    public ResponseEntity<Response<TablesDto>> addTables(@RequestBody TablesDto tablesDto) {
        Response<TablesDto> response = tablesService.addTables(tablesDto);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @GetMapping("/get")
    public Page<Tables> getTables(Pageable pageable){
        return tablesService.allTables(pageable);
    }
}
