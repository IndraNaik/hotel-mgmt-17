package com.hotel_mgnt._7.dto;

import com.hotel_mgnt._7.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String phoneNo;

    private boolean status;

    private Role role;

    private List<TablesDto> tables;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<TablesDto> getTables() {
        return tables;
    }

    public void setTables(List<TablesDto> tables) {
        this.tables = tables;
    }
}
