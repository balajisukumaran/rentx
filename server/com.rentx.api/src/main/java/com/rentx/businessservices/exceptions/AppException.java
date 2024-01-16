package com.rentx.businessservices.exceptions;

import org.springframework.http.HttpStatus;

/**
 * App exception class
 */
public class AppException extends RuntimeException{

    private final HttpStatus code;

    /**
     * constructor for app exception
     * @param message exception message
     * @param code exception code
     */
    public AppException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

    /**
     * get exception code
     * @return Http status
     */
    public HttpStatus getCode() {
        return code;
    }
}
