package com.epam.faculty.assembler.Impl;

import com.epam.faculty.assembler.RegistrationAssembler;
import com.epam.faculty.dto.RegistrationDto;
import com.epam.faculty.entity.Registration;
import org.springframework.stereotype.Component;

@Component
public class RegistrationAssemblerImpl implements RegistrationAssembler {

    @Override
    public Registration assemble(RegistrationDto dto) {
        Registration registration = new Registration();
        registration.setUserId(dto.getUserId());
        registration.setCourseId(dto.getCourseId());
        registration.setApprove(dto.isApprove());
        registration.setRegistrationId(dto.getRegistrationId());
        return registration;
    }

    @Override
    public RegistrationDto assemble(Registration registration) {
        RegistrationDto dto = new RegistrationDto();
        dto.setRegistrationId(registration.getRegistrationId());
        dto.setCourseId(registration.getCourseId());
        dto.setApprove(registration.isApprove());
        dto.setStudentMark(registration.getStudentMark());
        return dto;
    }
}
