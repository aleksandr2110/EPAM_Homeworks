package com.epam.faculty.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


public class CourseDto implements Serializable {

    private UUID courseId;
    private String courseName;
    private LocalDateTime dateTime;
    private int durationWeeks;
    private int studentCount;
    private long userId;
    private String status;
    private int price;

    public CourseDto() {
    }

    public CourseDto(UUID courseId, String courseName, LocalDateTime dateTime, int durationWeeks, long userId, String status, int price)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.dateTime = dateTime;
        this.durationWeeks = durationWeeks;
        this.userId = userId;
        this.status = status;
        this.price = price;
    }
    public CourseDto(String courseName, LocalDateTime dateTime, int durationWeeks, int studentCount, long userId, String status, int price)
    {
        this.courseName = courseName;
        this.dateTime = dateTime;
        this.durationWeeks = durationWeeks;
        this.studentCount = studentCount;
        this.userId = userId;
        this.status = status;
        this.price = price;
    }
    public CourseDto(UUID courseId, String courseName, LocalDateTime dateTime, int durationWeeks, int studentCount, long userId, String status, int price)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.dateTime = dateTime;
        this.durationWeeks = durationWeeks;
        this.studentCount = studentCount;
        this.userId = userId;
        this.status = status;
        this.price = price;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
