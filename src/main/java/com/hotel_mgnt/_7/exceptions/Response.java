package com.hotel_mgnt._7.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<C> {

    private String message;
    private C data;
    private HttpStatus httpStatus;

    // Constructor when HTTP status is provided
    public Response(C data, String message, HttpStatus httpStatus) {
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
    }

    // Constructor when HTTP status is NOT provided (default to OK)
    public Response(C data, String message) {
        this.message = message;
        this.data = data;
        this.httpStatus = HttpStatus.OK; // Default value
    }
}
