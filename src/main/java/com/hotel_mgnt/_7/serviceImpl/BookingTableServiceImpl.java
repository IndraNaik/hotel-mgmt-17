package com.hotel_mgnt._7.serviceImpl;

import com.hotel_mgnt._7.dto.BookingTableDto;
import com.hotel_mgnt._7.entity.BookingTable;
import com.hotel_mgnt._7.entity.Tables;
import com.hotel_mgnt._7.entity.User;
import com.hotel_mgnt._7.enums.Role;
import com.hotel_mgnt._7.exceptions.ResourceNotFoundException;
import com.hotel_mgnt._7.exceptions.Response;
import com.hotel_mgnt._7.repository.BookingTableRepository;
import com.hotel_mgnt._7.repository.TablesRepository;
import com.hotel_mgnt._7.repository.UserRepository;
import com.hotel_mgnt._7.service.BookingTableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BookingTableServiceImpl implements BookingTableService {


    @Autowired
    private BookingTableRepository bookingTableRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TablesRepository tablesRepository;

    @Override
    public Response<BookingTableDto> bookTable(BookingTableDto bookingTableDto) {
        Response<BookingTableDto> response = new Response<>();

        try {
            User customer = userRepository.findById(bookingTableDto.getCustomer().getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Customer not found!")
            );

            Tables table = tablesRepository.findById(bookingTableDto.getTable_id().getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Table not found!")
            );

            if (!customer.isStatus() || customer.getRole() != Role.CUSTOMER) {
                return new Response<BookingTableDto>(
                        null, "Can't book, invalid customer details", HttpStatus.BAD_REQUEST);
            }

            if (Boolean.TRUE.equals(table.isBookingStatus())) {
                return new Response<BookingTableDto>(
                        null, "Table is already booked!", HttpStatus.BAD_REQUEST);
            }

            BookingTable bookingTable = dtoToEntity(bookingTableDto);
            bookingTable.setCustomer(customer);
            bookingTable.setBooking_status(true);
            bookingTable.setCreatedBy(customer.getId());
            bookingTable.setLastModifiedBy(customer.getId());
            bookingTable.setTable(table);
            table.setBookingStatus(true);
            bookingTable.setWaiter(null);

            BookingTable savedBooking = bookingTableRepository.save(bookingTable);
            response.setData(entityToDto(savedBooking));
            response.setMessage("Table booked successfully");
            response.setHttpStatus(HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            return new Response<BookingTableDto>(null, e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new Response<BookingTableDto>(
                    null, "Table is already booked!", HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @Override
    public Response<BookingTableDto> assignWaiter(BookingTableDto bookingTableDto) {
        Response<BookingTableDto> response = new Response<>();

        try {
            User waiter = userRepository.findById(bookingTableDto.getWaiter().getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Waiter not found!")
            );

            BookingTable bookingTable = bookingTableRepository.findById(bookingTableDto.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Booking not found!")
            );

            bookingTable.setWaiter(waiter);
            BookingTable updatedBooking = bookingTableRepository.save(bookingTable);

            response.setData(entityToDto(updatedBooking));
            response.setMessage("Waiter assigned successfully");
            response.setHttpStatus(HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            return new Response<BookingTableDto>(null, e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new Response<BookingTableDto>(
                    null, "Failed to assign waiter: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }



    // Entity to Dto
    private BookingTableDto entityToDto(BookingTable bookingTable) {
        BookingTableDto bookingTableDto = new BookingTableDto();
        bookingTableDto = modelMapper.map(bookingTable, BookingTableDto.class);
        return bookingTableDto;
    }

    // Dto to Entity
    private BookingTable dtoToEntity(BookingTableDto bookingTableDto) {
        BookingTable  bookingTable = new BookingTable();
        bookingTable = modelMapper.map(bookingTableDto, BookingTable.class);
        return bookingTable;
    }
}
