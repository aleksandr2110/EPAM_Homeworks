package com.faculty.exception;

/**
 * Created by Aleksandr on 08.11.2020.
 */
public class CrudException extends Exception {
    String message;

    public CrudException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
