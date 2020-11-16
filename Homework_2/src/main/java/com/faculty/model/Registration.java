package com.faculty.model;

import java.io.Serializable;

/**
 * Created by Aleksandr on 08.11.2020.
 */
public class Registration implements Serializable {

    private long registrationId;
    private long courseId;
    private long userId;
    private int studentMark;
    private boolean approve;

    public Registration(long registrationId, long courseId, long userId)
    {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.userId = userId;
    }
    public Registration(long registrationId, long courseId, long userId, int studentMark, boolean approve )
    {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.userId = userId;
        this.studentMark = studentMark;
        this.approve = approve;
    }

    public long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(long registrationId) {
        this.registrationId = registrationId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(int studentMark) {
        this.studentMark = studentMark;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Registration that = (Registration) o;

        if (registrationId != that.registrationId) return false;
        if (courseId != that.courseId) return false;
        if (userId != that.userId) return false;
        if (studentMark != that.studentMark) return false;
        return approve == that.approve;
    }

    @Override
    public int hashCode() {
        int result = (int) (registrationId ^ (registrationId >>> 32));
        result = 31 * result + (int) (courseId ^ (courseId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + studentMark;
        result = 31 * result + (approve ? 1 : 0);
        return result;
    }
}
