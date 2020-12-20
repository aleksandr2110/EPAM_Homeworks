package com.epam.faculty.dao;

import com.epam.faculty.entity.Registration;

import java.util.UUID;

public interface RegistrationDao {

    void save(Registration registration);
    Registration get(UUID registrationId);
}
