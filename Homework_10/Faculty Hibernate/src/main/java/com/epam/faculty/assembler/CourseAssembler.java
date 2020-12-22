package com.epam.faculty.assembler;


import com.epam.faculty.dto.CourseDto;
import com.epam.faculty.entity.Course;

public interface CourseAssembler {

    Course assemble(CourseDto dto);

    CourseDto assemble(Course entity);

}
