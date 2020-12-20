package com.epam.faculty.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "registrations")
@DynamicInsert
@DynamicUpdate
public class Registration {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "registration_id", length = 16, nullable = false, updatable = false)
    private UUID registrationId;

    @Column(name = "course_id", nullable = false)
    private UUID courseId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "student_mark", nullable = false)
    private int studentMark;

    @Column(name = "approve", nullable = false)
    private boolean approve;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private FacultyUser facultyUser;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    public Registration() {
    }

    public Registration(UUID registrationId, UUID courseId, UUID userId, int studentMark, boolean approve )
    {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.userId = userId;
        this.studentMark = studentMark;
        this.approve = approve;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public FacultyUser getFacultyUser() {
        return facultyUser;
    }

    public void setFacultyUser(FacultyUser facultyUser) {
        this.facultyUser = facultyUser;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
