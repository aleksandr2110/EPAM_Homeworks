package com.epam.faculty.service;

import com.epam.faculty.dto.RegistrationDto;
import com.epam.faculty.entity.Registration;

import java.util.UUID;

/**
 * Created by Aleksandr on 19.12.2020.
 */
public interface RegistrationService {

    RegistrationDto create(RegistrationDto registrationDto);
    RegistrationDto get(UUID registrationId);
}
