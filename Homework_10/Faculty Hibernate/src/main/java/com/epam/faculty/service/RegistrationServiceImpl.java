package com.epam.faculty.service;

import com.epam.faculty.assembler.RegistrationAssembler;
import com.epam.faculty.dao.RegistrationDao;
import com.epam.faculty.dto.RegistrationDto;
import com.epam.faculty.entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;

    @Autowired
    private RegistrationAssembler registrationAssembler;

    @Override
    @Transactional
    public RegistrationDto create(RegistrationDto regDto) {
        Registration registration = registrationAssembler.assemble(regDto);
        registrationDao.save(registration);
        RegistrationDto registrationDto = registrationAssembler.assemble(registration);
        return registrationDto;
    }

    @Override
    @Transactional(readOnly = true)
    public RegistrationDto get(UUID registrationId) {
        Registration registration = registrationDao.get(registrationId);
        return registrationAssembler.assemble(registration);
    }
}
