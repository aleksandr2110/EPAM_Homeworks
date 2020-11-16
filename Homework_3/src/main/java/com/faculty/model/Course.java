package com.faculty.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Aleksandr on 08.11.2020.
 */
public class Course implements Serializable {

    private long courseId;
    private String courseName;
    private LocalDateTime dateTime;
    private int durationWeeks;
    private int studentCount;
    private long userId;
    private String status;
    private int price;

    public Course(Long courseId, String courseName, LocalDateTime dateTime, int durationWeeks, long userId, String status, int price)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.dateTime = dateTime;
        this.durationWeeks = durationWeeks;
        this.userId = userId;
        this.status = status;
        this.price = price;
    }
    public Course(String courseName, LocalDateTime dateTime, int durationWeeks, int studentCount, long userId, String status, int price)
    {
        this.courseName = courseName;
        this.dateTime = dateTime;
        this.durationWeeks = durationWeeks;
        this.studentCount = studentCount;
        this.userId = userId;
        this.status = status;
        this.price = price;
    }
    public Course(Long courseId, String courseName, LocalDateTime dateTime, int durationWeeks, int studentCount, long userId, String status, int price)
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseId != course.courseId) return false;
        if (durationWeeks != course.durationWeeks) return false;
        if (studentCount != course.studentCount) return false;
        if (userId != course.userId) return false;
        if (price != course.price) return false;
        if (!courseName.equals(course.courseName)) return false;
        if (!dateTime.equals(course.dateTime)) return false;
        return status.equals(course.status);
    }

    @Override
    public int hashCode() {
        int result = (int) (courseId ^ (courseId >>> 32));
        result = 31 * result + courseName.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + durationWeeks;
        result = 31 * result + studentCount;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + status.hashCode();
        result = 31 * result + price;
        return result;
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", dateTime=" + dateTime +
                ", durationWeeks=" + durationWeeks +
                ", studentCount=" + studentCount +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", price=" + price +
                '}';
    }
}
