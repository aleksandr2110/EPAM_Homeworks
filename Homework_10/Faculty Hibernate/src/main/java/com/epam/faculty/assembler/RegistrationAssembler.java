package com.epam.faculty.assembler;

import com.epam.faculty.dto.RegistrationDto;
import com.epam.faculty.entity.Registration;


public interface RegistrationAssembler {

    Registration assemble(RegistrationDto dto);

    RegistrationDto assemble(Registration registration);
}
