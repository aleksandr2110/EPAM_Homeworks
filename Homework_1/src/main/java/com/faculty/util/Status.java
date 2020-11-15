package com.faculty.util;

/**
 * Created by Aleksandr on 13.11.2020.
 */
public enum Status {

    NOTSTARTED("Не начат"),
    INPROGRESS("Начат"),
    FINISHED("Закончен");

    private String status;

    Status(String status)
    {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
