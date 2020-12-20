package com.epam.faculty.assembler.Impl;

import com.epam.faculty.assembler.CourseAssembler;
import com.epam.faculty.dto.CourseDto;
import com.epam.faculty.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseAssemblerImpl implements CourseAssembler {

    @Override
    public Course assemble(CourseDto dto) {
        Course course = new Course();
        course.setCourseId(dto.getCourseId());
        course.setCourseName(dto.getCourseName());
        course.setDateTime(dto.getDateTime());
        course.setDurationWeeks(dto.getDurationWeeks());
        course.setPrice(dto.getPrice());
        course.setStatus(dto.getStatus());
        course.setStudentCount(dto.getStudentCount());
        course.setUserId(dto.getUserId());
        return course;
    }

    @Override
    public CourseDto assemble(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setUserId(course.getUserId());
        courseDto.setCourseId(course.getCourseId());
        courseDto.setStudentCount(course.getStudentCount());
        courseDto.setStatus(course.getStatus());
        courseDto.setPrice(course.getPrice());
        courseDto.setDurationWeeks(course.getDurationWeeks());
        courseDto.setDateTime(course.getDateTime());
        courseDto.setCourseName(course.getCourseName());
        return courseDto;
    }
}
