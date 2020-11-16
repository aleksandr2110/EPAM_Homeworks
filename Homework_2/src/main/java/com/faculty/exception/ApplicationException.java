package com.faculty.exception;

/**
 * Created by Aleksandr on 13.11.2020.
 */
public class ApplicationException extends Exception {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
