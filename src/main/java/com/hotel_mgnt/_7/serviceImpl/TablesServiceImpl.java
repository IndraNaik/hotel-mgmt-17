package com.hotel_mgnt._7.serviceImpl;

import com.hotel_mgnt._7.dto.TablesDto;
import com.hotel_mgnt._7.entity.Tables;
import com.hotel_mgnt._7.entity.User;
import com.hotel_mgnt._7.enums.Role;
import com.hotel_mgnt._7.exceptions.ResourceNotFoundException;
import com.hotel_mgnt._7.exceptions.Response;
import com.hotel_mgnt._7.repository.TablesRepository;
import com.hotel_mgnt._7.repository.UserRepository;
import com.hotel_mgnt._7.service.TablesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TablesServiceImpl implements TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response<TablesDto> addTables(TablesDto tablesDto) {
        Response<TablesDto> response = new Response<>();

        try {
            User admin = userRepository.findById(tablesDto.getAdmin().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
            if (admin.getRole() != Role.ADMIN) {
                response.setMessage("Only Admin can add tables");
                response.setHttpStatus(HttpStatus.FORBIDDEN);
                return response;
            }

            Tables tables = dtoToEntity(tablesDto);
            tables.setAdmin(admin);
            tables.setBookingStatus(false);
            tables.setPrice(tablesDto.getPrice());
            tables.setCreatedBy(admin.getId());
            tables.setLastModifiedBy(admin.getId());
            tables.setStatus(true);

            Tables savedTable = tablesRepository.save(tables);

            response.setData(entityToDto(savedTable));
            response.setMessage("Table added successfully");
            response.setHttpStatus(HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.setMessage("An error occurred: " + e.getMessage());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @Override
    public Page<Tables> allTables(Pageable pageable) {
        return tablesRepository.findAll(pageable);
    }

    // Entity to Dto
    private TablesDto entityToDto(Tables tables) {
        TablesDto tablesDto = new TablesDto();
        tablesDto = modelMapper.map(tables, TablesDto.class);
        return  tablesDto;
    }

    // Dto to Entity
    private Tables dtoToEntity(TablesDto tablesDto) {
        Tables tables = new Tables();
        tables = modelMapper.map(tablesDto, Tables.class);
        return  tables;
    }
}
