package com.faculty.exception;

/**
 * Created by Aleksandr on 08.11.2020.
 */
public class ValidationException extends Exception  {

    String message;

    public ValidationException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
