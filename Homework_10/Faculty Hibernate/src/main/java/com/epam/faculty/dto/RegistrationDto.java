package com.epam.faculty.dto;

import java.util.UUID;

/**
 * Created by Aleksandr on 18.12.2020.
 */
public class RegistrationDto {

    private UUID registrationId;
    private UUID courseId;
    private UUID userId;
    private int studentMark;
    private boolean approve;

    public RegistrationDto() {
    }

    public RegistrationDto(UUID registrationId, UUID courseId, UUID userId, int studentMark, boolean approve )
    {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.userId = userId;
        this.studentMark = studentMark;
        this.approve = approve;
    }

    public UUID getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(UUID registrationId) {
        this.registrationId = registrationId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
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
}
