package com.hotel_mgnt._7.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response<T> {
    private String message;
    private T data;
    private HttpStatus httpStatus;

    // Constructor with HTTP status
    public Response(T data, String message, HttpStatus httpStatus) {
        this.data = data;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    // Constructor without HTTP status (defaults to OK)
    public Response(T data, String message) {
        this.data = data;
        this.message = message;
        this.httpStatus = HttpStatus.OK;
    }

    // Default constructor for empty responses
    public Response() {
        this.data = null;
        this.message = "";
        this.httpStatus = HttpStatus.OK;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
